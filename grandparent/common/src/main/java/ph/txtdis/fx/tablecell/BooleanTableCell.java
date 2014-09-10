package ph.txtdis.fx.tablecell;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.CheckBoxTableCell;

public abstract class BooleanTableCell<S> extends CheckBoxTableCell<S, Boolean> {
	private final CheckBox checkBox;

	public BooleanTableCell() {
		checkBox = new CheckBox();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateItem(Boolean isTrue, boolean empty) {
		super.updateItem(isTrue, empty);
		if (isTrue != null) {
			checkBox.setSelected(isTrue);
			checkBox.selectedProperty().addListener(
					(ObservableValue<? extends Boolean> ov, Boolean old_val,
							Boolean new_val) -> {
						Object item = getTableRow().getItem();
						setBoolean((S) item, new_val);
					});
			setGraphic(checkBox);
			setStyle("-fx-alignment: CENTER;");
		}
	}

	protected abstract void setBoolean(S item, Boolean new_val);
}
