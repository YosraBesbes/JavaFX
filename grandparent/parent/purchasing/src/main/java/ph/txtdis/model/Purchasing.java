package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Purchasing extends AbstractOrder<PurchasingDetail> {

    private static final long serialVersionUID = -5606817850562768621L;

    @OneToMany(mappedBy = "purchasing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasingDetail> details;

    public Purchasing() {
    }

    public Purchasing(Customer partner, LocalDate orderDate) {
        this.partner = partner;
        this.orderDate = orderDate;
    }

    @Override
    public List<PurchasingDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<PurchasingDetail> details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((details == null) ? 0 : details.hashCode());
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
        Purchasing other = (Purchasing) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getPartner() + " on " + getOrderDate();
    }
}
