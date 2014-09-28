package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractPricedDetail extends AbstractOrderDetail implements Priced {

    private static final long serialVersionUID = 257754573072417395L;

    @Column(nullable = false)
    private BigDecimal price;

    @Transient
    private BigDecimal subtotal;

    protected AbstractPricedDetail() {
    }

    @Override
    public BigDecimal getPrice() {
        return price == null ? BigDecimal.ZERO : price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getSubtotal() {
        return subtotal == null ? getQty().multiply(getPrice()) : subtotal;
    }

    @Override
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((subtotal == null) ? 0 : subtotal.hashCode());
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
        AbstractPricedDetail other = (AbstractPricedDetail) obj;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (subtotal == null) {
            if (other.subtotal != null)
                return false;
        } else if (!subtotal.equals(other.subtotal))
            return false;
        return true;
    }
}
