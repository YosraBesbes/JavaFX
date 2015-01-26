package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class RemittanceDetail extends AbstractAudited {

    private static final long serialVersionUID = 7471155507338324274L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Remittance remittance;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoicing;

    @Column(nullable = false)
    private BigDecimal unpaidValue;

    @Column(nullable = false)
    private BigDecimal paymentValue;

    public BigDecimal getBalanceValue() {
        return getPaymentValue().subtract(getUnpaidValue());
    }

    @Override
    public String toString() {
        return getRemittance() + ": Payment of " + getPaymentValue() + " for S/I No. " + invoicing.getId() + "'s "
                + getUnpaidValue() + " balance";
    }
}
