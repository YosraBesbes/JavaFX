package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.InvoiceBookletService;
import ph.txtdis.service.UserService;
import ph.txtdis.util.Login;

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
        Login.setUser(userService.get("MICHELLE"));
        SystemUser dsp1 = userService.get("MICHAEL");
        SystemUser dsp2 = userService.get("PHILLIP");

        bookletService.save(new InvoiceBooklet(1, 50, dsp1));

        bookletService.save(new InvoiceBooklet(51, 100, dsp2));
    }
}
