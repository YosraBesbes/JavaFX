package ph.txtdis.fx.tablecell;

import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ph.txtdis.app.Referenced;
import ph.txtdis.dto.AuditedDTO;

public class DoubleClickTableCell<E, T> extends TableCell<E, T> {

    public DoubleClickTableCell(Stage stage, AuditedDTO<E> dto) {
        super();
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() > 1)
                handleDoubleClick(stage, dto);
        });
    }

    public DoubleClickTableCell(Stage stage) {
        super();
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() > 1)
                handleDoubleClick(stage);
        });
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? null : getItem().toString());
        setGraphic(null);
    }

    private void handleDoubleClick(Stage stage, AuditedDTO<E> dto) {
        dto.set(getEntity());
        stage.close();
    }

    @SuppressWarnings("unchecked")
    private void handleDoubleClick(Stage stage) {
        ((Referenced<E>) stage).listFoundReferences(getEntity());
    }

    @SuppressWarnings("unchecked")
    private E getEntity() {
        return (E) getTableRow().getItem();
    }
}
