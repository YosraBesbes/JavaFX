package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.RemittanceSettlement;
import ph.txtdis.model.RemittanceSettlementDetail;
import ph.txtdis.model.RemittanceSettlementFilteredDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.service.RemittanceSettlementService;
import ph.txtdis.util.DIS;

@Component
public class RemittanceSettlementDTOImpl extends
        AbstractDTO<RemittanceSettlement, RemittanceSettlementService, Integer> implements RemittanceSettlementDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new RemittanceSettlement();
    }

    @Override
    public RemittanceSettlement get(Truck truck, LocalDate date) {
        return service.get(truck, date);
    }

    @Override
    public Truck getTruck() {
        return entity.getTruck();
    }

    @Override
    public LocalDate getDate() {
        return entity.getDate();
    }

    @Override
    public SystemUser getClosedBy() {
        return entity.getClosedBy();
    }

    @Override
    public ZonedDateTime getClosedOn() {
        return entity.getClosedOn();
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
    public ObservableList<RemittanceSettlementFilteredDetail> getSettlementFilteredDetail(Truck truck, LocalDate date) {
        List<RemittanceSettlementFilteredDetail> isfx = new ArrayList<>();
        service.getDetail(truck, date).forEach(is -> selectInvoicesNotFullyPaid(isfx, is));
        return FXCollections.observableArrayList(isfx);
    }

    private void selectInvoicesNotFullyPaid(List<RemittanceSettlementFilteredDetail> isfx, RemittanceSettlementDetail is) {
        if (!DIS.isZero(is.getVariance()))
            isfx.add(new RemittanceSettlementFilteredDetail(is.getInvoice(), is.getRemittedValue(), is
                    .getAdjustmentValue(), is.getActionTaken()));
    }
}
