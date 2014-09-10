package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Printable;
import ph.txtdis.dto.DTO;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class PrintButton<E> extends FontButton<E> {

    public PrintButton(Printable app, DTO<E> dto) {

        super("\ue81c", "Print...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    app.print();
                }

                @Override
                protected void next() {
                    new InfoDialog((Stage) app, "Successfully printed data of\n" + Util.getEntityIdAndName(app, dto));
                }
            };
        });
    }
}
