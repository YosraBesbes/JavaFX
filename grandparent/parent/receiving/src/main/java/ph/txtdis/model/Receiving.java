package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ph.txtdis.type.ReceivingReferenceType;

@Entity
public class Receiving extends AbstractOrder<ReceivingDetail> {

    private static final long serialVersionUID = 1717512766392903432L;

    private long partnerReferenceId;

    @Column(nullable = false)
    private ReceivingReferenceType reference;

    @Column(nullable = false)
    private int referenceId;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser checker;

    @OneToMany(mappedBy = "receiving", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceivingDetail> details;

    public Receiving() {
    }

    public Receiving(Customer partner, ReceivingReferenceType reference, int referenceId, SystemUser checker,
            LocalDate orderDate) {
        this.partner = partner;
        this.reference = reference;
        this.referenceId = referenceId;
        this.checker = checker;
        this.orderDate = orderDate;
    }

    public SystemUser getChecker() {
        return checker;
    }

    public void setChecker(SystemUser checker) {
        this.checker = checker;
    }

    public long getPartnerReferenceId() {
        return partnerReferenceId;
    }

    public void setPartnerReferenceId(long partnerReferenceId) {
        this.partnerReferenceId = partnerReferenceId;
    }

    public ReceivingReferenceType getReference() {
        return reference;
    }

    public void setReference(ReceivingReferenceType reference) {
        this.reference = reference;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public List<ReceivingDetail> getDetails() {
        return details;
    }

    @Override
    public void setDetails(List<ReceivingDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return getPartner() + "'s " + reference + " " + referenceId + " on " + orderDate;
    }
}
