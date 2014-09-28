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
public class Customer extends AbstractAudited implements Named {

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

    @Override
    public String getName() {
        return name;
    }

    @Override
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((barangay == null) ? 0 : barangay.hashCode());
        result = prime * result + ((channel == null) ? 0 : channel.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((contactTitle == null) ? 0 : contactTitle.hashCode());
        result = prime * result + ((creditContactName == null) ? 0 : creditContactName.hashCode());
        result = prime * result + ((creditContactSurname == null) ? 0 : creditContactSurname.hashCode());
        result = prime * result + ((creditDetails == null) ? 0 : creditDetails.hashCode());
        result = prime * result + ((discounts == null) ? 0 : discounts.hashCode());
        result = prime * result + (int) (mobile ^ (mobile >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((province == null) ? 0 : province.hashCode());
        result = prime * result + ((routeHistory == null) ? 0 : routeHistory.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((visitFrequency == null) ? 0 : visitFrequency.hashCode());
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
        Customer other = (Customer) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (barangay == null) {
            if (other.barangay != null)
                return false;
        } else if (!barangay.equals(other.barangay))
            return false;
        if (channel == null) {
            if (other.channel != null)
                return false;
        } else if (!channel.equals(other.channel))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (contactTitle == null) {
            if (other.contactTitle != null)
                return false;
        } else if (!contactTitle.equals(other.contactTitle))
            return false;
        if (creditContactName == null) {
            if (other.creditContactName != null)
                return false;
        } else if (!creditContactName.equals(other.creditContactName))
            return false;
        if (creditContactSurname == null) {
            if (other.creditContactSurname != null)
                return false;
        } else if (!creditContactSurname.equals(other.creditContactSurname))
            return false;
        if (creditDetails == null) {
            if (other.creditDetails != null)
                return false;
        } else if (!creditDetails.equals(other.creditDetails))
            return false;
        if (discounts == null) {
            if (other.discounts != null)
                return false;
        } else if (!discounts.equals(other.discounts))
            return false;
        if (mobile != other.mobile)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (province == null) {
            if (other.province != null)
                return false;
        } else if (!province.equals(other.province))
            return false;
        if (routeHistory == null) {
            if (other.routeHistory != null)
                return false;
        } else if (!routeHistory.equals(other.routeHistory))
            return false;
        if (type != other.type)
            return false;
        if (visitFrequency != other.visitFrequency)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
