package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import ph.txtdis.util.DIS;

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

    protected InvoiceView() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + day;
        result = prime * result + id;
        result = prime * result + ((partner == null) ? 0 : partner.hashCode());
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
        InvoiceView other = (InvoiceView) obj;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (day != other.day)
            return false;
        if (id != other.id)
            return false;
        if (partner == null) {
            if (other.partner != null)
                return false;
        } else if (!partner.equals(other.partner))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return DIS.formatCurrency(balance);
    }
}
