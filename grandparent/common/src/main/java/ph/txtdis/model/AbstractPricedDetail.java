package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class AbstractPricedDetail extends AbstractOrderDetail implements Priced {

    private static final long serialVersionUID = 257754573072417395L;

    @Column(nullable = false)
    protected BigDecimal price = BigDecimal.ZERO;

    @Override
    public BigDecimal getSubtotal() {
        return getQty().multiply(getPrice());
    }
}
