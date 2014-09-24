package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.app.Searched;
import ph.txtdis.dto.Audited;
import ph.txtdis.dto.SearchedDTO;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.fx.dialog.SearchDialog;

public class SearchByTextButton<E> extends FontButton<E> {

	@SuppressWarnings("unchecked")
	public SearchByTextButton(Apped app, Audited<E> dto) {
		super("\ue824", "Find...");
		button.setOnAction(event -> {
			String name = new SearchDialog(app).getText();
			if (name == null)
				return;
			new ProgressDialog((Stage) app) {
				@Override
				protected void begin() {
					((SearchedDTO<E, String>) dto).findAll(name);
				}

				@Override
				protected void next() {
					((Searched) app).listFoundEntities();
					app.refresh();
					app.setFocus();
				}
			};
		});
	}
}
