package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import ph.txtdis.type.RemittanceType;

@Entity
public class Remittance extends AbstractAudited implements Remitted {

    private static final long serialVersionUID = -5860334462169889589L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Customer partner;

    @Transient
    private int partnerId;

    @Transient
    private String partnerName;

    @Transient
    private String partnerAddress;

    @Column(nullable = false)
    private RemittanceType type;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    private String remarks;

    @OneToMany(mappedBy = "remittance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RemittanceDetail> details;

    public Remittance() {
    }

    public Remittance(Customer partner, RemittanceType type, String reference, BigDecimal amount, LocalDate orderDate) {
        this.partner = partner;
        this.type = type;
        this.reference = reference;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public Customer getPartner() {
        return partner;
    }

    public void setPartner(Customer partner) {
        this.partner = partner;
    }

    public int getPartnerId() {
        return partner == null ? 0 : partner.getId();
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String getPartnerName() {
        return partner == null ? null : partner.getName();
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerAddress() {
        return partner == null ? null : partner.getFullAdddress();
    }

    public void setPartnerAddress(String partnerAddress) {
        this.partnerAddress = partnerAddress;
    }

    public RemittanceType getType() {
        return type;
    }

    public void setType(RemittanceType type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<RemittanceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RemittanceDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return partner + " on " + orderDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + ((partner == null) ? 0 : partner.hashCode());
        result = prime * result + ((partnerAddress == null) ? 0 : partnerAddress.hashCode());
        result = prime * result + partnerId;
        result = prime * result + ((partnerName == null) ? 0 : partnerName.hashCode());
        result = prime * result + ((reference == null) ? 0 : reference.hashCode());
        result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Remittance other = (Remittance) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
            return false;
        if (partner == null) {
            if (other.partner != null)
                return false;
        } else if (!partner.equals(other.partner))
            return false;
        if (partnerAddress == null) {
            if (other.partnerAddress != null)
                return false;
        } else if (!partnerAddress.equals(other.partnerAddress))
            return false;
        if (partnerId != other.partnerId)
            return false;
        if (partnerName == null) {
            if (other.partnerName != null)
                return false;
        } else if (!partnerName.equals(other.partnerName))
            return false;
        if (reference == null) {
            if (other.reference != null)
                return false;
        } else if (!reference.equals(other.reference))
            return false;
        if (remarks == null) {
            if (other.remarks != null)
                return false;
        } else if (!remarks.equals(other.remarks))
            return false;
        if (type != other.type)
            return false;
        return true;
    }
}
