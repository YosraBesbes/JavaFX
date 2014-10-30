package ph.txtdis.fx.tab;

import java.math.BigDecimal;
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
import ph.txtdis.dto.SummaryDTO;
import ph.txtdis.fx.display.CurrencyDisplay;
import ph.txtdis.fx.display.DecimalDisplay;
import ph.txtdis.fx.table.RemittanceTable;
import ph.txtdis.fx.table.RevenueTable;
import ph.txtdis.fx.table.VolumeTable;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.Truck;
import ph.txtdis.model.VolumeSummary;

public class SummaryPerTruckTab extends AbstractTab<SummaryDTO> implements MultiTabled {

    private DecimalDisplay totalVolumeDisplay;
    private CurrencyDisplay totalRevenueDisplay, totalRemittanceDisplay;
    private TableView<Invoicing> revenueTable;
    private TableView<Remittance> remittanceTable;
    private TableView<VolumeSummary> volumeTable;

    public SummaryPerTruckTab(Truck truck, Stage stage, SummaryDTO dto) {
        super(truck.getName(), truck.getName(), stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SummaryDTO dto) {
        HBox hBox = new HBox(setVolumeTableVBox(), setRevenueTableVBox(), setRemittanceTableVBox());
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return new Node[] { hBox };
    }

    private VBox setVolumeTableVBox() {
        volumeTable = new VolumeTable(stage).getTable();
        volumeTable.setItems(dto.getVolumeSummary(name));
        volumeTable.setUserData(new BigDecimal[] { dto.getTotalVolume() });
        totalVolumeDisplay = new DecimalDisplay(((BigDecimal[]) volumeTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total(CS)"), totalVolumeDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Volume"), volumeTable, hBox);
    }

    private VBox setRevenueTableVBox() {
        revenueTable = new RevenueTable(stage).getTable();
        revenueTable.setItems(dto.getInvoices(name));
        revenueTable.setUserData(new BigDecimal[] { dto.getTotalRevenue() });
        totalRevenueDisplay = new CurrencyDisplay(((BigDecimal[]) revenueTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total"), totalRevenueDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Revenue"), revenueTable, hBox);
    }

    private VBox setRemittanceTableVBox() {
        remittanceTable = new RemittanceTable(stage).getTable();
        remittanceTable.setItems(dto.getRemittances(name));
        remittanceTable.setUserData(new BigDecimal[] { dto.getTotalRemittance() });
        totalRemittanceDisplay = new CurrencyDisplay(((BigDecimal[]) remittanceTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total"), totalRemittanceDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Remittance"), remittanceTable, hBox);
    }

    private Label setLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 16pt;");
        return label;
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public void save() {
    }

    @Override
    public void refresh() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(volumeTable, revenueTable, remittanceTable);
    }
}
