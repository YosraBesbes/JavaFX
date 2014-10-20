package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledCurrencyField;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.model.Employee;
import ph.txtdis.model.Loan;
import ph.txtdis.type.LoanType;

public class LoanDialog extends AbstractFieldDialog<Loan, EmployeeDTO> {

    public LoanDialog(Stage stage, EmployeeDTO dto) {
        super("Loan", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<LoanType> typeComboBox = new LabeledComboBox<>("Type", LoanType.values());
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start");
        LabeledCurrencyField amountBigDecimalField = new LabeledCurrencyField("Amount");
        return Arrays.asList(typeComboBox, startDatePicker, amountBigDecimalField);
    }

    @Override
    protected Loan createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        LoanType type = getInputAtRow(0);
        LocalDate start = getInputAtRow(1);
        BigDecimal amount = getInputAtRow(2);
        return new Loan(employee, type, start, amount);
    }
}
