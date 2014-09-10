package ph.txtdis.fx.tablecell;

import java.math.BigDecimal;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ph.txtdis.fx.dialog.Tabled;

public abstract class TabledPriceFieldTableCell<E, I> extends PriceFieldTableCell<E> {

    @SuppressWarnings("unchecked")
    public <T> TabledPriceFieldTableCell(Stage stage) {
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() > 1) {
                TableCell<E, BigDecimal> tableCell = (TableCell<E, BigDecimal>) event.getSource();
                TableRow<E> row = tableCell.getTableRow();
                TableView<E> table = row.getTableView();
                int index = row.getIndex();
                E entity = row.getItem();

                Tabled<I, E> dialog = addDialog(stage, entity);
                ((Stage) dialog).showAndWait();
                entity = addItems(entity, dialog);
                table.getItems().set(index, entity);
            }
        });

        contentDisplayProperty().bind(
                Bindings.when(editingProperty()).then(ContentDisplay.TEXT_ONLY).otherwise(ContentDisplay.TEXT_ONLY));
    }

    protected abstract E addItems(E entity, Tabled<I, E> dialog);

    protected abstract Tabled<I, E> addDialog(Stage stage, E item);
}
