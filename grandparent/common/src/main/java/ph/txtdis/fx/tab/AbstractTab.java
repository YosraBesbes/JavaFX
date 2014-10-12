package ph.txtdis.fx.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public abstract class AbstractTab<D> implements Tabbed {
    private final Tab tab;
    protected final String name;
    protected final Stage stage;
    protected D dto;

    public AbstractTab(String name, String id, Stage stage, D dto) {
        tab = new Tab(name);
        tab.setId(id);
        this.name = name;
        this.stage = stage;
        setDTO(dto);
        tab.setContent(addTabContent(stage));
    }

    protected void setDTO(D dto) {
        this.dto = dto;
    }

    protected Node addTabContent(Stage stage) {
        HBox box = new HBox();
        box.getChildren().addAll(addNodes(stage, dto));
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);
        return box;
    }

    protected Node[] addNodes(Stage stage, D dto) {
        return new Node[] {};
    };

    @Override
    public Tab getTab() {
        return tab;
    }
}
