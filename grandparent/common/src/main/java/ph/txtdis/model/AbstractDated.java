package ph.txtdis.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

@MappedSuperclass
public class AbstractDated implements Serializable {

    private static final long serialVersionUID = -8816070253252272183L;

    @Id
    @Generated(value = GenerationTime.INSERT)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate idDate;

    protected AbstractDated() {
    }

    public LocalDate getIdDate() {
        return idDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDate == null) ? 0 : idDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractDated other = (AbstractDated) obj;
        if (idDate == null) {
            if (other.idDate != null)
                return false;
        } else if (!idDate.equals(other.idDate))
            return false;
        return true;
    }
}