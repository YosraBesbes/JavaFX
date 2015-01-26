package ph.txtdis.model;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public class AbstractDisabledNamed extends AbstractNamed implements Disable {

    private static final long serialVersionUID = 6409589135828964023L;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users disabledBy;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime disabledOn;

    public AbstractDisabledNamed(String name) {
        super(name);
    }
}