package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class AbstractOrder<D extends ItemDetailed> extends AbstractAudited implements Ordered<D> {

    private static final long serialVersionUID = 3715783817464199036L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    protected Customer partner;
    
    @Transient
    private int partnerId;
    
    @Transient
    private String partnerName;
    
    @Transient
    private String partnerAddress;
    
    @ManyToOne(cascade = CascadeType.REFRESH)
    protected Route route;
    
    private CreditDetail credit;
    
    private CustomerDiscount discount;
    
    @Column(nullable = false)
    protected BigDecimal amount;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    protected LocalDate orderDate;

    private String remarks;

    public AbstractOrder() {
    }

    @Override
    public Customer getPartner() {
        return partner;
    }

    @Override
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public CreditDetail getCredit() {
        return credit;
    }

    public void setCredit(CreditDetail credit) {
        this.credit = credit;
    }

    public CustomerDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(CustomerDiscount discount) {
        this.discount = discount;
    }

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

    @Override
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
