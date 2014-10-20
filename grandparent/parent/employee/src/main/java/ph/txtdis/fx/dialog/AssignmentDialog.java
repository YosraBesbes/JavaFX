package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Assignment;
import ph.txtdis.model.Employee;

public class AssignmentDialog extends AbstractFieldDialog<Assignment, EmployeeDTO> {

    public AssignmentDialog(Stage stage, EmployeeDTO dto) {
        super("Assignment", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start");
        LabeledStringField locationField = new LabeledStringField("Location");
        LabeledStringField designationField = new LabeledStringField("Designation");
        return Arrays.asList(startDatePicker, locationField, designationField);
    }

    @Override
    protected Assignment createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        LocalDate startDate = getInputAtRow(0);
        String location = getInputAtRow(1);
        String designation = getInputAtRow(2);
        return new Assignment(employee, startDate, location, designation);
    }
}
