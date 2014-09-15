package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Channel;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.Quality;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.ChannelService;
import ph.txtdis.service.ItemFamilyService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.ItemTier;

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

    public SetupImpl() {
    }

    @Override
    public void start() {
        userService.save(new SystemUser("SYSGEN", "I'mSysGen4txtDIS@PostgreSQL", true));
        userService.save(new SystemUser("JACKIE", "robbie", true));
        userService.save(new SystemUser("RONALD", "alphacowboy", true));
        userService.save(new SystemUser("BUTCH", "attila", true));
        userService.save(new SystemUser("MICHELLE", "TWEETY", true));
        userService.save(new SystemUser("ANGIE", "Angelica Loteyro", true));
        userService.save(new SystemUser("MAY", "May Tuscano", true));
        userService.save(new SystemUser("MENNEN", "Mennen Timbal", true));
        userService.save(new SystemUser("MHON", "NOMAR", true));
        userService.save(new SystemUser("IRENE", "magnum08", true));
        userService.save(new SystemUser("MICHAEL", "dsp", true));
        userService.save(new SystemUser("PHILLIP", "dsp", true));
        userService.save(new SystemUser("BONG", "dsp", true));
        userService.save(new SystemUser("JEROME", "dsp", true));
        userService.save(new SystemUser("ROBERT", "dsp", true));
        userService.save(new SystemUser("HENRY", "dsp", true));
        userService.save(new SystemUser("ROLAND", "dsp", true));

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
    }
}
