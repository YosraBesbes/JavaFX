package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class AbstractTableDialog<E, D> extends AbstractInputDialog<D> implements Tabled<E, D> {
    protected TableView<E> table;

    public AbstractTableDialog(String name, Stage stage, D entity) {
        super(name + " List", stage, entity);
    }

    @Override
    protected void populateGrid(GridPane gridPane, D dto) {
        gridPane.add(addTable(dto), 0, 0);
    }

    @Override
    protected Button[] createButtons(D dto) {
        return new Button[] { createCloseButton() };
    }

    private TableView<E> addTable(D dto) {
        table = createTable(dto);
        return addTableItems(table, dto);
    }

    protected abstract TableView<E> createTable(D dto);

    protected abstract TableView<E> addTableItems(TableView<E> table, D dto);
}
