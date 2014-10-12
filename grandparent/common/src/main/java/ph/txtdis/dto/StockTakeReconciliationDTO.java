package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationFilteredDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.MinMax;

public interface StockTakeReconciliationDTO extends Spun, DTO<StockTakeReconciliation, LocalDate>, MinMax<LocalDate> {

    SystemUser getCutoffBy();

    void setCutoffBy(SystemUser cutoffBy);

    ZonedDateTime getCutoffOn();

    SystemUser getClosedBy();

    void setClosedBy(SystemUser closedBy);

    ZonedDateTime getClosedOn();

    void setClosedOn(ZonedDateTime closedOn);

    SystemUser getReconciledBy();

    void setReconciledBy(SystemUser reconciledBy);

    ZonedDateTime getReconciledOn();

    void setReconciledOn(ZonedDateTime reconciledOn);

    SystemUser getMailedBy();

    void setMailedBy(SystemUser mailedBy);

    ZonedDateTime getMailedOn();

    void setMailedOn(ZonedDateTime mailedOn);

    SystemUser getApprovedBy();

    void setApprovedBy(SystemUser approvedBy);

    ZonedDateTime getApprovedOn();

    void setApprovedOn(ZonedDateTime approvedOn);

    SystemUser getCompletedBy();

    void setCompletedBy(SystemUser completedBy);

    ZonedDateTime getCompletedOn();

    void setCompleteOn(ZonedDateTime completedOn);

    Boolean isApproved();

    void setApproved(Boolean isApproved);

    ObservableList<StockTakeReconciliationFilteredDetail> getStockTakeReconciliationFilteredDetail();
}
