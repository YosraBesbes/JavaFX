package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Routing extends AbstractAudited {

    private static final long serialVersionUID = 7710563453808768120L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;
    
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Route route;
    
    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    public Routing() {
    }

    public Routing(Customer customer, Route route, LocalDate start) {
        this.customer = customer;
        this.route = route;
        this.startDate = start;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return customer + " - " + route + ": " + startDate;
    }
}
