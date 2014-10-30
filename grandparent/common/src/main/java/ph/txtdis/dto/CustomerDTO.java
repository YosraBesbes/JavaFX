package ph.txtdis.dto;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import ph.txtdis.model.Channel;
import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.CustomerRoute;
import ph.txtdis.model.Disable;
import ph.txtdis.model.Location;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;
import ph.txtdis.model.SystemUser;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

public interface CustomerDTO extends SearchedDTO<Customer, String>, Spun, SpecificName<Customer>, UniqueName, Disable {

    String getAddress();

    void setAddress(String address);

    Location getBarangay();

    void setBarangay(Location barangay);

    Location getCity();

    void setCity(Location city);

    Location getProvince();

    void setProvince(Location province);

    CustomerType getType();

    void setType(CustomerType type);

    Channel getChannel();

    void setChannel(Channel channel);

    VisitFrequency getVisitFrequency();

    void setVisitFrequency(VisitFrequency visitFrequency);

    ObservableList<Routing> getRouteHistory();

    void setRouteHistory(List<Routing> routeHistory);

    String getCreditContactName();

    void setCreditContactName(String creditContactName);

    String getCreditContactSurname();

    void setCreditContactSurname(String creditContactSurname);

    String getContactTitle();

    void setContactTitle(String contactTitle);

    long getMobile();

    void setMobile(long mobile);

    ObservableList<CreditDetail> getCreditDetails();

    void setCreditDetails(List<CreditDetail> creditDetails);

    ObservableList<CustomerDiscount> getDiscounts();

    void setDiscounts(List<CustomerDiscount> discounts);

    Route getLatestRoute(LocalDate date);

    CreditDetail getLatestCreditDetail(LocalDate date);

    CustomerDiscount getLatestCustomerDiscount(LocalDate date);

    boolean acceptsRemittance();

    ObservableList<CustomerRoute> list();

    @Override
    SystemUser getDisabledBy();
}
