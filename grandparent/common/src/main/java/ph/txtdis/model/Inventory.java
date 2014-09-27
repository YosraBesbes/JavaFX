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
}
