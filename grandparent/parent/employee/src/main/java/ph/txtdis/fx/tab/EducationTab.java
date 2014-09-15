package ph.txtdis.fx.tab;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.EducationDialog;
import ph.txtdis.fx.table.AbstractInputTable;
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
        table = new AbstractInputTable<Education, EmployeeDTO>(stage, dto) {

            @Override
            protected void addTableColumns() {
                TableColumn<Education, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
                TableColumn<Education, LocalDate> endCol = FX.addDateColumn("End", "endDate");
                TableColumn<Education, String> institutionCol = FX.addStringColumn("Institution", "institution", 160);
                TableColumn<Education, String> programCol = FX.addStringColumn("Program/Course", "program", 160);
                TableColumn<Education, String> highestHonorCol = FX.addStringColumn("Highest Honor", "highestHonor",
                        160);

                table.getColumns().addAll(startCol, endCol, institutionCol, programCol, highestHonorCol);
            }

            @Override
            protected void createInputDialog() {
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