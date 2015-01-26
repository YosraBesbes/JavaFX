package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Users;
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
        Users dsp1 = userService.get("MICHAEL");
        Users dsp2 = userService.get("PHILLIP");

        bookletService.save(new InvoiceBooklet(1, 50, dsp1));

        bookletService.save(new InvoiceBooklet(51, 100, dsp2));
    }
}
