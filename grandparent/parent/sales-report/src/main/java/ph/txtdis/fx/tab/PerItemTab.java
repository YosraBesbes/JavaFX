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
import ph.txtdis.fx.table.PerItemTable;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.util.Util;

public class PerItemTab extends AbstractTab<SalesReportDTO> implements MultiTabled {

    private Label perItemLabel;
    private TableView<VolumeSummary> perItemTable;

    public PerItemTab(Stage stage, SalesReportDTO dto) {
        super("Amount per Item", "item", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SalesReportDTO dto) {
        return new Node[] { new VBox(setPerItemTableHBox()) };
    }

    public HBox setPerItemTableHBox() {
        HBox hBox = new HBox(setPerItemTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setPerItemTableVBox() {
        setPerItemTable();
        return new VBox(setPerItemLabel(), perItemTable);
    }

    private TableView<VolumeSummary> setPerItemTable() {
        perItemTable = new PerItemTable(stage).getTable();
        setPerItemTableData();
        return perItemTable;
    }

    private void setPerItemTableData() {
        perItemTable.setItems(dto.getPerItem(dto.getStartDate(), dto.getEndDate()));
        perItemTable.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
    }

    private Label setPerItemLabel() {
        perItemLabel = new Label(perItemTable.getId());
        perItemLabel.setStyle("-fx-font-size: 16pt;");
        return perItemLabel;
    }

    private void setPerItemData() {
        setPerItemTableData();
        perItemLabel.setText(perItemTable.getId());
    }

    @Override
    public void refresh() {
        setPerItemData();
    }

    @Override
    public void save() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(perItemTable);
    }
}
