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
import ph.txtdis.fx.table.PerChannelTable;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.util.Util;

public class PerChannelTab extends AbstractTab<SalesReportDTO> implements MultiTabled {

    private Label perChannelLabel;
    private TableView<VolumePerChannelByRoute> perChannelTable;

    public PerChannelTab(Stage stage, SalesReportDTO dto) {
        super("Volume per Channel", "channel", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SalesReportDTO dto) {
        return new Node[] { new VBox(setPerChannelTableHBox()) };
    }

    public HBox setPerChannelTableHBox() {
        HBox hBox = new HBox(setPerChannelTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setPerChannelTableVBox() {
        setPerChannelTable();
        return new VBox(setPerChannelLabel(), perChannelTable);
    }

    private TableView<VolumePerChannelByRoute> setPerChannelTable() {
        perChannelTable = new PerChannelTable(stage, dto.getRouteNames()).getTable();
        setPerChannelTableData();
        return perChannelTable;
    }

    private void setPerChannelTableData() {
        perChannelTable.setItems(dto.getPerChannel(dto.getStartDate(), dto.getEndDate()));
        perChannelTable.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
    }

    private Label setPerChannelLabel() {
        perChannelLabel = new Label(perChannelTable.getId());
        perChannelLabel.setStyle("-fx-font-size: 16pt;");
        return perChannelLabel;
    }

    private void setPerChannelData() {
        setPerChannelTableData();
        perChannelLabel.setText(perChannelTable.getId());
    }

    @Override
    public void refresh() {
        setPerChannelData();
    }

    @Override
    public void save() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(perChannelTable);
    }
}
