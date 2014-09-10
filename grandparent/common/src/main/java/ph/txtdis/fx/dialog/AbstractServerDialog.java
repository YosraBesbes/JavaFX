package ph.txtdis.fx.dialog;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import ph.txtdis.fx.util.FX;
import ph.txtdis.service.UserService;
import ph.txtdis.type.HostType;
import ph.txtdis.type.ServerType;

public abstract class AbstractServerDialog extends AbstractInputDialog<Object> {
    protected ComboBox<ServerType> serverCombo;
    protected ComboBox<HostType> hostCombo;

    public AbstractServerDialog(String name, UserService service) {
        super(name, null, service);
        FX.decorateWindow(this);        
        initStyle(StageStyle.DECORATED);
    }

    @Override
    protected void populateGrid(GridPane gridPane, Object object) {
        Label databaseLabel = createLabel("Database");
        serverCombo = FX.createComboBox(ServerType.values(), ServerType.DM);
        Label hostLabel = createLabel("Connection");
        hostCombo = FX.createComboBox(HostType.values(), HostType.PC);
        gridPane.add(databaseLabel, 0, 1);
        gridPane.add(serverCombo, 1, 1);
        gridPane.add(hostLabel, 0, 2);
        gridPane.add(hostCombo, 1, 2);
    }

    protected Label createLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 11pt; ");
        return label;
    }

    @Override
    protected Button[] createButtons(Object object) {
        return new Button[] { createCloseButton("OK") };
    }

    @Override
    protected void process() {
        new ErrorDialog(this, "You're not authorized\nto change server");
    }
}