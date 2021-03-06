package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.LoadSettlement;
import ph.txtdis.model.LoadSettlementDetail;
import ph.txtdis.model.Users;
import ph.txtdis.model.Truck;
import ph.txtdis.service.LoadSettlementService;
import ph.txtdis.util.DIS;

@Component
public class LoadSettlementDTOImpl extends AbstractDTO<LoadSettlement, LoadSettlementService, Integer> implements
        LoadSettlementDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new LoadSettlement();
    }

    @Override
    public LoadSettlement get(Truck truck, LocalDate date) {
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
    public ObservableList<LoadSettlementDetail> getSettlementDetail(Truck truck, LocalDate date) {
        return FXCollections.observableArrayList(removeBalancedItems(service.getDetail(truck, date)));
    }

    private List<LoadSettlementDetail> removeBalancedItems(List<LoadSettlementDetail> details) {
        List<LoadSettlementDetail> list = new ArrayList<>();
        for (LoadSettlementDetail detail : details)
            if (!DIS.isZero(detail.getVarianceQty()))
                list.add(detail);
        return list;
    }
}
