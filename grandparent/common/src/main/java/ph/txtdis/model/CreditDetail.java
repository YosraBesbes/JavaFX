package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class CreditDetail extends AbstractAudited {

    private static final long serialVersionUID = 1655742390812653142L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;
    
    private int term, gracePeriod;
    
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal creditLimit;  

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    public CreditDetail() {
    }

    public CreditDetail(Customer customer, int term, int gracePeriod, BigDecimal creditLimit, LocalDate startDate) {
        this.customer = customer;
        this.term = term;
        this.gracePeriod = gracePeriod;
        this.creditLimit = creditLimit;
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public BigDecimal getLimit() {
        return creditLimit;
    }

    public void setLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return customer + ": max of " + term + " days or P" + creditLimit + " starting " + startDate;
    }
}
