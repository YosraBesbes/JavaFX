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
import ph.txtdis.model.Users;
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
    public Users getClosedBy() {
        return entity.getClosedBy();
    }

    @Override
    public ZonedDateTime getClosedOn() {
        return entity.getClosedOn();
    }

    @Override
    public Users getReconciledBy() {
        return entity.getReconciledBy();
    }

    @Override
    public void setReconciledBy(Users reconciledBy) {
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
    public ObservableList<RemittanceSettlementDetail> getSettlementDetail(Truck truck, LocalDate date) {
        return FXCollections.observableArrayList(removeFullyPaidInvoices(service.getDetail(truck, date)));
    }

    private List<RemittanceSettlementDetail> removeFullyPaidInvoices(List<RemittanceSettlementDetail> details) {
        List<RemittanceSettlementDetail> list = new ArrayList<>();
        for (RemittanceSettlementDetail detail : details)
            if (!DIS.isZero(detail.getVarianceValue()))
                list.add(detail);
        return list;
    }
}
