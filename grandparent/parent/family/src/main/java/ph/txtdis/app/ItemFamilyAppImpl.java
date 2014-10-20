package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.fx.table.ItemFamilyTable;
import ph.txtdis.model.ItemFamily;

public class ItemFamilyAppImpl extends AbstractApp<ItemFamily, Integer> {

    private TableView<ItemFamily> table;
    private ItemFamilyDTO itemFamily;

    public ItemFamilyAppImpl() {
        super("Item Family Master", "");
    }

    @Override
    protected void setDTO() {
        dto = itemFamily = App.getContext().getBean(ItemFamilyDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new ItemFamilyTable(this, itemFamily).getTable();
        table.setItems(itemFamily.list());
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
