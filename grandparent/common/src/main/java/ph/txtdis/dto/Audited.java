package ph.txtdis.dto;

import java.time.ZonedDateTime;

import ph.txtdis.model.Users;

public interface Audited<E> extends DTO<E, Integer> {

    Users getCreatedBy();

    ZonedDateTime getIssuedDate();
}
