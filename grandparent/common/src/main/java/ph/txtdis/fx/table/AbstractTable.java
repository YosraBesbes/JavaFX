package ph.txtdis.fx.table;

import javafx.scene.control.TableView;

public class AbstractTable<E> {
    
    protected final TableView<E> table;

    public AbstractTable() {
        table = new TableView<>();
    }

    public TableView<E> getTable() {
        return table;
    }
}