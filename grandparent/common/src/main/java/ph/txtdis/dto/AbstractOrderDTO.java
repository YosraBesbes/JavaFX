package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.AbstractOrder;
import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.ItemDetailed;
import ph.txtdis.model.Route;
import ph.txtdis.service.OrderServiced;

@Component
public abstract class AbstractOrderDTO<E extends AbstractOrder<D>, S extends OrderServiced<E, D>, D extends ItemDetailed>
        extends AbstractSpunDTO<E, S> implements OrderDTO<E, D> {

    @Override
    public int getPartnerId() {
        return entity.getPartner() == null ? 0 : entity.getPartner().getId();
    }

    @Override
    public void setPartner(Customer partner) {
        entity.setPartner(partner);
    }

    @Override
    public String getPartnerName() {
        return entity.getPartner() == null ? null : entity.getPartner().getName();
    }

    @Override
    public String getPartnerAddress() {
        return entity.getPartner() == null ? null : entity.getPartner().getFullAdddress();
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
    public void setRoute(Route route) {
        entity.setRoute(route);
    }

    @Override
    public void setCredit(CreditDetail credit) {
        entity.setCredit(credit);
    }

    @Override
    public void setDiscount(CustomerDiscount discount) {
        entity.setDiscount(discount);
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
    public ObservableList<D> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public void setDetails(List<D> details) {
        entity.setDetails(details);
    }
}
