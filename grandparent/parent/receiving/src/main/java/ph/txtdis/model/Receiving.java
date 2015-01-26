package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.ReceivingReferenceType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Receiving extends AbstractOrder<ReceivingDetail> {

    private static final long serialVersionUID = 1717512766392903432L;

    private long partnerReferenceId;

    @Column(nullable = false)
    private ReceivingReferenceType reference;

    @Column(nullable = false)
    private int referenceId;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users checker;

    @OneToMany(mappedBy = "receiving", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceivingDetail> details;

    public Receiving(Customer partner, ReceivingReferenceType reference, int referenceId, Users checker,
            LocalDate orderDate) {
        this.partner = partner;
        this.reference = reference;
        this.referenceId = referenceId;
        this.checker = checker;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return getPartner() + "'s " + reference + " " + referenceId + " on " + orderDate;
    }
}
