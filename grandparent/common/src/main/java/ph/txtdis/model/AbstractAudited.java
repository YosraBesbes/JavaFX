package ph.txtdis.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import ph.txtdis.util.Login;

@MappedSuperclass
public class AbstractAudited implements Serializable, Key<Integer> {

    private static final long serialVersionUID = 1945412496395116277L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SystemUser createdBy;

    @Column(updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime timeStamp;

    protected AbstractAudited() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public SystemUser getCreatedBy() {
        return createdBy;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    @PrePersist
    private void setCreationStamp() {
        this.createdBy = Login.user();
        this.timeStamp = ZonedDateTime.now();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result + id;
        result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
        AbstractAudited other = (AbstractAudited) obj;
        if (createdBy == null) {
            if (other.createdBy != null)
                return false;
        } else if (!createdBy.equals(other.createdBy))
            return false;
        if (id != other.id)
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        return true;
    }
}