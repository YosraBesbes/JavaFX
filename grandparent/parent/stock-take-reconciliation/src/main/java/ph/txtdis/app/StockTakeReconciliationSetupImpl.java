package ph.txtdis.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.service.PickingService;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.service.StockTakeService;

@Component
public class StockTakeReconciliationSetupImpl implements StockTakeReconciliationSetup {

    @Autowired
    StockTakeReconciliationService reconService;

    @Autowired
    ReceivingService receivingService;

    @Autowired
    PickingService pickingService;

    @Autowired
    StockTakeService stockTakeService;

    public StockTakeReconciliationSetupImpl() {
    }

    @Override
    public void start() {

        LocalDate date1 = LocalDate.parse("2014-09-01");
        LocalDate date2 = LocalDate.parse("2014-10-01");
        pickingService.getSummary(date1, date2).forEach(
                s -> System.err.println("Picking: " + s.getItem().getId() + ", " + s.getItem() + ", " + s.getQuality()
                        + ", " + s.getQty()));
        receivingService.getSummary(date1, date2).forEach(
                s -> System.err.println("Receiving: " + s.getItem().getId() + ", " + s.getItem() + ", "
                        + s.getQuality() + ", " + s.getQty()));
        stockTakeService.getSummary(date1).forEach(
                s -> System.err.println("Stock Take: " + s.getItem().getId() + ", " + s.getItem() + ", "
                        + s.getQuality() + ", " + s.getQty()));

        reconService.getItemSummary(date1, date2).forEach(
                s -> System.err.println("Item Log: " + s.getItem().getId() + ", " + s.getItem() + ", " + s.getQuality()
                        + ", " + s.getStartQty() + ", " + s.getInQty() + ", " + s.getOutQty() + ", " + s.getEndQty()));
    }
}
