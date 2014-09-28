package ph.txtdis.model;

import java.math.BigDecimal;

public class StockTakeReconciliationDetail {

    private Item item;

    private Quality quality;

    private BigDecimal startQty, startAdjustQty, inQty, outQty, countQty, adjustmentQty;

    private String justification;

    public StockTakeReconciliationDetail(Item item, Quality quality, BigDecimal startQty, BigDecimal startAdjustQty,
            BigDecimal inQty, BigDecimal outQty, BigDecimal countQty, BigDecimal countAdjustQty, String justification) {
        this.item = item;
        this.quality = quality;
        this.startQty = startQty;
        this.startAdjustQty = startAdjustQty;
        this.inQty = inQty;
        this.outQty = outQty;
        this.countQty = countQty;
        this.adjustmentQty = countAdjustQty;
        this.justification = justification;
    }

    public Item getItem() {
        return item;
    }

    public Quality getQuality() {
        return quality;
    }

    public BigDecimal getStartQty() {
        return startQty == null ? BigDecimal.ZERO : startQty;
    }

    public BigDecimal getStartAdjustQty() {
        return startAdjustQty == null ? BigDecimal.ZERO : startAdjustQty;
    }

    public BigDecimal getInQty() {
        return inQty == null ? BigDecimal.ZERO : inQty;
    }

    public BigDecimal getOutQty() {
        return outQty == null ? BigDecimal.ZERO : outQty;
    }

    public BigDecimal getSystemQty() {
        return getStartQty().add(getStartAdjustQty()).add(getInQty()).subtract(getOutQty());
    }

    public BigDecimal getCountQty() {
        return countQty == null ? BigDecimal.ZERO : countQty;
    }

    public BigDecimal getAdjustmentQty() {
        return adjustmentQty == null ? BigDecimal.ZERO : adjustmentQty;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentQty == null) ? 0 : adjustmentQty.hashCode());
        result = prime * result + ((countQty == null) ? 0 : countQty.hashCode());
        result = prime * result + ((inQty == null) ? 0 : inQty.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((justification == null) ? 0 : justification.hashCode());
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
        StockTakeReconciliationDetail other = (StockTakeReconciliationDetail) obj;
        if (adjustmentQty == null) {
            if (other.adjustmentQty != null)
                return false;
        } else if (!adjustmentQty.equals(other.adjustmentQty))
            return false;
        if (countQty == null) {
            if (other.countQty != null)
                return false;
        } else if (!countQty.equals(other.countQty))
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
        if (justification == null) {
            if (other.justification != null)
                return false;
        } else if (!justification.equals(other.justification))
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

    @Override
    public String toString() {
        return "StockTakeReconciliationDetail [item=" + item + ", quality=" + quality + ", startQty=" + startQty
                + ", startAdjustQty=" + startAdjustQty + ", inQty=" + inQty + ", outQty=" + outQty + ", countQty="
                + countQty + ", adjustmentQty=" + adjustmentQty + ", justification=" + justification + "]";
    }
}
