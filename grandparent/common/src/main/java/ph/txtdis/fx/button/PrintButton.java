package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Printed;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.ProgressDialog;
import ph.txtdis.util.Util;

public class PrintButton<E> extends FontButton<E> {

    public PrintButton(Printed app, Audited<E> dto) {
        super("\ue81c", "Print...");
        button.setOnAction(event -> {

            new ProgressDialog((Stage) app) {
                private Exception e;

                @Override
                protected void begin() {
                    try {
                        app.print();
                    } catch (Exception e) {
                        this.e = e;
                    }
                }

                @Override
                protected void next() {
                    if (e == null) {
                        app.refresh();
                        new InfoDialog((Stage) app, "Successfully printed data of\n"
                                + Util.getEntityIdAndName(app, dto));
                    } else
                        new ErrorDialog(stage, e.getMessage());
                }
            };
        });
    }
}
