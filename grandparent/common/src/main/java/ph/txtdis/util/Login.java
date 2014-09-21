package ph.txtdis.util;

import ph.txtdis.model.SystemUser;
import ph.txtdis.service.UserService;

public class Login {
    private static SystemUser user;

    public static SystemUser validate(UserService service, String username, String password) {
        user = service.get(username, password);
        return user;
    }

    public static SystemUser user() {
        return user;
    }

    public static void setUser(SystemUser user) {
        Login.user = user;
    }

    public static String getVersion() {
        return "txtDIS 0.9";
    }
}
