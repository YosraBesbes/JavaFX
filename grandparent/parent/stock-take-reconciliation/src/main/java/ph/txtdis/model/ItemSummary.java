package ph.txtdis.model;

import java.math.BigDecimal;

public class ItemSummary {

    private Item item;

    private Quality quality;

    private BigDecimal startQty, inQty, outQty, endQty;

    protected ItemSummary() {
    }

    public ItemSummary(Item item, Quality quality, BigDecimal startQty, BigDecimal inQty, BigDecimal outQty,
            BigDecimal endQty) {
        this.item = item;
        this.quality = quality;
        this.startQty = startQty;
        this.inQty = inQty;
        this.outQty = outQty;
        this.endQty = endQty;
    }

    public Integer getItemId() {
        return item == null ? null : item.getId();
    }

    public Item getItem() {
        return item;
    }

    public Quality getQuality() {
        return quality;
    }

    public BigDecimal getStartQty() {
        return startQty;
    }

    public BigDecimal getInQty() {
        return inQty;
    }

    public BigDecimal getOutQty() {
        return outQty;
    }

    public BigDecimal getEndQty() {
        return endQty;
    }
}
