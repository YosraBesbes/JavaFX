package ph.txtdis.fx.button;

import ph.txtdis.app.Apped;
import ph.txtdis.excel.Excel;

public class ExcelButton<E> extends FontButton<E> {

    public ExcelButton(Apped app) {
        super("\ue810", "Save as Excel...");
        button.setOnAction(event -> {
            // new ProgressDialog((Stage) app) {
            // @Override
            // protected void begin() {
            ((Excel) app).saveAsExcel();
            // }

            // @Override
            // protected void next() {
            // }
            // };
        });
    }
}
