package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.Users;
import ph.txtdis.type.UserType;

public interface UserDTO {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    List<Users> list();

    List<Users> list(UserType type);

    String[] getAddresses(UserType type);

    Users get(String email);

    Users getTxtDIS();
}