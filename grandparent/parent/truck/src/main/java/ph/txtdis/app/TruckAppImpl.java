package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.TruckDTO;
import ph.txtdis.fx.table.TruckTable;
import ph.txtdis.model.Truck;

public class TruckAppImpl extends AbstractApp<Truck, Integer> {

    private TableView<Truck> table;
    private TruckDTO truck;

    public TruckAppImpl() {
        super("Truck Master", "");
    }

    @Override
    protected void setDTO() {
        dto = truck = App.getContext().getBean(TruckDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new TruckTable(this, truck).getTable();
        table.setItems(truck.list());
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
