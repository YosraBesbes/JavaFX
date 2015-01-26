package ph.txtdis.service;

import ph.txtdis.model.Style;
import ph.txtdis.model.Users;

public interface StyleService {

	public Style get(Users user);

	public Style save(Style style);

	public void delete(Style style);
}
