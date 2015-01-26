package ph.txtdis.dto;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.Users;
import ph.txtdis.service.PickingService;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.type.ReceivingReferenceType;

@Component
public class ReceivingDTOImpl extends AbstractOrderDTO<Receiving, ReceivingService, ReceivingDetail> implements
        ReceivingDTO {

    @Autowired
    PickingService pickingService;

    @Override
    public void reset() {
        id = 0;
        entity = new Receiving();
    }

    @Override
    public Users getChecker() {
        return entity.getChecker();
    }

    @Override
    public void setChecker(Users checker) {
        entity.setChecker(checker);
    }

    @Override
    public long getPartnerReferenceId() {
        return entity.getPartnerReferenceId();
    }

    @Override
    public void setPartnerReferenceId(long partnerReferenceId) {
        entity.setPartnerReferenceId(partnerReferenceId);
    }

    @Override
    public ReceivingReferenceType getReference() {
        return entity.getReference();
    }

    @Override
    public void setReference(ReceivingReferenceType reference) {
        entity.setReference(reference);
    }

    @Override
    public int getReferenceId() {
        return entity.getReferenceId();
    }

    @Override
    public void setReferenceId(int referenceId) {
        entity.setReferenceId(referenceId);
    }

    @Override
    public ObservableList<ReceivingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public LocalDate getReceivedDateFromPurchaseOrder(int id) {
        return service.getDateFromPurchaseOrder(id);
    }

    @Override
    public LocalDate getPickDateFromSalesOrder(Booking booking) {
        return pickingService.getPickDateFromSalesOrder(booking);
    }
}
