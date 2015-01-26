package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.Users;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.service.MinMax;

public interface SummaryDTO extends Spun, DTO<DailySummary, LocalDate>, MinMax<LocalDate> {

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
