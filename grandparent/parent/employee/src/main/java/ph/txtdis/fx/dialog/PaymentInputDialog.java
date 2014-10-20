package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledCurrencyField;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.model.Loan;
import ph.txtdis.model.Payment;

public class PaymentInputDialog extends AbstractFieldDialog<Payment, Loan> {

    public PaymentInputDialog(Stage stage, Loan loan) {
        super("Payment", stage, loan);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker receiptDatePicker = new LabeledDatePicker("Receipt");
        LabeledCurrencyField amountField = new LabeledCurrencyField("Amount");
        return Arrays.asList(receiptDatePicker, amountField);
    }

    @Override
    protected Payment createEntity(Loan loan, List<InputNode<?>> inputNodes) {
        LocalDate paymentDate = getInputAtRow(0);
        BigDecimal amount = getInputAtRow(1);
        return new Payment(loan, paymentDate, amount);
    }
}
