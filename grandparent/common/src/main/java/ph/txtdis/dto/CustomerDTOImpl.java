package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Channel;
import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.Location;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;
import ph.txtdis.service.CustomerService;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

@Component
public class CustomerDTOImpl extends AbstractSearchedSpunDTO<Customer, String, CustomerService> implements CustomerDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Customer();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public void setName(String name) {
        entity.setName(name);
    }

    @Override
    public String getAddress() {
        return entity.getAddress();
    }

    @Override
    public void setAddress(String address) {
        entity.setAddress(address);
    }

    @Override
    public Location getBarangay() {
        return entity.getBarangay();
    }

    @Override
    public void setBarangay(Location barangay) {
        entity.setBarangay(barangay);
    }

    @Override
    public Location getCity() {
        return entity.getCity();
    }

    @Override
    public void setCity(Location city) {
        entity.setCity(city);
    }

    @Override
    public Location getProvince() {
        return entity.getProvince();
    }

    @Override
    public void setProvince(Location province) {
        entity.setProvince(province);
    }

    @Override
    public CustomerType getType() {
        return entity.getType();
    }

    @Override
    public void setType(CustomerType type) {
        entity.setType(type);
    }

    @Override
    public Channel getChannel() {
        return entity.getChannel();
    }

    @Override
    public void setChannel(Channel channel) {
        entity.setChannel(channel);
    }

    @Override
    public VisitFrequency getVisitFrequency() {
        return entity.getVisitFrequency();
    }

    @Override
    public void setVisitFrequency(VisitFrequency visitFrequency) {
        entity.setVisitFrequency(visitFrequency);
    }

    @Override
    public ObservableList<Routing> getRouteHistory() {
        return FXCollections.observableList(service.getRouteHistory(id));
    }

    @Override
    public void setRouteHistory(List<Routing> routeHistory) {
        entity.setRouteHistory(routeHistory);
    }

    @Override
    public String getCreditContactName() {
        return entity.getCreditContactName();
    }

    @Override
    public void setCreditContactName(String creditContactName) {
        entity.setCreditContactName(creditContactName);
    }

    @Override
    public String getCreditContactSurname() {
        return entity.getCreditContactSurname();
    }

    @Override
    public void setCreditContactSurname(String creditContactSurname) {
        entity.setCreditContactSurname(creditContactSurname);
    }

    @Override
    public String getContactTitle() {
        return entity.getContactTitle();
    }

    @Override
    public void setContactTitle(String contactTitle) {
        entity.setContactTitle(contactTitle);
    }

    @Override
    public long getMobile() {
        return entity.getMobile();
    }

    @Override
    public void setMobile(long mobile) {
        entity.setMobile(mobile);
    }

    @Override
    public ObservableList<CreditDetail> getCreditDetails() {
        return FXCollections.observableList(service.getCreditDetails(id));
    }

    @Override
    public void setCreditDetails(List<CreditDetail> creditDetails) {
        entity.setCreditDetails(creditDetails);
    }

    @Override
    public ObservableList<CustomerDiscount> getDiscounts() {
        return FXCollections.observableList(service.getDiscounts(id));
    }

    @Override
    public void setDiscounts(List<CustomerDiscount> discounts) {
        entity.setDiscounts(discounts);
    }

    @Override
    public boolean exists(String name) {
        return service.exists(name);
    }

    @Override
    public String getDetail() {
        return entity.getFullAdddress();
    }

    @Override
    public Route getLatestRoute(LocalDate date) {
        return service.getLatestRoute(entity, date);
    }

    @Override
    public CreditDetail getLatestCreditDetail(LocalDate date) {
        return service.getLatestCreditDetail(entity, date);
    }

    @Override
    public CustomerDiscount getLatestCustomerDiscount(LocalDate date) {
        return service.getLatestCustomerDiscount(entity, date);
    }
}
