package ph.txtdis.fx.tablecell;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ph.txtdis.app.Referenced;
import ph.txtdis.dto.Audited;

public class DoubleClickTableCell<E, T> extends TableCell<E, T> {
    private Pos pos;

    public DoubleClickTableCell(Stage stage, Audited<E> dto) {
        super();
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() > 1)
                handleDoubleClick(stage, dto);
        });
    }

    public DoubleClickTableCell(Stage stage, Pos pos) {
        super();
        this.pos = pos;
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() > 1)
                handleDoubleClick(stage);
        });
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setTextValue(item);
        setTextProperties();
    }

    private void setTextValue(T item) {
        setText(item == null ? "" : getItem().toString());
        setGraphic(null);
    }

    private void setTextProperties() {
        setAlignment(pos);
        if (pos == Pos.CENTER_RIGHT && getText().contains(">"))
            setStyle("-fx-text-fill: red");
    }

    private void handleDoubleClick(Stage stage, Audited<E> dto) {
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
