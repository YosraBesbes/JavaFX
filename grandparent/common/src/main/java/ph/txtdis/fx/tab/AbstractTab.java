package ph.txtdis.fx.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public abstract class AbstractTab<E, D> implements Tabled {
	private final Tab tab;
	protected D dto;

	public AbstractTab(String name, Stage stage, D dto) {
		tab = new Tab(name);
		this.dto = dto;

		HBox box = new HBox();
		box.getChildren().addAll(addNodes(stage, dto));
		box.setSpacing(10);
		box.setPadding(new Insets(5));
		box.setAlignment(Pos.CENTER);

		tab.setContent(box);
	}

	protected abstract Node[] addNodes(Stage stage, D dto);

	@Override
	public Tab getTab() {
		return tab;
	}
}
