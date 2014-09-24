package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.StockTakeReconciliationDTO;
import ph.txtdis.util.Login;

public class CurrentStockTakeClosureOptionDialog extends OptionDialog {
    private LocalDate date;

    public CurrentStockTakeClosureOptionDialog(Stage stage, LocalDate date) {
        super(stage, "Close this stock take?\nDoing so will prevent further data entry,\nbut allow reconciliation.");
        this.date = date;
        showAndWait();
    }

    @Override
    protected void handleAffirmation() {
        StockTakeReconciliationDTO recon = App.getContext().getBean(StockTakeReconciliationDTO.class);
        recon.get(date);
        recon.setClosedBy(Login.user());
        recon.setClosedOn(ZonedDateTime.now());
        recon.save();
        super.handleAffirmation();
    }
}
