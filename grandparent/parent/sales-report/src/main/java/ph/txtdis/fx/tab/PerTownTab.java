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
import ph.txtdis.fx.table.PerTownTable;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.util.Util;

public class PerTownTab extends AbstractTab<SalesReportDTO> implements MultiTabled {

    private Label perTownLabel;
    private TableView<VolumePerTownByRoute> perTownTable;

    public PerTownTab(Stage stage, SalesReportDTO dto) {
        super("Quantity per Town", "town", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SalesReportDTO dto) {
        return new Node[] { new VBox(setPerTownTableHBox()) };
    }

    public HBox setPerTownTableHBox() {
        HBox hBox = new HBox(setPerTownTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setPerTownTableVBox() {
        setPerTownTable();
        return new VBox(setPerTownLabel(), perTownTable);
    }

    private TableView<VolumePerTownByRoute> setPerTownTable() {
        perTownTable = new PerTownTable(stage, dto.getRouteNames()).getTable();
        setPerTownTableData();
        return perTownTable;
    }

    private void setPerTownTableData() {
        perTownTable.setItems(dto.getPerTown(dto.getStartDate(), dto.getEndDate()));
        perTownTable.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
    }

    private Label setPerTownLabel() {
        perTownLabel = new Label(perTownTable.getId());
        perTownLabel.setStyle("-fx-font-size: 16pt;");
        return perTownLabel;
    }

    private void setPerTownData() {
        setPerTownTableData();
        perTownLabel.setText(perTownTable.getId());
    }

    @Override
    public void refresh() {
        setPerTownData();
    }

    @Override
    public void save() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(perTownTable);
    }
}
