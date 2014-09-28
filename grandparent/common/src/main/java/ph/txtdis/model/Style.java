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

    public Style(SystemUser systemUser, String base, String background, String accent, String font) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((accent == null) ? 0 : accent.hashCode());
        result = prime * result + ((background == null) ? 0 : background.hashCode());
        result = prime * result + ((base == null) ? 0 : base.hashCode());
        result = prime * result + ((font == null) ? 0 : font.hashCode());
        result = prime * result + ((systemUser == null) ? 0 : systemUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Style other = (Style) obj;
        if (accent == null) {
            if (other.accent != null)
                return false;
        } else if (!accent.equals(other.accent))
            return false;
        if (background == null) {
            if (other.background != null)
                return false;
        } else if (!background.equals(other.background))
            return false;
        if (base == null) {
            if (other.base != null)
                return false;
        } else if (!base.equals(other.base))
            return false;
        if (font == null) {
            if (other.font != null)
                return false;
        } else if (!font.equals(other.font))
            return false;
        if (systemUser == null) {
            if (other.systemUser != null)
                return false;
        } else if (!systemUser.equals(other.systemUser))
            return false;
        return true;
    }
}
