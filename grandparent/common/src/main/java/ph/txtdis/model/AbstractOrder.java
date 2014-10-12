package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class AbstractOrder<D extends ItemDetailed> extends AbstractAudited implements Ordered<D> {

    private static final long serialVersionUID = 3715783817464199036L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Customer partner;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Route route;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private CreditDetail credit;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private CustomerDiscount discount;

    @Column(nullable = false)
    protected BigDecimal value;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    protected LocalDate orderDate;

    private String remarks;

    public AbstractOrder() {
    }

    @Override
    public Customer getPartner() {
        return partner;
    }

    @Override
    public void setPartner(Customer partner) {
        this.partner = partner;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public CreditDetail getCredit() {
        return credit;
    }

    public void setCredit(CreditDetail credit) {
        this.credit = credit;
    }

    public CustomerDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(CustomerDiscount discount) {
        this.discount = discount;
    }

    @Override
    public BigDecimal getTotalValue() {
        return value;
    }

    @Override
    public void setTotalValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((credit == null) ? 0 : credit.hashCode());
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + ((partner == null) ? 0 : partner.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
        result = prime * result + ((route == null) ? 0 : route.hashCode());
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
        AbstractOrder<?> other = (AbstractOrder<?>) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (credit == null) {
            if (other.credit != null)
                return false;
        } else if (!credit.equals(other.credit))
            return false;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
            return false;
        if (partner == null) {
            if (other.partner != null)
                return false;
        } else if (!partner.equals(other.partner))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
            return false;
        if (route == null) {
            if (other.route != null)
                return false;
        } else if (!route.equals(other.route))
            return false;
        return true;
    }
}
