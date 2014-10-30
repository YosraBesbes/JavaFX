package ph.txtdis.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.CustomerRoute;
import ph.txtdis.model.Route;
import ph.txtdis.model.Routing;
import ph.txtdis.repository.CreditDetailRepository;
import ph.txtdis.repository.CustomerDiscountRepository;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.RoutingRepository;
import ph.txtdis.type.CustomerType;

@Service
@Transactional()
public class CustomerServiceImpl extends AbstractService<Customer, Integer> implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private RoutingRepository routingRepository;

    @Autowired
    private CreditDetailRepository creditRepository;

    @Autowired
    private CustomerDiscountRepository discountRepository;

    protected CustomerServiceImpl() {
    }

    @Override
    public List<Customer> findAll(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public Integer getMinId() {
        return repository.getMinId();
    }

    @Override
    public Integer getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public List<Routing> getRouteHistory(int id) {
        return repository.getRouteHistory(id);
    }

    @Override
    public List<CreditDetail> getCreditDetails(int id) {
        return repository.getCreditDetails(id);
    }

    @Override
    public List<CustomerDiscount> getDiscounts(int id) {
        return repository.getDiscounts(id);
    }

    @Override
    public boolean exists(String name) {
        return !repository.findByName(name).isEmpty();
    }

    @Override
    public Route getLatestRoute(Customer customer, LocalDate date) {
        List<Routing> routes = routingRepository.findByCustomerAndStartDateBeforeOrderByStartDateDesc(customer,
                date.plusDays(1L), new PageRequest(0, 1));
        return routes.isEmpty() ? null : routes.get(0).getRoute();
    }

    @Override
    public CreditDetail getLatestCreditDetail(Customer customer, LocalDate date) {
        List<CreditDetail> details = creditRepository.findByCustomerAndStartDateBeforeOrderByStartDateDesc(customer,
                date.plusDays(1L), new PageRequest(0, 1));
        return details.isEmpty() ? null : details.get(0);
    }

    @Override
    public CustomerDiscount getLatestCustomerDiscount(Customer customer, LocalDate date) {
        List<CustomerDiscount> discounts = discountRepository.findByCustomerAndStartDateBeforeOrderByStartDateDesc(
                customer, date.plusDays(1L), new PageRequest(0, 1));
        return discounts.isEmpty() ? null : discounts.get(0);
    }

    @Override
    public boolean acceptsRemittance(int id) {
        CustomerType type = repository.getType(id);
        return type == CustomerType.BANK || type == CustomerType.CASHIER || type == CustomerType.SHORTAGE;
    }

    @Override
    public List<CustomerRoute> list() {
        return repository.getExistingCustomersWithTheirLatestRoute();
    }
}
