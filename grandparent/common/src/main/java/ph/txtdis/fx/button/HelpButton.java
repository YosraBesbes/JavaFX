package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.HelpDialog;

public class HelpButton<E> extends FontButton<E> {
	private static String help;

	public HelpButton(Stage stage) {
		super("\ue627", help = "Help");
		button.setOnAction(event -> new HelpDialog(stage, help.toLowerCase()));
	}
}
