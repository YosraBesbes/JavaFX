package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import ph.txtdis.util.DIS;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("select i.id, i.partner_id, "
        + "        i.value - sum(case when d.payment is null then cast(0 as numeric(7, 2)) else d.payment end) balance,"
        + "        cast ((current_date - i.order_date) as int) + "
        + "             case when c.term is null then 0 else c.term end + "
        + "             case when c.grace_period is null then 0 else c.grace_period end as day"
        + "   from invoicing i left join remittance_detail d on i.id = d.invoicing_id "
        + "        left join credit_detail c on c.id = i.credit_id "
        + "  group by i.id, i.partner_id, i.value, current_date, i.order_date, c.term, c.grace_period "
        + " having i.value - sum(case when d.payment is null then cast(0 as numeric(7, 2)) else d.payment end) > 0 ")
@Synchronize({ "invoicing", "remittance_detail", "customer" })
public class InvoiceView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer partner;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private int day;

    @Override
    public String toString() {
        return DIS.formatCurrency(balance);
    }
}
