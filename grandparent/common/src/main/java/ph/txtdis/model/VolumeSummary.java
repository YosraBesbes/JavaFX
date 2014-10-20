package ph.txtdis.model;

import java.math.BigDecimal;

public class VolumeSummary {

    private Item item;

    private BigDecimal qty;

    public VolumeSummary(Item item, BigDecimal qty) {
        this.item = item;
        this.qty = qty;
    }

    public int getItemId() {
        return item == null ? 0 : item.getId();
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getQty() {
        return qty == null ? BigDecimal.ZERO : qty;
    }

    public BigDecimal getVol() {
        return qty == null ? BigDecimal.ZERO : qty;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
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
        VolumeSummary other = (VolumeSummary) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        return true;
    }
}
