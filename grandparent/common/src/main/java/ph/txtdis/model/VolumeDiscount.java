package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class VolumeDiscount extends AbstractAudited {

    private static final long serialVersionUID = 7710563453808768120L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Column(nullable = false)
    private VolumeDiscountType type;

    @Column(nullable = false)
    private UomType uom;

    @Column(nullable = false)
    private int cutOff;

    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal discount;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate startDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Channel channelLimit;

    public VolumeDiscount(Item item, VolumeDiscountType type, UomType uom, int cutOff, BigDecimal discount,
            LocalDate startDate) {
        this.item = item;
        this.type = type;
        this.uom = uom;
        this.cutOff = cutOff;
        this.discount = discount;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return item + ": less ₱" + discount + type() + cutOff + uom + " starting " + startDate;
    }

    private String type() {
        return type == VolumeDiscountType.SET ? " per set of " : " when ";
    }
}
