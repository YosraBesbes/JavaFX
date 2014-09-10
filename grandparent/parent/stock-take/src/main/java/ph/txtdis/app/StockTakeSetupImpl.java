package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Warehouse;
import ph.txtdis.service.UserService;
import ph.txtdis.service.WarehouseService;

@Component
public class StockTakeSetupImpl implements StockTakeSetup {

    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    public StockTakeSetupImpl() {
    }

    @Override
    public void start() {
        
        SystemUser sysgen = userService.get("SYSGEN");
        
        Warehouse edsa = new Warehouse("EDSA");
        edsa.setCreatedBy(sysgen);
        warehouseService.save(edsa);
    }
}
