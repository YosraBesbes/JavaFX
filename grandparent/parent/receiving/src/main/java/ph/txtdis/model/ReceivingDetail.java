package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class ReceivingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = -6383895333116700458L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Receiving receiving;

    protected ReceivingDetail() {
    }

    public ReceivingDetail(Receiving receiving, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.receiving = receiving;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((receiving == null) ? 0 : receiving.hashCode());
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
        ReceivingDetail other = (ReceivingDetail) obj;
        if (receiving == null) {
            if (other.receiving != null)
                return false;
        } else if (!receiving.equals(other.receiving))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return receiving + ": " + qty + uom + " " + quality + " " + item;
    }
}
