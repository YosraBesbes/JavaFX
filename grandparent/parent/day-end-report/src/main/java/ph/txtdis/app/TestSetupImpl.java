package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.SystemUser;
import ph.txtdis.service.ChannelService;
import ph.txtdis.service.ItemFamilyService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.TruckService;
import ph.txtdis.service.UserService;
import ph.txtdis.service.WarehouseService;
import ph.txtdis.util.Login;

@Component
public class TestSetupImpl implements TestSetup {

    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;

    @Autowired
    ItemFamilyService familyService;

    @Autowired
    QualityService qualityService;

    @Autowired
    TruckService truckService;

    @Autowired
    WarehouseService warehouseService;

    public TestSetupImpl() {
    }

    @Override
    public void start() {
        SystemUser sysgen = userService.get("BUTCH");
        Login.setUser(sysgen);
    }
}
