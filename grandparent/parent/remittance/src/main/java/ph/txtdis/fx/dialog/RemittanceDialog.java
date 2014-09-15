package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledIdNameField;
import ph.txtdis.fx.input.LabeledMonetaryDisplay;
import ph.txtdis.fx.input.LabeledMonetaryField;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.type.RemittanceReferenceType;

public class RemittanceDialog extends AbstractFieldDialog<RemittanceDetail, RemittanceDTO> {

    private LabeledComboBox<RemittanceReferenceType> referenceCombo;
    private LabeledIdNameField referenceField;
    private LabeledMonetaryDisplay amountDisplay, balanceDisplay;
    private LabeledMonetaryField paymentField;
    private AuditedDTO<?> dto;

    public RemittanceDialog(Stage stage, RemittanceDTO dto) {
        super("Remittance", stage, dto);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {

        referenceField = new LabeledIdNameField("Item ID No.", 18);
        referenceCombo = new LabeledComboBox<>("Reference Type", RemittanceReferenceType.values());
        amountDisplay = new LabeledMonetaryDisplay("Amount");
        paymentField = new LabeledMonetaryField("Payment");
        balanceDisplay = new LabeledMonetaryDisplay("Balance");

        return Arrays.asList(referenceField, referenceCombo, amountDisplay, paymentField, balanceDisplay);
    }

    private void setListeners() {
        referenceField.getIdField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB) {
                int id = referenceField.getValue();
                RemittanceReferenceType type = referenceCombo.getValue();
                switch (type) {
                    case DELIVERY:
                        // dto = App.getContext().getBean(DeliveryDTO.class);
                break;
                    case EWT:
                        // dto = App.getContext().getBean(EwtDTO.class);
                break;
                    case INVOICE:
                        dto = App.getContext().getBean(InvoicingDTO.class);
                        break;
                    case REMITTANCE:
                        dto = App.getContext().getBean(RemittanceDTO.class);
                        break;
                    case VALE:
                        // dto = App.getContext().getBean(ValeDTO.class);
                        break;
                }
                if (dto.exists(id)) {
                    actWhenFound(id);
                } else {
                    try {
                        throw new NotFoundException(type + " No. " + id);
                    } catch (Exception e) {
                        actOnError(this, e);
                    }
                }
            }
        });

        amountDisplay.getTextField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                ;
            // checkAvailableQty(amountDisplay.getValue());
            });

    }

    private void actWhenFound(int id) {
        dto.setId(id);
    }

    private void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    @Override
    protected RemittanceDetail createEntity(RemittanceDTO dto, List<InputNode<?>> inputNodes) {

        // @SuppressWarnings("unchecked")
        // Date date = ((RemittanceAppImpl) stage).getPickerDate();
        // Remittance remittance = dto.get();
        // UomType uom = getInputAtRow(1);
        // BigDecimal qty = getInputAtRow(2);

        RemittanceDetail detail = null;
        return detail;
    }
}
