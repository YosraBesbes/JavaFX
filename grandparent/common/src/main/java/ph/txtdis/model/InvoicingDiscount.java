package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class InvoicingDiscount extends AbstractAudited implements CustomerDiscounted {

    private static final long serialVersionUID = 6426211122117658687L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoicing;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false, precision = 7, scale = 4)
    private BigDecimal perCent;

    @Column(nullable = false)
    protected BigDecimal value;

    public InvoicingDiscount() {
    }

    public InvoicingDiscount(Invoicing invoicing, int level, BigDecimal perCent, BigDecimal value) {
        this.invoicing = invoicing;
        this.level = level;
        this.perCent = perCent;
        this.value = value;
    }

    public Invoicing getInvoicing() {
        return invoicing;
    }

    public void setInvoicing(Invoicing invoicing) {
        this.invoicing = invoicing;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public BigDecimal getPerCent() {
        return perCent;
    }

    @Override
    public void setPerCent(BigDecimal perCent) {
        this.perCent = perCent;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((invoicing == null) ? 0 : invoicing.hashCode());
        result = prime * result + level;
        result = prime * result + ((perCent == null) ? 0 : perCent.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        InvoicingDiscount other = (InvoicingDiscount) obj;
        if (invoicing == null) {
            if (other.invoicing != null)
                return false;
        } else if (!invoicing.equals(other.invoicing))
            return false;
        if (level != other.level)
            return false;
        if (perCent == null) {
            if (other.perCent != null)
                return false;
        } else if (!perCent.equals(other.perCent))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return invoicing + ": less " + perCent + "%, valued " + value;
    }
}
