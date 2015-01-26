package ph.txtdis.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.dto.Printed;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractPrintedOrder<D extends ItemDetailed> extends AbstractOrder<D> implements Printed {

    private static final long serialVersionUID = 4553686125204430154L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users printedBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime printedOn;
}
