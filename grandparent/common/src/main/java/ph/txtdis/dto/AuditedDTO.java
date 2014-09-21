package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.SystemUser;

public interface AuditedDTO<E> extends DTO<E> {

    int getId();

    void setId(int id);

    E get(int id);

    boolean exists(int id);

    public SystemUser getCreatedBy();

    void setCreatedBy(SystemUser createdBy);

    ZonedDateTime getTimeStamp();

    void setTimeStamp(ZonedDateTime timestamp);
}
