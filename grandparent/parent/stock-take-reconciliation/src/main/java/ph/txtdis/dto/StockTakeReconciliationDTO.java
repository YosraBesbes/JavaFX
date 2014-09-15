package ph.txtdis.dto;

import java.time.ZonedDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.SystemUser;

public interface StockTakeReconciliationDTO extends SpunDTO, DatedDTO<StockTakeReconciliation> {

    SystemUser getClosedBy();

    void setClosedBy(SystemUser closedBy);

    ZonedDateTime getClosedOn();

    void setClosedOn(ZonedDateTime closedOn);

    SystemUser getReconciledBy();

    void setReconciledBy(SystemUser reconciledBy);

    ZonedDateTime getReconciledOn();

    void setReconciledOn(ZonedDateTime reconciledOn);

    ObservableList<StockTakeReconciliationDetail> getDetails();

    void setDetails(List<StockTakeReconciliationDetail> details);
}
