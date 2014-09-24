package ph.txtdis.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.PickingService;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.service.UserService;

@Component
public class StockTakeReconciliationSetupImpl implements StockTakeReconciliationSetup {

    @Autowired
    PickingService pickingService;

    @Autowired
    ReceivingService receivingService;

    @Autowired
    StockTakeReconciliationService reconService;

    @Autowired
    UserService userService;

    public StockTakeReconciliationSetupImpl() {
    }

    @Override
    public void start() {

        LocalDate date1 = LocalDate.ofEpochDay(0);
        LocalDate date2 = LocalDate.parse("2014-09-01");
        SystemUser user = userService.get("SYSGEN");

        reconService.save(new StockTakeReconciliation(user, date2));

        pickingService.getSummary(date1, date2).forEach(
                s -> System.err.println("Picking: " + s.getItem().getId() + ", " + s.getItem() + ", " + s.getQuality()
                        + ", " + s.getQty()));
        receivingService.getSummary(date1, date2).forEach(
                s -> System.err.println("Receiving: " + s.getItem().getId() + ", " + s.getItem() + ", "
                        + s.getQuality() + ", " + s.getQty()));

        reconService.getDetail(date1, date2).forEach(
                s -> System.err.println("Item Log: " + s.getItem().getId() + ", " + s.getItem() + ", " + s.getQuality()
                        + ", " + s.getStartQty() + ", " + s.getStartAdjustQty() + ", " + s.getInQty() + ", "
                        + s.getOutQty() + ", " + s.getCountQty() + ", " + s.getAdjustmentQty() + ", "
                        + s.getJustification()));
    }
}
