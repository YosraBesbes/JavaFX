package ph.txtdis.fx.tab;

import java.sql.Date;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.EducationDialog;
import ph.txtdis.fx.table.AbstractTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Education;

public class EducationTab extends AbstractTab<Education, EmployeeDTO> {
	private TableView<Education> table;

	public EducationTab(Stage stage, EmployeeDTO dto) {
		super("Education", stage, dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Node[] addNodes(Stage stage, EmployeeDTO dto) {
		table = new AbstractTable<Education, EmployeeDTO>(stage, dto) {

			@Override
			protected void addTableColumns() {
				TableColumn<Education, Date> startCol = FX.addDateColumn(
						"Start", "startDate");
				TableColumn<Education, Date> endCol = FX.addDateColumn("End",
						"endDate");
				TableColumn<Education, String> institutionCol = FX
						.addStringColumn("Institution", "institution", 160);
				TableColumn<Education, String> programCol = FX.addStringColumn(
						"Program/Course", "program", 160);
				TableColumn<Education, String> highestHonorCol = FX
						.addStringColumn("Highest Honor", "highestHonor", 160);

				table.getColumns().addAll(startCol, endCol, institutionCol,
						programCol, highestHonorCol);
			}

			@Override
			protected void setInputDialog() {
				inputDialog = new EducationDialog(stage, dto);
			}

		}.getTable();

		return new Node[] { table };
	}

	@Override
	public void save() {
		dto.setStudies(table.getItems());
	}

	@Override
	public void refresh() {
		table.setItems(dto.getStudies());
	}
}