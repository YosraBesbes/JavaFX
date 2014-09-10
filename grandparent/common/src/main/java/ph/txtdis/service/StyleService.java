package ph.txtdis.service;

import ph.txtdis.model.Style;
import ph.txtdis.model.SystemUser;

public interface StyleService {

	public Style get(SystemUser user);

	public Style save(Style style);

	public void delete(Style style);
}
