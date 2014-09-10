package ph.txtdis.dto;

import java.util.List;

import javafx.collections.FXCollections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.SystemUser;
import ph.txtdis.service.UserService;

@Component
public class UserDTOImpl implements UserDTO {

    @Autowired
    private UserService service;
	private SystemUser user;

	public UserDTOImpl() {
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public void setUsername(String username) {
		user.setUsername(username);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public void setPassword(String password) {
		user.setPassword(password);
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		user.setEnabled(enabled);
	}

    @Override
    public List<SystemUser> list() {
        return FXCollections.observableList(service.list());
    }
}
