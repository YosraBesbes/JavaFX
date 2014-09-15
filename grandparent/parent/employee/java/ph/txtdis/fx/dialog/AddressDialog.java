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
import ph.txtdis.model.EmployeeAddress;
import ph.txtdis.type.AddressType;

public class AddressDialog extends AbstractFieldDialog<EmployeeAddress, EmployeeDTO> {

    public AddressDialog(Stage stage, EmployeeDTO dto) {
        super("Address", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<AddressType> typeLabel = new LabeledComboBox<>("Type", AddressType.values());
        LabeledStringField streetField = new LabeledStringField("Street");
        LabeledStringField barangayField = new LabeledStringField("Barangay");
        LabeledStringField townField = new LabeledStringField("Town/District");
        LabeledStringField provinceField = new LabeledStringField("City/Province");
        return Arrays.asList(typeLabel, streetField, barangayField, townField, provinceField);
    }

    @Override
    protected EmployeeAddress createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        AddressType type = getInputAtRow(0);
        String street = getInputAtRow(1);
        String barangay = getInputAtRow(2);
        String town = getInputAtRow(3);
        String province = getInputAtRow(4);
        String location = street + ", " + barangay + ", " + town + ", " + province;
        return new EmployeeAddress(employee, type, location);
    }
}
