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
}
