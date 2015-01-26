package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.UomType;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class ReceivingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = -6383895333116700458L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Receiving receiving;

    public ReceivingDetail(Receiving receiving, Item item, UomType uom, BigDecimal qty, Quality quality) {
        this.receiving = receiving;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return receiving + ": " + qty + uom + " " + quality + " " + item;
    }
}
