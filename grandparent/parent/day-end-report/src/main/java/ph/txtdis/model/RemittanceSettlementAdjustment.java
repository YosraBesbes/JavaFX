package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import ph.txtdis.util.DIS;

@Entity
public class RemittanceSettlementAdjustment extends AbstractAudited {

    private static final long serialVersionUID = 4362180227772097988L;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate pickDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Truck truck;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Invoicing invoice;

    @Column(precision = 10, scale = 4)
    private BigDecimal value;

    @Column(nullable = false)
    private String actionTaken;

    protected RemittanceSettlementAdjustment() {
    }

    public RemittanceSettlementAdjustment(LocalDate pickDate, Truck truck, Invoicing invoicing, BigDecimal value,
            String actionTaken) {
        this.pickDate = pickDate;
        this.truck = truck;
        this.invoice = invoicing;
        this.value = value;
        this.actionTaken = actionTaken;
    }

    public LocalDate getPickDate() {
        return pickDate;
    }

    public void setPickDate(LocalDate pickDate) {
        this.pickDate = pickDate;
    }

    public Invoicing getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoicing invoice) {
        this.invoice = invoice;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public BigDecimal getTotalValue() {
        return value == null ? BigDecimal.ZERO : value;
    }

    public void setTotalValue(BigDecimal value) {
        this.value = value;
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
        int result = super.hashCode();
        result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
        result = prime * result + ((actionTaken == null) ? 0 : actionTaken.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((pickDate == null) ? 0 : pickDate.hashCode());
        result = prime * result + ((truck == null) ? 0 : truck.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RemittanceSettlementAdjustment other = (RemittanceSettlementAdjustment) obj;
        if (invoice == null) {
            if (other.invoice != null)
                return false;
        } else if (!invoice.equals(other.invoice))
            return false;
        if (actionTaken == null) {
            if (other.actionTaken != null)
                return false;
        } else if (!actionTaken.equals(other.actionTaken))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (pickDate == null) {
            if (other.pickDate != null)
                return false;
        } else if (!pickDate.equals(other.pickDate))
            return false;
        if (truck == null) {
            if (other.truck != null)
                return false;
        } else if (!truck.equals(other.truck))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return DIS.formatQuantity(value);
    }
}
