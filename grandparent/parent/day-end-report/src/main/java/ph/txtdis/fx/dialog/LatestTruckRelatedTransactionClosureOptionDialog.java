package ph.txtdis.fx.dialog;

import java.time.LocalDate;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.LoadSettlementDTO;
import ph.txtdis.dto.PickingDTO;
import ph.txtdis.dto.RemittanceSettlementDTO;
import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.Truck;

public class LatestTruckRelatedTransactionClosureOptionDialog extends OptionDialog {
    private LoadSettlementDTO load;
    private RemittanceSettlementDTO remittance;
    private LocalDate date;

    public LatestTruckRelatedTransactionClosureOptionDialog(Stage stage, LocalDate date) {
        super(stage, "Current truck-related transactions must be closed first,\n"
                + "enabling settlement, but allowing further data entry.\nContinue?");
        this.date = date;
        showAndWait();
    }

    @Override
    protected void handleAffirmation() {
        setDTOs();
        closeAllTruckRelatedTransactions(getTrucks());
        super.handleAffirmation();
    }

    private void setDTOs() {
        load = App.getContext().getBean(LoadSettlementDTO.class);
        remittance = App.getContext().getBean(RemittanceSettlementDTO.class);
    }

    private void closeAllTruckRelatedTransactions(List<Truck> trucks) {
        for (Truck truck : trucks)
            updateDTOs(truck);
    }

    private void updateDTOs(Truck truck) {
        load.setById(getLoadId(truck));
        remittance.setById(getRemitId(truck));
    }

    private List<Truck> getTrucks() {
        PickingDTO picking = App.getContext().getBean(PickingDTO.class);
        List<Truck> trucks = picking.getLoadedTrucks(date);
        return trucks;
    }

    private Integer getLoadId(Truck truck) {
        return saveLoad(truck).getId();
    }

    private LoadSettlement saveLoad(Truck truck) {
        return load.save(new LoadSettlement(truck));
    }

    private Integer getRemitId(Truck truck) {
        return saveRemit(truck).getId();
    }

    private RemittanceSettlement saveRemit(Truck truck) {
        return remittance.save(new RemittanceSettlement(truck));
    }
}
