package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.RouteDTO;
import ph.txtdis.fx.table.RouteTable;
import ph.txtdis.model.Route;

public class RouteAppImpl extends AbstractApp<Route, Integer> {

    private TableView<Route> table;
    private RouteDTO route;

    public RouteAppImpl() {
        super("Route Master", "");
    }

    @Override
    protected void setDTO() {
        dto = route = App.getContext().getBean(RouteDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new RouteTable(this, route).getTable();
        table.setItems(route.list());
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
