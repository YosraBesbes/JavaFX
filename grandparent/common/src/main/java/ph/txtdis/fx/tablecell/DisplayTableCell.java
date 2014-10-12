package ph.txtdis.fx.tablecell;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class DisplayTableCell<E, T> extends TableCell<E, T> {
    private Pos pos;

    public DisplayTableCell(Pos pos) {
        this.pos = pos;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setTextValue(item);
    }

    private void setTextValue(T item) {
        setText(item == null ? "" : setString(item));
        setGraphic(null);
        setTextProperties();
    }

    protected String setString(T item) {
        return item.toString();
    }

    private void setTextProperties() {
        setAlignment(pos);
        if (pos == Pos.CENTER_RIGHT && getText().contains(">"))
            setStyle("-fx-text-fill: red");
    }
}
