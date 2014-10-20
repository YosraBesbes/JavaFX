package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.WarehouseDTO;
import ph.txtdis.fx.table.WarehouseTable;
import ph.txtdis.model.Warehouse;

public class WarehouseAppImpl extends AbstractApp<Warehouse, Integer> {

    private TableView<Warehouse> table;
    private WarehouseDTO warehouse;

    public WarehouseAppImpl() {
        super("Warehouse Master", "");
    }

    @Override
    protected void setDTO() {
        dto = warehouse = App.getContext().getBean(WarehouseDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new WarehouseTable(this, warehouse).getTable();
        table.setItems(warehouse.list());
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
}
