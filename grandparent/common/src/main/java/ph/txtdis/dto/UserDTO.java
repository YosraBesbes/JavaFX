package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.SystemUser;
import ph.txtdis.type.UserType;

public interface UserDTO {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    List<SystemUser> list();

    List<SystemUser> list(UserType type);

    String[] getAddresses(UserType type);

    SystemUser get(String email);

    SystemUser getTxtDIS();
}