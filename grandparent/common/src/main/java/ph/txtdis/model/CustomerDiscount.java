package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class CustomerDiscount extends AbstractAudited {

    private static final long serialVersionUID = -455882680349394952L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer customer;
    
    @Column(nullable = false)
    private int level;
    
    @Column(nullable = false, precision = 7, scale = 4)
    private BigDecimal discount;
    
    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private ItemFamily familyLimit;
    
    public CustomerDiscount() {
    }

    public CustomerDiscount(Customer customer, int level, BigDecimal discount, LocalDate startDate) {
        this.customer = customer;
        this.level = level;
        this.discount = discount;
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ItemFamily getFamilyLimit() {
        return familyLimit;
    }

    public void setFamilyLimit(ItemFamily familyLimit) {
        this.familyLimit = familyLimit;
    }

    @Override
    public String toString() {
        return customer + ": less " + discount + "% " +  familyLimit() + " starting " + startDate;
    }
    
    private String familyLimit() {
        return familyLimit == null ? "" : " for " + familyLimit;
    }
}
