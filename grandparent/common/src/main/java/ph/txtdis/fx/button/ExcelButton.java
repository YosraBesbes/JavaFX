package ph.txtdis.fx.button;

import ph.txtdis.excel.Excel;

public class ExcelButton extends FontButton<Object> {

    public ExcelButton(Excel app) {
        super("\ue810", "Save as Excel...");
        button.setOnAction(event -> app.saveAsExcel());
    }
}
