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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
