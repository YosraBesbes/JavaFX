package ph.txtdis.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.DailySummary;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.SummaryService;
import ph.txtdis.service.TruckService;
import ph.txtdis.type.UomType;

@Component
public class SummaryDTOImpl extends AbstractSpunByDate<DailySummary, SummaryService> implements SummaryDTO {

    @Autowired
    ItemService itemService;

    @Autowired
    TruckService truckService;

    private List<VolumeSummary> volumes;
    private List<Invoicing> invoices;
    private List<Remittance> remittances;

    @Override
    public LocalDate getId() {
        return id == null ? LocalDate.now() : id;
    }

    @Override
    public void reset() {
        id = null;
        entity = new DailySummary();
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
    public SystemUser getCompletedBy() {
        return entity.getCompletedBy();
    }

    @Override
    public void setCompletedBy(SystemUser completedBy) {
        entity.setCompletedBy(completedBy);
    }

    @Override
    public ZonedDateTime getCompletedOn() {
        return entity.getCompletedOn();
    }

    @Override
    public void setCompletedOn(ZonedDateTime completedOn) {
        entity.setCompletedOn(completedOn);
    }

    @Override
    public ObservableList<VolumeSummary> getVolumeSummary() {
        volumes = service.getVolumeSummary(getId());
        return FXCollections.observableArrayList(volumes);
    }

    @Override
    public ObservableList<Invoicing> getInvoices() {
        invoices = service.getInvoices(getId());
        return FXCollections.observableArrayList(invoices);
    }

    @Override
    public ObservableList<Remittance> getRemittances() {
        remittances = service.getRemittances(getId());
        return FXCollections.observableArrayList(remittances);
    }

    @Override
    public ObservableList<VolumeSummary> getVolumeSummary(String truckName) {
        volumes = service.getVolumeSummary(getId(), truckService.get(truckName));
        return FXCollections.observableArrayList(volumes);
    }

    @Override
    public ObservableList<Invoicing> getInvoices(String truckName) {
        invoices = service.getInvoices(getId(), truckService.get(truckName));
        return FXCollections.observableArrayList(invoices);
    }

    @Override
    public ObservableList<Remittance> getRemittances(String truckName) {
        remittances = service.getRemittances(getId(), truckService.get(truckName));
        return FXCollections.observableArrayList(remittances);
    }

    @Override
    public BigDecimal getTotalVolume() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal qtyPerCase = BigDecimal.ZERO;
        for (VolumeSummary volume : volumes) {
            qtyPerCase = itemService.getQtyPerUomMap(volume.getItemId()).get(UomType.CS);
            total = total.add(volume.getQty().divide(qtyPerCase, 2, RoundingMode.HALF_EVEN));
        }
        return total;
    }

    @Override
    public BigDecimal getTotalRevenue() {
        BigDecimal total = BigDecimal.ZERO;
        for (Invoicing invoice : invoices)
            total = total.add(invoice.getTotalValue());
        return total;
    }

    @Override
    public BigDecimal getTotalRemittance() {
        BigDecimal total = BigDecimal.ZERO;
        for (Remittance remittance : remittances)
            total = total.add(remittance.getTotalValue());
        return total;
    }
}
