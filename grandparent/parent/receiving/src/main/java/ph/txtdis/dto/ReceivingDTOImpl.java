package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.type.ReceivingReferenceType;

@Component
public class ReceivingDTOImpl extends AbstractOrderDTO<Receiving, ReceivingService, ReceivingDetail> implements
        ReceivingDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Receiving();
    }

    @Override
    public SystemUser getChecker() {
        return entity.getChecker();
    }

    @Override
    public void setChecker(SystemUser checker) {
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
}
