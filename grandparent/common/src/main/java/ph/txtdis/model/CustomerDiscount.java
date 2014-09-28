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

    private String familyLimit() {
        return familyLimit == null ? "" : " for " + familyLimit;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((familyLimit == null) ? 0 : familyLimit.hashCode());
        result = prime * result + level;
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
        CustomerDiscount other = (CustomerDiscount) obj;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
            return false;
        if (familyLimit == null) {
            if (other.familyLimit != null)
                return false;
        } else if (!familyLimit.equals(other.familyLimit))
            return false;
        if (level != other.level)
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
        return customer + ": less " + discount + "% " + familyLimit() + " starting " + startDate;
    }
}
