package ph.txtdis.fx.dialog;

import java.time.LocalDate;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.StockTakeReconciliationDTO;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.util.Login;

public class CutoffOptionDialog extends OptionDialog {

    private LocalDate date;

    public CutoffOptionDialog(Stage stage, LocalDate date) {
        super(stage, "From this point on, only stock taking is allowed,\n"
                + "until it's completed, other transactions cannot be posted.\nContinue?");
        this.date = date;
        showAndWait();
    }

    @Override
    protected void handleAffirmation() {
        StockTakeReconciliationDTO recon = App.getContext().getBean(StockTakeReconciliationDTO.class);
        recon.set(new StockTakeReconciliation(Login.user(), date));
        recon.save();
        super.handleAffirmation();
    }
}
