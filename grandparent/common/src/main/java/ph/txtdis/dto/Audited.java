package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.SystemUser;

public interface Audited<E> extends DTO<E, Integer> {

    SystemUser getCreatedBy();

    ZonedDateTime getTimeStamp();
}
