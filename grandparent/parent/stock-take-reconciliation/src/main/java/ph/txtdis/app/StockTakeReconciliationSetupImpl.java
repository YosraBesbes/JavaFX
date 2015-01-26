package ph.txtdis.app;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.Users;
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

        LocalDate date = LocalDate.parse("2014-09-01");
        Users user = userService.get("BUTCH");
        reconService.save(new StockTakeReconciliation(user, date));
    }
}
