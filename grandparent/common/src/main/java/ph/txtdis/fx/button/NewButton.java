package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.ProgressDialog;

public class NewButton<E> extends FontButton<E> {

	public NewButton(Apped app, Audited<E> dto) {
		super("\ue800", "Add...");
		button.setOnAction(event -> {
			new ProgressDialog((Stage) app) {
				@Override
				protected void begin() {
					dto.reset();
				}

				@Override
				protected void next() {
					app.refresh();
					app.setFocus();
				}
			};
		});
	}
}
