package ph.txtdis.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.FxStockTakeReconciliationDetail;
import ph.txtdis.model.Users;
import ph.txtdis.service.MinMax;

public interface StockTakeReconciliationDTO extends Spun, DTO<StockTakeReconciliation, LocalDate>, MinMax<LocalDate> {

    Users getCutoffBy();

    void setCutoffBy(Users cutoffBy);

    ZonedDateTime getCutoffOn();

    Users getClosedBy();

    void setClosedBy(Users closedBy);

    ZonedDateTime getClosedOn();

    void setClosedOn(ZonedDateTime closedOn);

    Users getReconciledBy();

    void setReconciledBy(Users reconciledBy);

    ZonedDateTime getReconciledOn();

    void setReconciledOn(ZonedDateTime reconciledOn);

    Users getMailedBy();

    void setMailedBy(Users mailedBy);

    ZonedDateTime getMailedOn();

    void setMailedOn(ZonedDateTime mailedOn);

    Users getApprovedBy();

    void setApprovedBy(Users approvedBy);

    ZonedDateTime getApprovedOn();

    void setApprovedOn(ZonedDateTime approvedOn);

    Users getCompletedBy();

    void setCompletedBy(Users completedBy);

    ZonedDateTime getCompletedOn();

    void setCompleteOn(ZonedDateTime completedOn);

    Boolean isApproved();

    void setApproved(Boolean isApproved);

    ObservableList<FxStockTakeReconciliationDetail> getStockTakeReconciliationFilteredDetail();
}
