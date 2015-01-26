package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.UomType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class StockTakeDetail extends AbstractAudited {

    private static final long serialVersionUID = 4692138441515885681L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private StockTake stockTake;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Quality quality;

    public Integer getItemId() {
        return item.getId();
    }

    public String getItemName() {
        return item.getName();
    }

    public String getItemDescription() {
        return item.getDescription();
    }

    @Override
    public String toString() {
        return stockTake + ": " + qty + uom + " " + quality + " " + item;
    }
}
