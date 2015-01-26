package ph.txtdis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Customer extends AbstractDisabledNamed implements Disable, Named {

    private static final long serialVersionUID = 2888326520266278498L;

    private String street;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location barangay;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location city;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location province;

    private CustomerType type;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Channel channel;

    private VisitFrequency visitFrequency;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Routing> routeHistory;

    private String creditContactName, creditContactSurname, contactTitle;

    @Type(type = "org.jadira.usertype.phonenumber.PersistentE164PhoneNumberWithExtension")
    private PhoneNumber mobile;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditDetail> creditDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerDiscount> discounts;

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }

    public String getFullAdddress() {
        return street + ", " + barangay + ", " + city + ", " + province;
    }

    @Override
    public String toString() {
        return name;
    }
}
