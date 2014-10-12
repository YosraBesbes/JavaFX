package ph.txtdis.fx.tab;

import javafx.stage.Stage;
import ph.txtdis.dto.SummaryDTO;

public class SummaryTab extends AbstractSettlementTab<SummaryDTO> {

    public SummaryTab(Stage stage, SummaryDTO dto) {
        super("Summary per Truck", "summary", stage, dto);
    }

    @Override
    protected Tabbed getPerTruckTab() {
        return new SummaryPerTruckTab(truck, stage, dto);
    }

    @Override
    protected void setSettlementDTO() {
    }
}
