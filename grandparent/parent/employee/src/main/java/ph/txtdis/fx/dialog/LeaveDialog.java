package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledIntegerField;
import ph.txtdis.model.Employee;
import ph.txtdis.model.Leave;
import ph.txtdis.type.LeaveType;

public class LeaveDialog extends AbstractFieldDialog<Leave, EmployeeDTO> {

    public LeaveDialog(Stage stage, EmployeeDTO dto) {
        super("Leave", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<LeaveType> typeLabel = new LabeledComboBox<>("Type", LeaveType.values());
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start");
        LabeledIntegerField dayCountField = new LabeledIntegerField("Days");
        return Arrays.asList(typeLabel, startDatePicker, dayCountField);
    }

    @Override
    protected Leave createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        LeaveType type = getInputAtRow(0);
        LocalDate start = getInputAtRow(1);
        int day = getInputAtRow(2);
        return new Leave(employee, type, start, day);
    }
}
