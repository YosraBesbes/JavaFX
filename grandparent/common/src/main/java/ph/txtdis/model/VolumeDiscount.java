package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;

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

    public VolumeDiscount() {
    }

    public VolumeDiscount(Item item, VolumeDiscountType type, UomType uom, int cutOff, BigDecimal discount,
            LocalDate startDate) {
        this.item = item;
        this.type = type;
        this.uom = uom;
        this.cutOff = cutOff;
        this.discount = discount;
        this.startDate = startDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public VolumeDiscountType getVolumeDiscountType() {
        return type;
    }

    public void setVolumeDiscountType(VolumeDiscountType type) {
        this.type = type;
    }

    public VolumeDiscountType getType() {
        return type;
    }

    public void setType(VolumeDiscountType type) {
        this.type = type;
    }

    public UomType getUom() {
        return uom;
    }

    public void setUom(UomType uom) {
        this.uom = uom;
    }

    public int getCutOff() {
        return cutOff;
    }

    public void setCutOff(int cutOff) {
        this.cutOff = cutOff;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Channel getChannelLimit() {
        return channelLimit;
    }

    public void setChannelLimit(Channel channelLimit) {
        this.channelLimit = channelLimit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((channelLimit == null) ? 0 : channelLimit.hashCode());
        result = prime * result + cutOff;
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((uom == null) ? 0 : uom.hashCode());
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
        VolumeDiscount other = (VolumeDiscount) obj;
        if (channelLimit == null) {
            if (other.channelLimit != null)
                return false;
        } else if (!channelLimit.equals(other.channelLimit))
            return false;
        if (cutOff != other.cutOff)
            return false;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (type != other.type)
            return false;
        if (uom != other.uom)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return item + ": less ₱" + discount + type() + cutOff + uom + " starting " + startDate;
    }

    private String type() {
        return type == VolumeDiscountType.SET ? " per set of " : " when ";
    }
}
