package ph.txtdis.fx.table;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.AddressDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.EmployeeAddress;
import ph.txtdis.type.AddressType;

public class AddressTable extends AbstractTable<EmployeeAddress, EmployeeDTO> {

	public AddressTable(Stage stage, EmployeeDTO dto) {
		super(stage, dto);
	}

	@SuppressWarnings("unchecked")
	protected void addTableColumns() {

		TableColumn<EmployeeAddress, AddressType> typeCol = FX.addComboColumn(
				"Type", "type", AddressType.values());
		TableColumn<EmployeeAddress, String> locationCol = FX.addStringColumn(
				"Location", "location", 400);
		table.getColumns().addAll(typeCol, locationCol);
		table.setMinHeight(65);
	}

	@Override
	protected void setInputDialog() {
		inputDialog = new AddressDialog(stage, dto);
	}
}
