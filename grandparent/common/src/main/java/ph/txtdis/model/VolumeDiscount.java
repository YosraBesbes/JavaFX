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
    public String toString() {
        return item + ": less ₱" + discount + type() + cutOff + uom + " starting " + startDate;
    }
    
    private String type() {
        return type == VolumeDiscountType.SET ? " per set of " : " when ";
    }
}
