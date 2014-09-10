package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.SystemUser;

public interface DTO<E> {

    int getId();

    void setId(int id);

    void reset();

    void delete();

    void save();

    E get();

    E get(int id);

    void set(E entity);
    
    boolean exists(int id);
    
    public SystemUser getCreatedBy(); 
    
    void setCreatedBy(SystemUser createdBy);
    
    ZonedDateTime getTimeStamp();
    
    void setTimeStamp(ZonedDateTime timestamp);
}
