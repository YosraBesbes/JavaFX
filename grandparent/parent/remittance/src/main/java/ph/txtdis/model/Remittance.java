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

    public Remittance(Customer partner, RemittanceType type, String reference, BigDecimal amount,
            LocalDate orderDate) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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
}
