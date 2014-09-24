package ph.txtdis.fx.dialog;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.AbstractFieldDialog;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Employee;
import ph.txtdis.model.Family;
import ph.txtdis.type.FamilyType;

public class FamilyDialog extends AbstractFieldDialog<Family, EmployeeDTO> {

    public FamilyDialog(Stage stage, EmployeeDTO dto) {
        super("Family", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<FamilyType> typeLabel = new LabeledComboBox<>("Type", FamilyType.values());
        LabeledStringField surnameField = new LabeledStringField("Last Name");
        LabeledStringField nameField = new LabeledStringField("Given Name/s");
        LabeledStringField middleInitialField = new LabeledStringField("Middle Initial");
        LabeledDatePicker birthdatePicker = new LabeledDatePicker("Birthdate");
        LabeledStringField institutionField = new LabeledStringField("Institution");
        LabeledStringField designationField = new LabeledStringField("Designation");
        return Arrays.asList(typeLabel, surnameField, nameField, middleInitialField, birthdatePicker, institutionField,
                designationField);
    }

    @Override
    protected Family createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        FamilyType type = getInputAtRow(0);
        String surname = getInputAtRow(1);
        String name = getInputAtRow(2);
        String middleInitial = getInputAtRow(3);
        Date birthdate = getInputAtRow(4);
        String instution = getInputAtRow(5);
        String designation = getInputAtRow(6);
        return new Family(employee, type, surname, name, middleInitial, birthdate, instution, designation);
    }
}
