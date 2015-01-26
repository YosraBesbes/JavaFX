package ph.txtdis.fx.tab;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.LoadSettlementDTO;
import ph.txtdis.dto.SummaryDTO;

public class LoadSettlementTab extends AbstractSettlementTab<LoadSettlementDTO> {

    public LoadSettlementTab(Stage stage, SummaryDTO dto) {
        super("Load Reconciliation", "load", stage, dto);
    }

    @Override
    protected Tabbed getPerTruckTab() {
        return new LoadSettlementPerTruckTab(truck, stage, settlement);
    }

    @Override
    protected void setSettlementDTO() {
        settlement = App.context().getBean(LoadSettlementDTO.class);
    }
}
