package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.display.LabeledDisplay;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledIdField;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;

public class RemittanceDialog extends AbstractFieldDialog<RemittanceDetail, RemittanceDTO> {

    private BigDecimal remainingPayment;
    private LabeledIdField idField;
    private LabeledDisplay partnerDisplay, dateDisplay;
    private InvoicingDTO invoicing;

    public RemittanceDialog(Stage stage, RemittanceDTO dto, BigDecimal remainingPayment) {
        super("Remittance", stage, dto);
        this.remainingPayment = remainingPayment;
        invoicing = App.context().getBean(InvoicingDTO.class);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        idField = new LabeledIdField("S/I No.");
        return Arrays.asList(idField);
    }

    private void setListeners() {
        idField.getTextField().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                verifyInvoiceId();
        });
    }

    private void verifyInvoiceId() {
        try {
            checkInvoiceIdExists();
        } catch (NotFoundException e) {
            handleException(stage, e);
        }
    }

    private void checkInvoiceIdExists() throws NotFoundException {
        int id = idField.getValue();
        if (invoicing.exists(id))
            handleFoundId(id);
        else
            throw new NotFoundException("S/I No. " + id);
    }

    private void handleFoundId(int id) {
        invoicing.setById(id);
        partnerDisplay.setText(invoicing.getPartnerName());
        dateDisplay.setDate(invoicing.getOrderDate());
    }

    private void handleException(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        inputNodes.forEach(inputNode -> inputNode.reset());
    }

    @Override
    protected RemittanceDetail createEntity(RemittanceDTO dto, List<InputNode<?>> inputNodes) {

        Remittance remittance = dto.get();
        Invoicing invoice = invoicing.get();
        BigDecimal unpaid = invoicing.getTotalValue().subtract(dto.getPayment(invoice));
        BigDecimal payment = remainingPayment.compareTo(unpaid) > 0 ? unpaid : remainingPayment;
        remainingPayment = remainingPayment.subtract(payment);

        return new RemittanceDetail(remittance, invoice, unpaid, payment);
    }

    @Override
    protected void addItems(RemittanceDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        if (remainingPayment.compareTo(BigDecimal.ZERO) <= 0)
            close();
    }

    public BigDecimal getRemainingPayment() {
        return remainingPayment;
    }
}
