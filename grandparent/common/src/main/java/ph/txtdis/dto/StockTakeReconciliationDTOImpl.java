package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.FxStockTakeReconciliationDetail;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.Users;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.util.DIS;

@Component
public class StockTakeReconciliationDTOImpl extends
        AbstractSpunByDate<StockTakeReconciliation, StockTakeReconciliationService> implements
        StockTakeReconciliationDTO {

    @Override
    public void reset() {
        id = null;
        entity = new StockTakeReconciliation();
    }

    @Override
    public Users getCutoffBy() {
        return entity.getCutoffBy();
    }

    @Override
    public void setCutoffBy(Users cutoffBy) {
        entity.setCutoffBy(cutoffBy);
    }

    @Override
    public ZonedDateTime getCutoffOn() {
        return entity.getCutoffOn();
    }

    @Override
    public Users getClosedBy() {
        return entity.getClosedBy();
    }

    @Override
    public void setClosedBy(Users closedBy) {
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
    public Users getMailedBy() {
        return entity.getMailedBy();
    }

    @Override
    public void setMailedBy(Users mailedBy) {
        entity.setMailedBy(mailedBy);
    }

    @Override
    public ZonedDateTime getMailedOn() {
        return entity.getMailedOn();
    }

    @Override
    public void setMailedOn(ZonedDateTime mailedOn) {
        entity.setMailedOn(mailedOn);
    }

    @Override
    public Users getApprovedBy() {
        return entity.getApprovedBy();
    }

    @Override
    public void setApprovedBy(Users approvedBy) {
        entity.setApprovedBy(approvedBy);
    }

    @Override
    public ZonedDateTime getApprovedOn() {
        return entity.getApprovedOn();
    }

    @Override
    public void setApprovedOn(ZonedDateTime approvedOn) {
        entity.setApprovedOn(approvedOn);
    }

    @Override
    public Boolean isApproved() {
        return entity.getIsApproved();
    }

    @Override
    public void setApproved(Boolean isApproved) {
        entity.setIsApproved(isApproved);
    }

    @Override
    public Users getCompletedBy() {
        return entity.getCompletedBy();
    }

    @Override
    public void setCompletedBy(Users completedBy) {
        entity.setCompletedBy(completedBy);
        ;
    }

    @Override
    public ZonedDateTime getCompletedOn() {
        return entity.getCompletedOn();
    }

    @Override
    public void setCompleteOn(ZonedDateTime completedOn) {
        entity.setCompletedOn(completedOn);
    }

    @Override
    public ObservableList<FxStockTakeReconciliationDetail> getStockTakeReconciliationFilteredDetail() {
        LocalDate endDate = getMaxId();
        List<FxStockTakeReconciliationDetail> isfx = new ArrayList<>();
        service.getDetail(getStartDate(endDate), endDate).forEach(is -> selectItemsWithVariance(isfx, is));
        return FXCollections.observableArrayList(isfx);
    }

    private void selectItemsWithVariance(List<FxStockTakeReconciliationDetail> isfx, StockTakeReconciliationDetail is) {
        if (!DIS.isZero(getVariance(is)))
            isfx.add(new FxStockTakeReconciliationDetail(is.getItem(), is.getQuality(), is.getSystemQty(), is
                    .getCountQty(), is.getAdjustmentQty(), is.getJustification()));
    }

    private BigDecimal getVariance(StockTakeReconciliationDetail is) {
        return is.getSystemQty().subtract(is.getCountQty());
    }

    private LocalDate getStartDate(LocalDate endDate) {
        LocalDate startDate = service.getImmediatelyPrecedingDate(endDate);
        return startDate == null ? LocalDate.ofEpochDay(0) : startDate;
    }
}
