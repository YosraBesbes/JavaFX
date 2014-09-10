package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.SystemUser;

public interface UserDTO {

	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	boolean isEnabled();

	void setEnabled(boolean enabled);
	
	List<SystemUser> list();
}