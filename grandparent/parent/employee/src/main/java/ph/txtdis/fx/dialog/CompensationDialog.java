package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledCurrencyField;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.model.Compensation;
import ph.txtdis.model.Employee;

public class CompensationDialog extends AbstractFieldDialog<Compensation, EmployeeDTO> {

    public CompensationDialog(Stage stage, EmployeeDTO dto) {
        super("Compensation", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start");
        LabeledCurrencyField dailyRateField = new LabeledCurrencyField("Daily Rate");
        return Arrays.asList(startDatePicker, dailyRateField);
    }

    @Override
    protected Compensation createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        LocalDate startDate = getInputAtRow(0);
        BigDecimal dailyRate = getInputAtRow(1);
        return new Compensation(employee, startDate, dailyRate);
    }
}
