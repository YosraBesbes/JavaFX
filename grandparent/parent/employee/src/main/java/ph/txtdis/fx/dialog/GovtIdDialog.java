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
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Employee;
import ph.txtdis.model.GovtId;
import ph.txtdis.type.GovtIdType;
import ph.txtdis.util.Util;

public class GovtIdDialog extends AbstractFieldDialog<GovtId, EmployeeDTO> {

    public GovtIdDialog(Stage stage, EmployeeDTO dto) {
        super("Government-Issued ID", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledComboBox<GovtIdType> typeLabel = new LabeledComboBox<>("Type", GovtIdType.values());
        LabeledDatePicker issuedDatePicker = new LabeledDatePicker("Issuance");
        LabeledDatePicker expiryDatePicker = new LabeledDatePicker("Expiry");
        LabeledStringField detailField = new LabeledStringField("Detail");
        return Arrays.asList(typeLabel, issuedDatePicker, expiryDatePicker, detailField);
    }

    @Override
    protected GovtId createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        Byte[] image = Util.inputStreamToBytes(FX.getDefaultImageStream("id"));
        GovtIdType type = getInputAtRow(0);
        Date issuance = getInputAtRow(1);
        Date expiry = getInputAtRow(2);
        String detail = getInputAtRow(3);
        return new GovtId(employee, image, type, issuance, expiry, detail);
    }
}
