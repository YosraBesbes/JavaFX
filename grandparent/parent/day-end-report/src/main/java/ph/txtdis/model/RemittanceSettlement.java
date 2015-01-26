package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.util.Util;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class RemittanceSettlement extends AbstractAudited {

    private static final long serialVersionUID = 3725295775807671330L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users reconciledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime reconciledOn;

    public RemittanceSettlement(Truck truck) {
        this.truck = truck;
    }

    public LocalDate getDate() {
        return getCreatedDate() == null ? null : getCreatedDate().toLocalDate();
    }

    public Users getClosedBy() {
        return getCreatedBy();
    }

    public ZonedDateTime getClosedOn() {
        return getCreatedDate();
    }

    @Override
    public String toString() {
        return truck + " load settlement on " + Util.formatDate(getDate());
    }
}
