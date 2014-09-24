package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;

public interface CustomerService extends SearchedSpunService<Customer, Integer, String>, Unique {

    List<Routing> getRouteHistory(int id);

    List<CreditDetail> getCreditDetails(int id);

    List<CustomerDiscount> getDiscounts(int id);

    Route getLatestRoute(Customer customer, LocalDate date);

    CreditDetail getLatestCreditDetail(Customer customer, LocalDate date);

    CustomerDiscount getLatestCustomerDiscount(Customer customer, LocalDate date);
}