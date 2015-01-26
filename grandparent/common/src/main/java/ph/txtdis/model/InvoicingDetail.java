package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.UomType;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InvoicingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 5296998573316461332L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoicing;

    public InvoicingDetail(Invoicing invoicing, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.invoicing = invoicing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return invoicing + ": " + qty + uom + " " + (quality == null ? "GOOD" : quality) + " " + item + " @P"
                + getPrice() + " each";
    }
}
