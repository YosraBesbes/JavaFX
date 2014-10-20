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
import ph.txtdis.fx.table.ProductivityTable;
import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.util.Util;

public class ProductivityTab extends AbstractTab<SalesReportDTO> implements MultiTabled {

    private Label productivitylabel;
    private TableView<ProductivityPerFamilyByRoute> productivityTable;

    public ProductivityTab(Stage stage, SalesReportDTO dto) {
        super("Productivity", "productive", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, SalesReportDTO dto) {
        return new Node[] { new VBox(setProductivityTableHBox()) };
    }

    public HBox setProductivityTableHBox() {
        HBox hBox = new HBox(setProductivityTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setProductivityTableVBox() {
        setProductivityTable();
        return new VBox(setProductivityLabel(), productivityTable);
    }

    private TableView<ProductivityPerFamilyByRoute> setProductivityTable() {
        productivityTable = new ProductivityTable(stage, dto.getRouteNames()).getTable();
        setProductivityTableData();
        return productivityTable;
    }

    private void setProductivityTableData() {
        productivityTable.setItems(dto.getProductivity(dto.getStartDate(), dto.getEndDate()));
        productivityTable.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
    }

    private Label setProductivityLabel() {
        productivitylabel = new Label(productivityTable.getId());
        productivitylabel.setStyle("-fx-font-size: 16pt;");
        return productivitylabel;
    }

    private void setProductivityData() {
        setProductivityTableData();
        productivitylabel.setText(productivityTable.getId());
    }

    @Override
    public void refresh() {
        setProductivityData();
    }

    @Override
    public void save() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(productivityTable);
    }
}
