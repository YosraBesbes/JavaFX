package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import ph.txtdis.model.Users;
import ph.txtdis.model.Truck;

public interface SettlementDTO<E> extends DTO<E, Integer> {

    E get(Truck truck, LocalDate date);

    Truck getTruck();

    LocalDate getDate();

    Users getClosedBy();

    ZonedDateTime getClosedOn();

    Users getReconciledBy();

    void setReconciledBy(Users reconciledBy);

    ZonedDateTime getReconciledOn();

    void setReconciledOn(ZonedDateTime reconciledOn);
}
