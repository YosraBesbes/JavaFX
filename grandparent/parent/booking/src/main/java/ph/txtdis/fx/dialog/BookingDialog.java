package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.app.OrderApp;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Item;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Quality;
import ph.txtdis.type.UomType;

public class BookingDialog extends AbstractFieldDialog<BookingDetail, BookingDTO> {

    private LabeledComboBox<UomType> uomCombo;
    private LabeledIdNameField itemField;
    private LabeledDecimalField qtyField;
    private LabeledComboBox<Quality> qualityCombo;
    private ItemDTO itemDTO;

    public BookingDialog(Stage stage, BookingDTO dto) {
        super("Booking", stage, dto);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {

        QualityRated quality = App.getContext().getBean(QualityRated.class);

        itemField = new LabeledIdNameField("Item ID No.", 180);
        uomCombo = new LabeledComboBox<>("UOM", UomType.values());
        qtyField = new LabeledDecimalField("Quantity");
        qualityCombo = new LabeledComboBox<Quality>("Quality", quality.list());
        qualityCombo.setSelection(quality.good());

        return Arrays.asList(itemField, uomCombo, qtyField, qualityCombo);
    }

    private void setListeners() {
        itemDTO = App.getContext().getBean(ItemDTO.class);
        itemField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB) {
                int id = itemField.getValue();
                if (itemDTO.exists(id)) {
                    actWhenFound(id);
                } else {
                    try {
                        throw new NotFoundException("Item ID No. " + id);
                    } catch (Exception e) {
                        actOnError(this, e);
                    }
                }
            }
        });

        qtyField.getDecimalField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                checkAvailableQty(qtyField.getValue());
        });

    }

    private void checkAvailableQty(BigDecimal value) {
        // TODO Auto-generated method stub

    }

    private void actWhenFound(int id) {
        itemDTO.setById(id);
        uomCombo.setItems(itemDTO.getSellingUoms());
        itemField.getNameField().setText(itemDTO.getName());
    }

    private void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    @Override
    protected BookingDetail createEntity(BookingDTO dto, List<InputNode<?>> inputNodes) {

        @SuppressWarnings("unchecked")
        LocalDate date = ((OrderApp<Priced>) stage).getPickerDate();
        Booking booking = dto.get();
        Item item = itemDTO.get();
        UomType uom = getInputAtRow(1);
        BigDecimal qty = getInputAtRow(2);
        Quality quality = getInputAtRow(3);

        BookingDetail detail = new BookingDetail(booking, item, uom, qty, quality);
        detail.setPrice(itemDTO.getLatestPurchasePrice(date));
        detail.setSubtotal(detail.getPrice().multiply(qty.multiply(itemDTO.getQtyPerUomMap().get(uom))));
        return detail;
    }
}
