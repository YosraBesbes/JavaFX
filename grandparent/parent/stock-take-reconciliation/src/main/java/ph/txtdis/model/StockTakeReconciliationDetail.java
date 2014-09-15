package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class StockTakeReconciliationDetail extends AbstractAudited {

    private static final long serialVersionUID = 2847149128475511326L;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    private StockTakeReconciliation reconciliation;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @Transient
    private int itemId;

    @Transient
    private String itemName;

    @Column(nullable = false)
    private Quality quality;

    @Transient
    private BigDecimal startQty = BigDecimal.ZERO;

    @Transient
    private BigDecimal inQty = BigDecimal.ZERO;

    @Transient
    private BigDecimal outQty = BigDecimal.ZERO;

    @Transient
    private BigDecimal countQty = BigDecimal.ZERO;

    @Transient
    private BigDecimal variance = BigDecimal.ZERO;

    @Column(precision = 10, scale = 4)
    private BigDecimal adjustQty;

    private String justification;

    @Column(precision = 10, scale = 4)
    private BigDecimal finalQty;

    protected StockTakeReconciliationDetail() {
    }

    public StockTakeReconciliationDetail(StockTakeReconciliation reconciliation, Item item, int itemId,
            String itemName, Quality quality, BigDecimal startQty, BigDecimal inQty, BigDecimal outQty,
            BigDecimal countQty, BigDecimal variance, BigDecimal adjustQty, String justification, BigDecimal finalQty) {
        this.reconciliation = reconciliation;
        this.item = item;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quality = quality;
        this.startQty = startQty;
        this.inQty = inQty;
        this.outQty = outQty;
        this.countQty = countQty;
        this.variance = variance;
        this.adjustQty = adjustQty;
        this.justification = justification;
        this.finalQty = finalQty;
    }

    public StockTakeReconciliation getReconciliation() {
        return reconciliation;
    }

    public void setReconciliation(StockTakeReconciliation reconciliation) {
        this.reconciliation = reconciliation;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemId() {
        return item.getId();
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return item.getName();
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public BigDecimal getStartQty() {
        return startQty;
    }

    public void setStartQty(BigDecimal startQty) {
        this.startQty = startQty;
        computeVariance();
    }

    public BigDecimal getInQty() {
        return inQty;
    }

    public void setInQty(BigDecimal inQty) {
        this.inQty = inQty;
        computeVariance();
    }

    public BigDecimal getOutQty() {
        return outQty;
    }

    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
        computeVariance();
    }

    public BigDecimal getCountQty() {
        return countQty;
    }

    public void setCountQty(BigDecimal countQty) {
        this.countQty = countQty;
        computeVariance();
    }

    public BigDecimal getVariance() {
        return variance;
    }

    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    private void computeVariance() {
        variance = startQty.add(inQty).subtract(outQty).subtract(countQty);
    }

    public BigDecimal getAdjustQty() {
        return adjustQty;
    }

    public void setAdjustQty(BigDecimal adjustQty) {
        this.adjustQty = adjustQty;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public BigDecimal getFinalQty() {
        return finalQty;
    }

    public void setFinalQty(BigDecimal finalQty) {
        this.finalQty = finalQty;
    }

    @Override
    public String toString() {
        return reconciliation + ": " + finalQty + "PC " + quality + " " + item;
    }
}
