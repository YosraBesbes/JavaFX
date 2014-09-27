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
import ph.txtdis.util.Login;

@Component
public class InventorySetupImpl implements InventorySetup {

    @Autowired
    PickingService pickingService;

    @Autowired
    ReceivingService receivingService;

    @Autowired
    StockTakeReconciliationService reconService;

    @Autowired
    UserService userService;

    public InventorySetupImpl() {
    }

    @Override
    public void start() {

        LocalDate date1 = LocalDate.ofEpochDay(0);
        LocalDate date2 = LocalDate.parse("2014-09-01");
        SystemUser user = userService.get("BUTCH");
        Login.setUser(user);

        reconService.save(new StockTakeReconciliation(user, date2));
    }
}
