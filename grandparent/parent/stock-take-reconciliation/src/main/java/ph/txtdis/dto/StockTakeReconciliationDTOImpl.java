package ph.txtdis.dto;

import java.time.ZonedDateTime;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.service.StockTakeStatusService;

@Component
public class StockTakeReconciliationDTOImpl extends
        AbstractSpunByDateDTO<StockTakeReconciliation, StockTakeReconciliationService> implements
        StockTakeReconciliationDTO {

    @Autowired
    StockTakeStatusService statusService;

    @Override
    public void reset() {
        idDate = null;
        entity = new StockTakeReconciliation();
    }

    @Override
    public SystemUser getClosedBy() {
        return entity.getClosedBy();
    }

    @Override
    public void setClosedBy(SystemUser closedBy) {
        entity.setClosedBy(closedBy);
    }

    @Override
    public ZonedDateTime getClosedOn() {
        return entity.getClosedOn();
    }

    @Override
    public void setClosedOn(ZonedDateTime closedOn) {
        entity.setClosedOn(closedOn);
    }

    @Override
    public SystemUser getReconciledBy() {
        return entity.getReconciledBy();
    }

    @Override
    public void setReconciledBy(SystemUser reconciledBy) {
        entity.setReconciledBy(reconciledBy);
    }

    @Override
    public ZonedDateTime getReconciledOn() {
        return entity.getReconciledOn();
    }

    @Override
    public void setReconciledOn(ZonedDateTime reconciledOn) {
        entity.setReconciledOn(reconciledOn);
    }

    @Override
    public ObservableList<StockTakeReconciliationDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(idDate));
    }

    @Override
    public void setDetails(List<StockTakeReconciliationDetail> details) {
        entity.setDetails(details);
    }
}
