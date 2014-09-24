package ph.txtdis.fx.tab;

import java.sql.Date;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.DisciplineDialog;
import ph.txtdis.fx.table.AbstractTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Discipline;

public class DisciplineTab extends AbstractTab<Discipline, EmployeeDTO> {
    private TableView<Discipline> table;

    public DisciplineTab(Stage stage, EmployeeDTO dto) {
        super("Discipline", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, EmployeeDTO dto) {
        table = new AbstractTable<Discipline, EmployeeDTO>(stage, dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {

                TableColumn<Discipline, Date> incidenceCol = FX.addDateColumn("Incidence", "incidence");
                TableColumn<Discipline, String> allegationCol = FX.addStringColumn("Allegation", "allegation", 100);
                TableColumn<Discipline, String> decisionCol = FX.addStringColumn("Decision", "decision", 100);
                TableColumn<Discipline, Date> effectivityCol = FX.addDateColumn("Effectivity", "effectivity");
                TableColumn<Discipline, Integer> dayCountCol = FX.addIntegerColumn("No. of Days", "dayCount");
                TableColumn<Discipline, Boolean> noticeCol = FX.addBooleanColumn("Notice", "noticeGiven");
                TableColumn<Discipline, Boolean> replyCol = FX.addBooleanColumn("Reply", "replyReceived");
                TableColumn<Discipline, Boolean> rulingCol = FX.addBooleanColumn("Decision", "decisionGiven");
                TableColumn<Discipline, Boolean> documentationCol = new TableColumn<>("Documentation\nGiven/Received");
                documentationCol.getColumns().addAll(noticeCol, replyCol, rulingCol);
                table.getColumns().addAll(incidenceCol, allegationCol, decisionCol, effectivityCol, dayCountCol,
                        documentationCol);
            }

            @Override
            protected void setInputDialog() {
                inputDialog = new DisciplineDialog(stage, dto);
            }

        }.getTable();

        return new Node[] { table };
    }

    @Override
    public void save() {
        dto.setDisciplinaryActions(table.getItems());
    }

    public void refresh() {
        table.setItems(dto.getDisciplinaryActions());
    }
}
