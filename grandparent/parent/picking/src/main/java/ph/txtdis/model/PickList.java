package ph.txtdis.model;

import java.math.BigDecimal;

public class PickList {

    private int itemId;

    private String itemName;

    private BigDecimal qty;

    public PickList(int itemId, String itemName, BigDecimal qty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + itemId;
        result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
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
        PickList other = (PickList) obj;
        if (itemId != other.itemId)
            return false;
        if (itemName == null) {
            if (other.itemName != null)
                return false;
        } else if (!itemName.equals(other.itemName))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PickList [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + "]";
    }
}
