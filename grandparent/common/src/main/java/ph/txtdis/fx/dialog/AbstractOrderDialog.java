package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.app.OrderApp;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.OrderDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.model.Item;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Quality;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.UomType;

public abstract class AbstractOrderDialog<E extends Ordered<D>, D extends Priced, T extends OrderDTO<E, D>> extends
        AbstractFieldDialog<D, T> implements Invoiced {

    private LabeledComboBox<UomType> uomCombo;
    private LabeledIdNameField itemField;
    private LabeledDecimalField qtyField;
    private LabeledComboBox<Quality> qualityCombo;
    private ItemDTO itemDTO;

    protected int lineNo;

    public AbstractOrderDialog(String module, Stage stage, T dto, int lineNo) {
        super(module, stage, dto);
        this.lineNo = lineNo;
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        QualityRated quality = getQuality();
        itemField = new LabeledIdNameField("Item No.", 18);
        uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        qtyField = new LabeledDecimalField("Quantity");
        qualityCombo = new LabeledComboBox<Quality>("Quality", quality.list());
        qualityCombo.setSelection(quality.good());
        return Arrays.asList(itemField, uomCombo, qtyField, qualityCombo);
    }

    protected abstract QualityRated getQuality();

    protected abstract ItemDTO getItemDTO();

    private void setListeners() {
        itemDTO = getItemDTO();

        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                verifyItemId();
        });

        qtyField.getDecimalField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                verifyQty(qtyField.getValue(), uomCombo.getValue());
        });
    }

    private void verifyItemId() {
        try {
            checkItemIdExists();
        } catch (NotFoundException e) {
            handleException(stage, e);
        }
    }

    private void checkItemIdExists() throws NotFoundException {
        int id = itemField.getValue();
        if (itemDTO.exists(id))
            handleFoundId(id);
        else
            throw new NotFoundException("Item No. " + id);
    }

    protected void handleFoundId(int id) {
        itemDTO.setById(id);
        uomCombo.setItems(itemDTO.getSellingUoms());
        itemField.getNameField().setText(itemDTO.getName());
    }

    private void handleException(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    private void verifyQty(BigDecimal qty, UomType uom) {
        try {
            checkQtyinPCsIsLessThanPerCS(qty, uom);
        } catch (InvalidException e) {
            handleException(stage, e);
        }
    }

    private void checkQtyinPCsIsLessThanPerCS(BigDecimal qty, UomType uom) throws InvalidException {
        if (uom == UomType.PC && hasCSasUom() && qty.compareTo(getQtyPerCS()) >= 0)
            throw new InvalidException("Quantity in PCs\nmust be less than " + getQtyPerCS());
    }

    private boolean hasCSasUom() {
        return getQtyPerCS() != null;
    }

    private BigDecimal getQtyPerCS() {
        return itemDTO.getQtyPerUomMap().get(UomType.CS);
    }

    @Override
    protected D createEntity(T dto, List<InputNode<?>> inputNodes) {

        LocalDate date = ((OrderApp<?>) stage).getPickerDate();
        E entity = dto.get();
        Item item = itemDTO.get();
        UomType uom = getInputAtRow(1);
        BigDecimal qty = getInputAtRow(2);
        Quality quality = getInputAtRow(3);

        D detail = getEntity(entity, item, uom, qty, quality);
        detail.setPrice(getNetPrice(date, uom, qty));
        return detail;
    }

    protected abstract D getEntity(E entity, Item item, UomType uom, BigDecimal qty, Quality quality);

    private BigDecimal getNetPrice(LocalDate date, UomType uom, BigDecimal qty) {
        BigDecimal qtyInPCs = itemDTO.getQtyPerUomMap().get(uom);
        return itemDTO.getLatestSellingPrice(date).multiply(qtyInPCs).subtract(getDiscount(date, qtyInPCs));
    }

    private BigDecimal getDiscount(LocalDate date, BigDecimal qtyInPCs) {
        return computeDiscount(qtyInPCs, itemDTO.getLatestVolumeDiscount(date));
    }

    private BigDecimal computeDiscount(BigDecimal qtyInPCs, VolumeDiscount discount) {
        if (discount == null)
            return BigDecimal.ZERO;
        else
            return determineDiscountBasedOnQty(qtyInPCs, discount, new BigDecimal(discount.getCutOff()));
    }

    private BigDecimal determineDiscountBasedOnQty(BigDecimal qtyInPCs, VolumeDiscount discount, BigDecimal cutoff) {
        if (qtyInPCs.compareTo(cutoff) >= 0)
            return cutoff.multiply(discount.getDiscount());
        return BigDecimal.ZERO;
    }

    @Override
    public int getLineNo() {
        return lineNo;
    }
}
