package ph.txtdis.fx.dialog;

import javafx.stage.Stage;

public class InfoDialog extends AbstractMessageDialog {

	public InfoDialog(Stage stage, String message) {
		super(stage, message, "\ue618", "lime");
	}
}
