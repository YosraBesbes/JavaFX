package ph.txtdis.fx.tab;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.RemittanceSettlementDTO;
import ph.txtdis.dto.SummaryDTO;

public class RemittanceSettlementTab extends AbstractSettlementTab<RemittanceSettlementDTO> {

    public RemittanceSettlementTab(Stage stage, SummaryDTO dto) {
        super("Remittance Settlement", "remittance", stage, dto);
    }

    @Override
    protected Tabbed getPerTruckTab() {
        return new RemittanceSettlementPerTruckTab(truck, stage, settlement);
    }

    @Override
    protected void setSettlementDTO() {
        settlement = App.context().getBean(RemittanceSettlementDTO.class);
    }
}
