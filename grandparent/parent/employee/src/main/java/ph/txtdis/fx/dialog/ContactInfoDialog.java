package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.AbstractFieldDialog;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Employee;
import ph.txtdis.model.EmployeeContactInfo;
import ph.txtdis.type.ContactInfoType;

public class ContactInfoDialog extends AbstractFieldDialog<EmployeeContactInfo, EmployeeDTO> {

    public ContactInfoDialog(Stage stage, EmployeeDTO dto) {
        super("Contact Info", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<ContactInfoType> typeLabel = new LabeledComboBox<>("Type", ContactInfoType.values());
        LabeledStringField streetField = new LabeledStringField("Detail");
        return Arrays.asList(typeLabel, streetField);
    }

    @Override
    protected EmployeeContactInfo createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        ContactInfoType type = getInputAtRow(0);
        String detail = getInputAtRow(1);
        return new EmployeeContactInfo(employee, type, detail);
    }
}
