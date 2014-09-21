package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.StockTakeReconciliationFilteredDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.util.DIS;

@Component
public class StockTakeReconciliationDTOImpl extends
        AbstractSpunByDateDTO<StockTakeReconciliation, StockTakeReconciliationService> implements
        StockTakeReconciliationDTO {

    @Override
    public void reset() {
        idDate = null;
        entity = new StockTakeReconciliation();
    }

    @Override
    public SystemUser getCutoffBy() {
        return entity.getCutoffBy();
    }

    @Override
    public void setCutoffBy(SystemUser cutoffBy) {
        entity.setCutoffBy(cutoffBy);
    }

    @Override
    public ZonedDateTime getCutoffOn() {
        return entity.getCutoffOn();
    }

    @Override
    public void setCutoffOn(ZonedDateTime cutoffOn) {
        entity.setCutoffOn(cutoffOn);
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
    public SystemUser getMailedBy() {
        return entity.getMailedBy();
    }

    @Override
    public void setMailedBy(SystemUser mailedBy) {
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
    public SystemUser getApprovedBy() {
        return entity.getApprovedBy();
    }

    @Override
    public void setApprovedBy(SystemUser approvedBy) {
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
        return entity.isApproved();
    }

    @Override
    public void setApproved(Boolean isApproved) {
        entity.setApproved(isApproved);
    }

    @Override
    public SystemUser getRetrievedBy() {
        return entity.getRetrievedBy();
    }

    @Override
    public void setRetrievedBy(SystemUser retrievedBy) {
        entity.setRetrievedBy(retrievedBy);
        ;
    }

    @Override
    public ZonedDateTime getRetrievedOn() {
        return entity.getRetrievedOn();
    }

    @Override
    public void setRetrievedOn(ZonedDateTime retrievedOn) {
        entity.setRetrievedOn(retrievedOn);
    }

    @Override
    public ObservableList<StockTakeReconciliationFilteredDetail> getStockTakeReconciliationFilteredDetail() {
        LocalDate endDate = getLatestDate();
        List<StockTakeReconciliationFilteredDetail> isfx = new ArrayList<>();
        service.getStockTakeReconciliationDetail(getStartDate(endDate), endDate).forEach(
                is -> selectItemsWithVariance(isfx, is));
        return FXCollections.observableArrayList(isfx);
    }

    private void selectItemsWithVariance(List<StockTakeReconciliationFilteredDetail> isfx,
            StockTakeReconciliationDetail is) {
        if (!DIS.isZero(getVariance(is)))
            isfx.add(new StockTakeReconciliationFilteredDetail(is.getItem(), is.getQuality(), is.getSystemQty(), is
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
