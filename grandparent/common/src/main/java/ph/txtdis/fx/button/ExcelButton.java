package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.DTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.fx.dialog.ProgressDialog;

public class ExcelButton<E, K> extends FontButton<E> {

    public ExcelButton(Apped app, DTO<E, K> dto) {
        super("\ue810", "Save as Excel...");
        button.setOnAction(event -> {
            new ProgressDialog((Stage) app) {
                @Override
                protected void begin() {
                    ((Excel) app).saveAsExcel();
                }

                @Override
                protected void next() {
                }
            };
        });
    }
}
