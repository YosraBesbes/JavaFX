package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class InvoicingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 5296998573316461332L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoicing;

    protected InvoicingDetail() {
    }

    public InvoicingDetail(Invoicing invoicing, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.invoicing = invoicing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((invoicing == null) ? 0 : invoicing.hashCode());
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
        InvoicingDetail other = (InvoicingDetail) obj;
        if (invoicing == null) {
            if (other.invoicing != null)
                return false;
        } else if (!invoicing.equals(other.invoicing))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return invoicing + ": " + qty + uom + " " + (quality == null ? "GOOD" : quality) + " " + item + " @P"
                + getPrice() + " each";
    }
}
