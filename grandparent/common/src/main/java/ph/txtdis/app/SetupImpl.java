package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Channel;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.Quality;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.model.Warehouse;
import ph.txtdis.service.ChannelService;
import ph.txtdis.service.ItemFamilyService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.TruckService;
import ph.txtdis.service.UserService;
import ph.txtdis.service.WarehouseService;
import ph.txtdis.type.ItemTier;
import ph.txtdis.type.UserType;
import ph.txtdis.util.Login;

@Component
public class SetupImpl implements Setup {

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

    public SetupImpl() {
    }

    @Override
    public void start() {
        SystemUser sysgen = userService.save(new SystemUser("SYSGEN", "I'mSysGen4txtDIS@PostgreSQL", true));
        Login.setUser(sysgen);

        SystemUser txtdis = new SystemUser("TXTDIS", "txtDIS@1", true);
        txtdis.setEmail("txtdis.mgdc.edsa.dmpi@gmail.com");
        userService.save(txtdis);

        SystemUser jackie = new SystemUser("JACKIE", "robbie", true);
        jackie.setEmail("manila12@gmail.com");
        jackie.setType(UserType.MANAGER);
        userService.save(jackie);

        SystemUser ronald = new SystemUser("RONALD", "alphacowboy", true);
        ronald.setEmail("ronaldallanso@yahoo.com");
        ronald.setType(UserType.MANAGER);
        userService.save(ronald);

        SystemUser butch = new SystemUser("BUTCH", "attila", true);
        butch.setEmail("butchlim888@yahoo.com");
        butch.setType(UserType.MANAGER);
        userService.save(butch);

        userService.save(new SystemUser("MICHELLE", "TWEETY", true));
        userService.save(new SystemUser("ANGIE", "Angelica Loteyro", true));
        userService.save(new SystemUser("MAY", "May Tuscano", true));
        userService.save(new SystemUser("MENNEN", "Mennen Timbal", true));
        userService.save(new SystemUser("MHON", "NOMAR", true));
        userService.save(new SystemUser("IRENE", "magnum08", true));

        userService.save(new SystemUser("OGIE", "dsp", true));
        userService.save(new SystemUser("PHILLIP", "dsp", true));
        userService.save(new SystemUser("BONG", "dsp", true));
        userService.save(new SystemUser("RANDY", "dsp", true));
        userService.save(new SystemUser("ROBERT", "dsp", true));
        userService.save(new SystemUser("HENRY", "dsp", true));
        userService.save(new SystemUser("ROLAND", "dsp", true));

        truckService.save(new Truck("RDM801"));
        truckService.save(new Truck("KDL170"));
        truckService.save(new Truck("WSN519"));

        userService.save(new SystemUser("LARRY", "dsp", true));
        userService.save(new SystemUser("VICENTE", "dsp", true));
        userService.save(new SystemUser("NOLI", "dsp", true));

        userService.save(new SystemUser("MARK", "dsp", true));
        userService.save(new SystemUser("MICHAEL", "dsp", true));
        userService.save(new SystemUser("TATA", "dsp", true));
        userService.save(new SystemUser("KEVIN", "dsp", true));
        userService.save(new SystemUser("JEFF", "dsp", true));
        userService.save(new SystemUser("RENE", "dsp", true));

        channelService.save(new Channel("GROCERY"));
        channelService.save(new Channel("SARI-SARI"));
        channelService.save(new Channel("WET MARKET"));
        channelService.save(new Channel("DRY MARKET"));
        channelService.save(new Channel("DRUG STORE"));
        channelService.save(new Channel("TFO"));

        familyService.save(new ItemFamily("CHEESE MAGIC", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("FNR", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("FRUITS", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("KETCHUP", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("MIX JUICES", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("ORANGE", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("OTHERS", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("PASTA", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("PASTA 175G", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("PASTE", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("PINE JUICE", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("Q&E MEAL MIXES", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("SOLIDS", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("SPAGHETTI SAUCE", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("TIDBITS 115G", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("TOMATO SAUCE", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("TS 90", ItemTier.CATEGORY));
        familyService.save(new ItemFamily("VINEGAR", ItemTier.CATEGORY));

        qualityService.save(new Quality("GOOD"));
        qualityService.save(new Quality("HOLD"));
        qualityService.save(new Quality("BAD"));

        warehouseService.save(new Warehouse("EDSA"));
    }
}
