package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.ContactInfoDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.EmployeeContactInfo;
import ph.txtdis.type.ContactInfoType;

public class ContactInfoTable extends AbstractTable<EmployeeContactInfo, EmployeeDTO> {

	public ContactInfoTable(Stage stage, EmployeeDTO dto) {
		super(stage, dto);
	}

	@SuppressWarnings("unchecked")
	protected void addTableColumns() {

		TableColumn<EmployeeContactInfo, ContactInfoType> typeCol = FX.addComboColumn(
				"Type", "type", ContactInfoType.values());
		TableColumn<EmployeeContactInfo, String> detailCol = FX.addStringColumn(
				"Detail", "detail", 200);
		table.getColumns().addAll(typeCol, detailCol);
		table.setMinHeight(65);
	}

	@Override
	protected void setInputDialog() {
		inputDialog = new ContactInfoDialog(stage, dto);
	}
}
