package ph.txtdis.fx.table;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AbstractBoxedTable<E, D> extends AbstractTable<E, D> {
	final private VBox box;

	public AbstractBoxedTable(String name, Stage stage, D dto) {
		super(stage, dto);
		Label label = new Label(name);
		label.setStyle("-fx-font-size: 16pt;");
		box = new VBox(label, table);
	}

	public VBox getBox() {
		return box;
	}
}
