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
}
