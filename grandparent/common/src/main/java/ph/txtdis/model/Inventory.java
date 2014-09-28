package ph.txtdis.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ph.txtdis.util.DIS;

public class Inventory {

    private Item item;

    private Quality quality;

    private BigDecimal startQty, startAdjustQty, inQty, outQty, avg4wkSoldQty;

    public Inventory(Item item, Quality quality, BigDecimal startQty, BigDecimal startAdjustQty, BigDecimal inQty,
            BigDecimal outQty, BigDecimal avg4wkSoldQty) {
        this.item = item;
        this.quality = quality;
        this.startQty = startQty;
        this.startAdjustQty = startAdjustQty;
        this.inQty = inQty;
        this.outQty = outQty;
        this.avg4wkSoldQty = avg4wkSoldQty;
    }

    public Item getItem() {
        return item;
    }

    public int getItemId() {
        return item == null ? 0 : item.getId();
    }

    public Quality getQualityType() {
        return quality;
    }

    private BigDecimal getStartQty() {
        return startQty == null ? BigDecimal.ZERO : startQty;
    }

    private BigDecimal getStartAdjustQty() {
        return startAdjustQty == null ? BigDecimal.ZERO : startAdjustQty;
    }

    public BigDecimal getBeginQty() {
        return getStartQty().add(getStartAdjustQty());
    }

    public BigDecimal getInQty() {
        return inQty == null ? BigDecimal.ZERO : inQty;
    }

    public BigDecimal getOutQty() {
        return outQty == null ? BigDecimal.ZERO : outQty;
    }

    public BigDecimal getEndQty() {
        return getBeginQty().add(getInQty()).subtract(getOutQty());
    }

    private BigDecimal getAvg4wkSoldQty() {
        return avg4wkSoldQty == null ? new BigDecimal(0.0001) : avg4wkSoldQty;
    }

    public String getDaysLevel() {
        int daysLevel = getEndQty().divide(getAvg4wkSoldQty(), 0, RoundingMode.HALF_EVEN).intValue();
        return daysLevel > 365 ? ">365" : DIS.formatInt(daysLevel);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((avg4wkSoldQty == null) ? 0 : avg4wkSoldQty.hashCode());
        result = prime * result + ((inQty == null) ? 0 : inQty.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((outQty == null) ? 0 : outQty.hashCode());
        result = prime * result + ((quality == null) ? 0 : quality.hashCode());
        result = prime * result + ((startAdjustQty == null) ? 0 : startAdjustQty.hashCode());
        result = prime * result + ((startQty == null) ? 0 : startQty.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Inventory other = (Inventory) obj;
        if (avg4wkSoldQty == null) {
            if (other.avg4wkSoldQty != null)
                return false;
        } else if (!avg4wkSoldQty.equals(other.avg4wkSoldQty))
            return false;
        if (inQty == null) {
            if (other.inQty != null)
                return false;
        } else if (!inQty.equals(other.inQty))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (outQty == null) {
            if (other.outQty != null)
                return false;
        } else if (!outQty.equals(other.outQty))
            return false;
        if (quality == null) {
            if (other.quality != null)
                return false;
        } else if (!quality.equals(other.quality))
            return false;
        if (startAdjustQty == null) {
            if (other.startAdjustQty != null)
                return false;
        } else if (!startAdjustQty.equals(other.startAdjustQty))
            return false;
        if (startQty == null) {
            if (other.startQty != null)
                return false;
        } else if (!startQty.equals(other.startQty))
            return false;
        return true;
    }
}
