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
import ph.txtdis.model.LoadSettlementFilteredDetail;
import ph.txtdis.model.SystemUser;
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
    public ObservableList<LoadSettlementFilteredDetail> getSettlementFilteredDetail(Truck truck, LocalDate date) {
        List<LoadSettlementFilteredDetail> isfx = new ArrayList<>();
        service.getDetail(truck, date).forEach(is -> selectItemsWithVariance(isfx, is));
        return FXCollections.observableArrayList(isfx);
    }

    private void selectItemsWithVariance(List<LoadSettlementFilteredDetail> isfx, LoadSettlementDetail is) {
        if (!DIS.isZero(is.getVarianceQty()))
            isfx.add(new LoadSettlementFilteredDetail(is.getItem(), is.getPickedQty(), is.getSoldQty(), is
                    .getReturnedQty(), is.getAdjustmentQty(), is.getActionTaken()));
    }
}
