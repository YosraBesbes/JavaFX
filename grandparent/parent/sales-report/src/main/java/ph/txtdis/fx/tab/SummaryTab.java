package ph.txtdis.fx.tab;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.SalesReportDTO;
import ph.txtdis.fx.table.SummaryTable;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.util.Util;

public class SummaryTab extends AbstractTab<SalesReportDTO> implements MultiTabled {

    private Label summarylabel;
    private TableView<VolumeByRoute> summaryTable;

    public SummaryTab(Stage stage, SalesReportDTO dto) {
        super("Total Sales to Trade", "summary", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SalesReportDTO dto) {
        return new Node[] { new VBox(setSummaryTableHBox()) };
    }

    public HBox setSummaryTableHBox() {
        HBox hBox = new HBox(setSummaryTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setSummaryTableVBox() {
        setSummaryTable();
        return new VBox(setSummaryLabel(), summaryTable);
    }

    private TableView<VolumeByRoute> setSummaryTable() {
        summaryTable = new SummaryTable(stage, dto.getRouteNames()).getTable();
        setSummaryTableData();
        return summaryTable;
    }

    private void setSummaryTableData() {
        summaryTable.setItems(dto.getSummary(dto.getStartDate(), dto.getEndDate()));
        summaryTable.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
    }

    private Label setSummaryLabel() {
        summarylabel = new Label(summaryTable.getId());
        summarylabel.setStyle("-fx-font-size: 16pt;");
        return summarylabel;
    }

    private void setSummaryData() {
        setSummaryTableData();
        summarylabel.setText(summaryTable.getId());
    }

    @Override
    public void refresh() {
        setSummaryData();
    }

    @Override
    public void save() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(summaryTable);
    }
}
