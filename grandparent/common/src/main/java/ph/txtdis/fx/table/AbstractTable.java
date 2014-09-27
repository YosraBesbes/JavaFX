package ph.txtdis.fx.table;

import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AbstractTable<E> {

    protected TableView<E> table;
    protected Stage stage;

    public AbstractTable() {
        table = new TableView<>();
    }

    public AbstractTable(Stage stage) {
        this();
        this.stage = stage;
    }

    public TableView<E> getTable() {
        return table;
    }
}