package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.InventoryDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.table.AbstractTable;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.QtyDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.model.Inventory;
import ph.txtdis.util.Util;

public class InventoryAppImpl extends AbstractApp<Inventory, Integer> implements Excel {

    private TableView<Inventory> table;
    private InventoryDTO dto;

    public InventoryAppImpl() {
        super("Inventory", "Date");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(InventoryDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
        buttons.put("excel", new ExcelButton<Inventory>(this).getButton());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Node[] addVBoxNodes() {
        table = new AbstractTable<Inventory>(this) {
            {
                TableColumn<Inventory, Integer> itemIdCol = new IdDisplayColumn<Inventory>(stage, "Item No.", "itemId",
                        80);
                TableColumn<Inventory, String> itemNameCol = new TextDisplayColumn<>(stage, "Name", "item", 180,
                        Pos.CENTER_LEFT);
                TableColumn<Inventory, String> qualityCol = new TextDisplayColumn<>(stage, "Quality", "qualityType",
                        70, Pos.CENTER);
                TableColumn<Inventory, BigDecimal> beginCol = new QtyDisplayColumn<>(stage, "Beginning "
                        + dto.getStartDateText(), "beginQty", 90);
                TableColumn<Inventory, BigDecimal> inCol = new QtyDisplayColumn<>(stage, "In", "inQty", 80);
                TableColumn<Inventory, BigDecimal> outCol = new QtyDisplayColumn<>(stage, "Out", "outQty", 80);
                TableColumn<Inventory, BigDecimal> endCol = new QtyDisplayColumn<>(stage, "Current "
                        + dto.getEndDateText(), "endQty", 90);
                TableColumn<Inventory, String> daysLevelCol = new TextDisplayColumn<>(stage, "Days Level", "daysLevel",
                        80, Pos.CENTER_RIGHT);
                table.getColumns().addAll(itemIdCol, itemNameCol, qualityCol, beginCol, inCol, outCol, endCol,
                        daysLevelCol);
                table.setItems(dto.getInventoryList());
            }
        }.getTable();

        HBox box = new HBox(table);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        return new Node[] { box };
    }

    @Override
    public void setFocus() {
        table.requestFocus();
    }

    @Override
    public void save() {
    }

    @Override
    protected void setBindings() {
    }

    @Override
    protected void addFooter(VBox box) {
    }

    @Override
    public void saveAsExcel() {
        new ExcelWriter<Inventory>(table, module, Util.formatDate(LocalDate.now()));
    }
}
