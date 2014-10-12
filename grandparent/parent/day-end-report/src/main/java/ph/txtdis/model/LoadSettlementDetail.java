package ph.txtdis.model;

import java.math.BigDecimal;

public class LoadSettlementDetail {

    private Item item;

    private BigDecimal pickedQty, soldQty, returnedQty, adjustmentQty;

    private String actionTaken;

    public LoadSettlementDetail(Item item, Double pickedQty, Double soldQty, Double returnedQty,
            BigDecimal adjustmentQty, String actionTaken) {
        this.item = item;
        this.pickedQty = toBigDecimal(pickedQty);
        this.soldQty = toBigDecimal(soldQty);
        this.returnedQty = toBigDecimal(returnedQty);
        this.adjustmentQty = adjustmentQty;
        this.actionTaken = actionTaken;
    }

    private BigDecimal toBigDecimal(Double qty) {
        return qty == null ? null : new BigDecimal(qty);
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getPickedQty() {
        return pickedQty == null ? BigDecimal.ZERO : pickedQty;
    }

    public BigDecimal getSoldQty() {
        return soldQty == null ? BigDecimal.ZERO : soldQty;
    }

    public BigDecimal getReturnedQty() {
        return returnedQty == null ? BigDecimal.ZERO : returnedQty;
    }

    public BigDecimal getVarianceQty() {
        return getSoldQty().add(getReturnedQty()).subtract(getPickedQty());
    }

    public BigDecimal getAdjustmentQty() {
        return adjustmentQty == null ? BigDecimal.ZERO : adjustmentQty;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentQty == null) ? 0 : adjustmentQty.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((actionTaken == null) ? 0 : actionTaken.hashCode());
        result = prime * result + ((pickedQty == null) ? 0 : pickedQty.hashCode());
        result = prime * result + ((returnedQty == null) ? 0 : returnedQty.hashCode());
        result = prime * result + ((soldQty == null) ? 0 : soldQty.hashCode());
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
        LoadSettlementDetail other = (LoadSettlementDetail) obj;
        if (adjustmentQty == null) {
            if (other.adjustmentQty != null)
                return false;
        } else if (!adjustmentQty.equals(other.adjustmentQty))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (actionTaken == null) {
            if (other.actionTaken != null)
                return false;
        } else if (!actionTaken.equals(other.actionTaken))
            return false;
        if (pickedQty == null) {
            if (other.pickedQty != null)
                return false;
        } else if (!pickedQty.equals(other.pickedQty))
            return false;
        if (returnedQty == null) {
            if (other.returnedQty != null)
                return false;
        } else if (!returnedQty.equals(other.returnedQty))
            return false;
        if (soldQty == null) {
            if (other.soldQty != null)
                return false;
        } else if (!soldQty.equals(other.soldQty))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoadSettlementDetail [item=" + item + ", pickedQty=" + pickedQty + ", soldQty=" + soldQty
                + ", returnedQty=" + returnedQty + ", adjustmentQty=" + adjustmentQty + ", justification="
                + actionTaken + "]";
    }
}
