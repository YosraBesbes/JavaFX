package ph.txtdis.fx.dialog;

import ph.txtdis.fx.util.FX;
import javafx.stage.Stage;

public class ErrorDialog extends AbstractMessageDialog {

	public ErrorDialog(Stage stage, String message) {
		super(stage, message, "\ue61e", "maroon");
		FX.decorateWindow(this);
	}
}

