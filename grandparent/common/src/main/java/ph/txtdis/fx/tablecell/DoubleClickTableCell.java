package ph.txtdis.fx.tablecell;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ph.txtdis.dto.DTO;

public class DoubleClickTableCell<S, T> extends TableCell<S, T> {

	public DoubleClickTableCell(Stage stage, DTO<S> dto) {

		addEventFilter(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@SuppressWarnings("unchecked")
					@Override
					public void handle(MouseEvent event) {
						if (event.getClickCount() > 1) {
							dto.set((S) getTableRow().getItem());
							stage.close();
						}
					}
				});
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty ? null : getString());
		setGraphic(null);
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}
