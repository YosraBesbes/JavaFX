package ph.txtdis.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAudited implements Serializable, Key<Integer> {

    private static final long serialVersionUID = -5753015257974898395L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @CreatedBy
    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Users createdBy;

    @CreatedDate
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    protected ZonedDateTime createdDate;

    @LastModifiedDate
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime lastModifiedDate;

    @LastModifiedBy
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Users lastModifiedBy;

    @Override
    public Integer getId() {
        return id;
    }
}