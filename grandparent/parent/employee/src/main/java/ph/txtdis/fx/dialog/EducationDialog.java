package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Education;
import ph.txtdis.model.Employee;

public class EducationDialog extends AbstractFieldDialog<Education, EmployeeDTO> {

    public EducationDialog(Stage stage, EmployeeDTO dto) {
        super("Education", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start Date");
        LabeledDatePicker endDatePicker = new LabeledDatePicker("End Date");
        LabeledStringField institutionField = new LabeledStringField("Institution");
        LabeledStringField programField = new LabeledStringField("Program/Course");
        LabeledStringField highestHonorField = new LabeledStringField("Highest Honors Received");
        return Arrays.asList(startDatePicker, endDatePicker, institutionField, programField, highestHonorField);
    }

    @Override
    protected Education createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        LocalDate startDate = getInputAtRow(0);
        LocalDate endDate = getInputAtRow(1);
        String institution = getInputAtRow(2);
        String program = getInputAtRow(3);
        String highestHonor = getInputAtRow(4);
        return new Education(employee, startDate, endDate, institution, program, highestHonor);
    }
}
