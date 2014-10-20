package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.ChannelAppImpl;

public class ChannelButton extends FontButton<Object> {

    public <C> ChannelButton(Stage stage) {
        super("\ue808", "Channel Master", 44);
        button.setOnAction(event -> new ChannelAppImpl().start());
    }
}
