package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

@Entity
public class InvoicingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 5296998573316461332L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoicing;

    protected InvoicingDetail() {
    }
    
    public InvoicingDetail(Invoicing invoicing, Item item, UomType uom, BigDecimal qty) {
        this.invoicing = invoicing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return invoicing + ": " + qty + uom + " " + (quality == null ? QualityType.GOOD : quality) + " " + item;
    }
}
