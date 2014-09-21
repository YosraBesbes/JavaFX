package ph.txtdis.dto;

import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTakeReconciliationFilteredDetail;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.SpunByDate;

public interface StockTakeReconciliationDTO extends SpunByDate, DatedDTO<StockTakeReconciliation> {

    SystemUser getCutoffBy();

    void setCutoffBy(SystemUser cutoffBy);

    ZonedDateTime getCutoffOn();

    void setCutoffOn(ZonedDateTime cutoffOn);

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

    SystemUser getRetrievedBy();

    void setRetrievedBy(SystemUser retrievedBy);

    ZonedDateTime getRetrievedOn();

    void setRetrievedOn(ZonedDateTime retrievedOn);

    Boolean isApproved();

    void setApproved(Boolean isApproved);

    ObservableList<StockTakeReconciliationFilteredDetail> getStockTakeReconciliationFilteredDetail();
}
