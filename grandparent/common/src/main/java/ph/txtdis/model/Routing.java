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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((route == null) ? 0 : route.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Routing other = (Routing) obj;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (route == null) {
            if (other.route != null)
                return false;
        } else if (!route.equals(other.route))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return customer + " - " + route + ": " + startDate;
    }
}
