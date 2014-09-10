package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Style extends AbstractAudited {

	private static final long serialVersionUID = -8394808921625740006L;

	@OneToOne(optional = false, cascade = CascadeType.REFRESH)
	private SystemUser systemUser;
	
	private String base, background, accent, font;

	public Style() {
	}

	public Style(SystemUser systemUser, String base, String background, String accent,
			String font) {
		this.systemUser = systemUser;
		this.base = base;
		this.background = background;
		this.accent = accent;
		this.font = font;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser user) {
		this.systemUser = user;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getAccent() {
		return accent;
	}

	public void setAccent(String accent) {
		this.accent = accent;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}
}
