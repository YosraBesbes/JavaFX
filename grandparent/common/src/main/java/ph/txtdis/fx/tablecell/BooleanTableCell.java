package ph.txtdis.fx.tablecell;

import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;

public abstract class BooleanTableCell<S> extends CheckBoxTableCell<S, Boolean> {
    private final CheckBox checkBox;

    public BooleanTableCell() {
        checkBox = new CheckBox();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateItem(Boolean checked, boolean empty) {
        super.updateItem(checked, empty);
        if (checked != null) {
            checkBox.setSelected(checked);
            checkBox.selectedProperty().addListener((check, oldValue, newValue) -> {
                Object item = getTableRow().getItem();
                setBoolean((S) item, newValue);
            });
            setGraphic(checkBox);
            setStyle("-fx-alignment: CENTER;");
        }
    }

    protected abstract void setBoolean(S item, Boolean new_val);
}
