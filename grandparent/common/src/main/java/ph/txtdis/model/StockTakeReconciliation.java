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
public class StockTakeReconciliation extends AbstractDated {

    private static final long serialVersionUID = -7137997478152209732L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users cutoffBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime cutoffOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users closedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime closedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users reconciledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime reconciledOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users mailedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime mailedOn;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users approvedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime approvedOn;

    private Boolean isApproved;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users completedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime completedOn;

    public StockTakeReconciliation(Users cutoffBy, LocalDate id) {
        setCutoffBy(cutoffBy);
        setId(id);
    }

    @Override
    public String toString() {
        return "Stock Take on " + Util.formatDate(getId());
    }
}
