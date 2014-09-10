package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Account extends AbstractAudited {

    private static final long serialVersionUID = -3816774251745575218L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser user;
    
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Route route;
    
    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    public Account() {
    }

    public Account(SystemUser user, Route route, LocalDate startDate) {
        this.user = user;
        this.route = route;
        this.startDate = startDate;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
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
        return route + ": " + user + " starting " + startDate;
    }
}
