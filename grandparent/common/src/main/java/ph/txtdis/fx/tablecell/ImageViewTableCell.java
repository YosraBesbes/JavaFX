package ph.txtdis.fx.tablecell;

import ph.txtdis.fx.util.FX;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImageViewTableCell<T> extends TextFieldTableCell<T, Byte[]> {

	public ImageViewTableCell(Stage stage) {
		addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getClickCount() > 1)
				commitEdit(FX.loadBytes(stage, "image", "id"));
			event.consume();
		});

		contentDisplayProperty().bind(
				Bindings.when(editingProperty())
						.then(ContentDisplay.GRAPHIC_ONLY)
						.otherwise(ContentDisplay.GRAPHIC_ONLY));
	}

	@Override
	public void updateItem(Byte[] bytes, boolean empty) {
		super.updateItem(bytes, empty);
		setGraphic(bytes == null ? null : FX.toImageView(bytes));
	}
}
