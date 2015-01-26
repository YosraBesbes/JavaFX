package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.UomType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PurchasingDetail extends AbstractPricedDetail {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Purchasing purchasing;

    private String justification;

    @Transient
    private String daysLevel;

    public PurchasingDetail(Purchasing purchasing, Item item, UomType uom, BigDecimal qty, BigDecimal price,
            Quality quality, String daysLevel, String justification) {
        this.purchasing = purchasing;
        this.item = item;
        this.uom = uom;
        this.qty = qty;
        this.price = price;
        this.quality = quality;
        this.daysLevel = daysLevel;
        this.justification = justification;
    }

    @Override
    public String toString() {
        return purchasing + ": " + qty + uom + " " + quality + " " + item;
    }
}
