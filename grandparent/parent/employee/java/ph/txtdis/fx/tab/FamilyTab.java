package ph.txtdis.fx.tab;

import java.sql.Date;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.FamilyDialog;
import ph.txtdis.fx.table.AbstractTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Family;
import ph.txtdis.type.FamilyType;

public class FamilyTab extends AbstractTab<Family, EmployeeDTO> {
	private TableView<Family> table;

	public FamilyTab(Stage stage, EmployeeDTO dto) {
		super("Family", stage, dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Node[] addNodes(Stage stage, EmployeeDTO dto) {

		table = new AbstractTable<Family, EmployeeDTO>(stage, dto) {

			@Override
			protected void addTableColumns() {
				TableColumn<Family, FamilyType> relationCol = FX.addComboColumn(
						"Relation", "type", FamilyType.values());
				TableColumn<Family, String> surnameCol = FX.addStringColumn(
						"Surname", "surname", 100);
				TableColumn<Family, String> nameCol = FX.addStringColumn(
						"Name", "name", 120);
				TableColumn<Family, String> middleInitialCol = FX
						.addStringColumn("MI", "middleInitial", 10);
				TableColumn<Family, Date> birthdateCol = FX.addDateColumn(
						"Birthdate", "birthdate");
				TableColumn<Family, String> institutionCol = FX
						.addStringColumn("Institution", "institution", 160);
				TableColumn<Family, String> designationCol = FX
						.addStringColumn("Designation", "designation", 160);
				table.getColumns().addAll(relationCol, surnameCol, nameCol,
						middleInitialCol, birthdateCol, institutionCol,
						designationCol);
			}

			@Override
			protected void setInputDialog() {
				inputDialog = new FamilyDialog(stage, dto);
			}
			
		}.getTable();

		return new Node[] { table };
	}

	@Override
	public void save() {
		dto.setRelatives(table.getItems());
	}

	@Override
	public void refresh() {
		table.setItems(dto.getRelatives());
	}
}
