package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.service.RemittanceService;
import ph.txtdis.type.RemittanceType;

@Component
public class RemittanceDTOImpl extends AbstractSpunDTO<Remittance, RemittanceService> implements RemittanceDTO {

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
        return entity.getPartnerId();
    }

    @Override
    public void setPartnerId(int partnerId) {
        entity.setPartnerId(partnerId);
    }

    @Override
    public String getPartnerName() {
        return entity.getPartnerName();
    }

    @Override
    public void setPartnerName(String partnerName) {
        entity.setPartnerName(partnerName);
    }

    @Override
    public String getPartnerAddress() {
        return entity.getPartnerAddress();
    }

    @Override
    public void setPartnerAddress(String partnerAddress) {
        entity.setPartnerAddress(partnerAddress);
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
    public BigDecimal getAmount() {
        return entity.getAmount();
    }

    @Override
    public void setAmount(BigDecimal amount) {
        entity.setAmount(amount);
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
}
