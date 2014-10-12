package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;

public interface SettlementDTO<E> extends DTO<E, Integer> {

    E get(Truck truck, LocalDate date);

    Truck getTruck();

    LocalDate getDate();

    SystemUser getClosedBy();

    ZonedDateTime getClosedOn();

    SystemUser getReconciledBy();

    void setReconciledBy(SystemUser reconciledBy);

    ZonedDateTime getReconciledOn();

    void setReconciledOn(ZonedDateTime reconciledOn);
}
