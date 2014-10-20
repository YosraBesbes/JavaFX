package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.ItemTreeDTO;
import ph.txtdis.fx.table.ItemTreeTable;
import ph.txtdis.model.ItemTree;

public class ItemTreeAppImpl extends AbstractApp<ItemTree, Integer> {

    private TableView<ItemTree> table;
    private ItemTreeDTO itemTree;

    public ItemTreeAppImpl() {
        super("Item Family Tree", "");
    }

    @Override
    protected void setDTO() {
        dto = itemTree = App.getContext().getBean(ItemTreeDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new ItemTreeTable(this, itemTree).getTable();
        table.setItems(itemTree.list());
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
