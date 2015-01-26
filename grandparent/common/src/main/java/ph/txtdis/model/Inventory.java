package ph.txtdis.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ph.txtdis.util.DIS;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inventory {

    private Item item;

    private Quality quality;

    private BigDecimal startQty = BigDecimal.ZERO;

    private BigDecimal startAdjustQty = BigDecimal.ZERO;

    private BigDecimal inQty = BigDecimal.ZERO;

    private BigDecimal outQty = BigDecimal.ZERO;

    private BigDecimal avg4wkSoldQty = new BigDecimal("0.0001");

    public int getItemId() {
        return item == null ? 0 : item.getId();
    }

    public BigDecimal getBeginQty() {
        return getStartQty().add(getStartAdjustQty());
    }

    public BigDecimal getEndQty() {
        return getBeginQty().add(getInQty()).subtract(getOutQty());
    }

    public String getDaysLevel() {
        int daysLevel = getEndQty().divide(getAvg4wkSoldQty(), 0, RoundingMode.HALF_EVEN).intValue();
        return daysLevel > 365 ? ">365" : DIS.formatInt(daysLevel);
    }
}
