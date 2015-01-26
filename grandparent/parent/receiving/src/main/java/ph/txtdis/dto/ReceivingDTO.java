package ph.txtdis.dto;

import java.time.LocalDate;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.Users;
import ph.txtdis.type.ReceivingReferenceType;

public interface ReceivingDTO extends OrderDTO<Receiving, ReceivingDetail> {

    Users getChecker();

    void setChecker(Users checker);

    long getPartnerReferenceId();

    void setPartnerReferenceId(long partnerReferenceId);

    ReceivingReferenceType getReference();

    void setReference(ReceivingReferenceType reference);

    int getReferenceId();

    void setReferenceId(int referenceId);

    LocalDate getReceivedDateFromPurchaseOrder(int id);

    LocalDate getPickDateFromSalesOrder(Booking booking);
}
