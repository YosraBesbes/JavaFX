package ph.txtdis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

@Entity
public class Customer extends AbstractAudited implements Typed {

    private static final long serialVersionUID = 2888326520266278498L;

    @Column(nullable = false, length = 32, unique = true)
    private String name;

    private String address;

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
    
    private long mobile;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditDetail> creditDetails;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerDiscount> discounts;

    public Customer() {
    }

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getBarangay() {
        return barangay;
    }

    public void setBarangay(Location barangay) {
        this.barangay = barangay;
    }

    public Location getCity() {
        return city;
    }

    public void setCity(Location city) {
        this.city = city;
    }

    public Location getProvince() {
        return province;
    }

    public void setProvince(Location province) {
        this.province = province;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public VisitFrequency getVisitFrequency() {
        return visitFrequency;
    }

    public void setVisitFrequency(VisitFrequency visitFrequency) {
        this.visitFrequency = visitFrequency;
    }

    public List<Routing> getRouteHistory() {
        return routeHistory;
    }

    public void setRouteHistory(List<Routing> routeHistory) {
        this.routeHistory = routeHistory;
    }

    public String getCreditContactName() {
        return creditContactName;
    }

    public void setCreditContactName(String creditContactName) {
        this.creditContactName = creditContactName;
    }

    public String getCreditContactSurname() {
        return creditContactSurname;
    }

    public void setCreditContactSurname(String creditContactSurname) {
        this.creditContactSurname = creditContactSurname;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public List<CreditDetail> getCreditDetails() {
        return creditDetails;
    }

    public void setCreditDetails(List<CreditDetail> creditDetails) {
        this.creditDetails = creditDetails;
    }

    public List<CustomerDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<CustomerDiscount> discounts) {
        this.discounts = discounts;
    }

    public String getFullAdddress() {
        return address + ", " + barangay + ", " + city + ", " + province;
   }

    @Override
    public String toString() {
        return name;
    }
}
