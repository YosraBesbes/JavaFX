package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.service.RemittanceService;
import ph.txtdis.type.RemittanceType;

@Component
public class RemittanceDTOImpl extends AbstractSpunById<Remittance, RemittanceService> implements RemittanceDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Remittance();
    }

    @Override
    public Customer getPartner() {
        return entity.getPartner();
    }

    @Override
    public void setPartner(Customer partner) {
        entity.setPartner(partner);
    }

    @Override
    public int getPartnerId() {
        return getPartner() == null ? 0 : getPartner().getId();
    }

    @Override
    public String getPartnerName() {
        return getPartner() == null ? null : getPartner().getName();
    }

    @Override
    public String getPartnerAddress() {
        return getPartner() == null ? null : getPartner().getStreet();
    }

    @Override
    public RemittanceType getType() {
        return entity.getType();
    }

    @Override
    public void setType(RemittanceType type) {
        entity.setType(type);
    }

    @Override
    public String getReference() {
        return entity.getReference();
    }

    @Override
    public void setReference(String reference) {
        entity.setReference(reference);
    }

    @Override
    public BigDecimal getTotalValue() {
        return entity.getValue();
    }

    @Override
    public void setTotalValue(BigDecimal value) {
        entity.setValue(value);
    }

    @Override
    public LocalDate getOrderDate() {
        return entity.getOrderDate();
    }

    @Override
    public void setOrderDate(LocalDate orderDate) {
        entity.setOrderDate(orderDate);
    }

    @Override
    public String getRemarks() {
        return entity.getRemarks();
    }

    @Override
    public void setRemarks(String remarks) {
        entity.setRemarks(remarks);
    }

    @Override
    public ObservableList<RemittanceDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public void setDetails(List<RemittanceDetail> details) {
        entity.setDetails(details);
    }

    @Override
    public BigDecimal getPayment(Invoicing invoice) {
        BigDecimal payment = service.getPayment(invoice);
        return payment == null ? BigDecimal.ZERO : payment;
    }
}
