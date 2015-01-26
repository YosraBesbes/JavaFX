package ph.txtdis.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Picking extends AbstractAudited {

    private static final long serialVersionUID = -3835242947594550479L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users driver;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users helper1;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users helper2;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate pickDate = LocalDate.now();

    private String remarks;

    @OneToMany(mappedBy = "picking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PickingDetail> details;

    @Transient
    private List<PickList> pickList;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users printedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime printedOn;

    public Picking(Truck truck, Users driver, Users helper1, LocalDate pickDate) {
        this.truck = truck;
        this.driver = driver;
        this.helper1 = helper1;
        this.pickDate = pickDate;
    }

    @Override
    public String toString() {
        return truck + " on " + pickDate;
    }
}
