package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.service.MinMax;

public interface SummaryDTO extends Spun, DTO<DailySummary, LocalDate>, MinMax<LocalDate> {

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

    void setCompletedOn(ZonedDateTime completedOn);

    Boolean isApproved();

    void setApproved(Boolean isApproved);

    ObservableList<VolumeSummary> getVolumeSummary();

    ObservableList<Invoicing> getInvoices();

    ObservableList<Remittance> getRemittances();

    ObservableList<VolumeSummary> getVolumeSummary(String truckName);

    ObservableList<Invoicing> getInvoices(String truckName);

    ObservableList<Remittance> getRemittances(String truckName);

    BigDecimal getTotalVolume();

    BigDecimal getTotalRevenue();

    BigDecimal getTotalRemittance();
}
