package ph.txtdis.model;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VolumeSummary {

    private Item item;

    private BigDecimal qty = BigDecimal.ZERO;

    public int getItemId() {
        return item == null ? 0 : item.getId();
    }

    public BigDecimal getVol() {
        return getQty();
    }
}
