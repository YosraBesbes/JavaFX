package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

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
@MappedSuperclass
public abstract class AbstractOrderDetail extends AbstractAudited implements ItemDetailed {

    private static final long serialVersionUID = 1774123388619693560L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Item item;

    @Column(nullable = false)
    protected UomType uom;

    @Column(nullable = false, precision = 10, scale = 4)
    protected BigDecimal qty = BigDecimal.ZERO;

    @ManyToOne(cascade = CascadeType.REFRESH)
    protected VolumeDiscount discount;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Quality quality;

    @Override
    public int getItemId() {
        return item.getId();
    }

    @Override
    public String getItemName() {
        return item.getName();
    }

    @Override
    public String getItemDescription() {
        return item.getDescription();
    }
}
