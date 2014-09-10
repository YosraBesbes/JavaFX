package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.InvoiceBookletService;
import ph.txtdis.service.UserService;

@Component
public class InvoiceBookletSetupImpl implements InvoiceBookletSetup {

    @Autowired
    UserService userService;

    @Autowired
    InvoiceBookletService bookletService;

    public InvoiceBookletSetupImpl() {
    }

    @Override
    public void start() {
        SystemUser michelle = userService.get("MICHELLE");
        SystemUser dsp1 = userService.get("MICHAEL");
        SystemUser dsp2 = userService.get("PHILLIP");
        
        InvoiceBooklet ib1 = new InvoiceBooklet(1, 50, dsp1);
        ib1.setCreatedBy(michelle);
        bookletService.save(ib1);
        
        InvoiceBooklet ib2 = new InvoiceBooklet(51, 100, dsp2);
        ib2.setCreatedBy(michelle);
        bookletService.save(ib2);
    }
}
