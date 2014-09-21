package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.app.StockTakeReconciliationAppImpl;
import ph.txtdis.dto.StockTakeReconciliationDTO;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class LatestStockTakeClosureOptionDialog extends ErrorOptionDialog {

    public LatestStockTakeClosureOptionDialog(Stage stage, LocalDate date) {
        super(stage, "Latest stock take dated " + Util.formatDate(date)
                + "\nmust be closed to complete data entry.\nContinue?");
    }

    @Override
    protected void handleAffirmation() {
        StockTakeReconciliationDTO recon = App.getContext().getBean(StockTakeReconciliationDTO.class);
        recon.get(((StockTakeReconciliationAppImpl) stage).getStockTakeDate());
        recon.setClosedBy(Login.user());
        recon.setClosedOn(ZonedDateTime.now());
        recon.save();
        super.handleAffirmation();
    }
}
