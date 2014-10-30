package ph.txtdis.model;

public class CustomerRoute {

    private int id;

    private String name;

    private String street;

    private Location barangay;

    private Location city;

    private Location province;

    private Route route;

    public CustomerRoute(int id, String name, String street, Location barangay, Location city, Location province,
            Route route) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public Location getBarangay() {
        return barangay;
    }

    public Location getCity() {
        return city;
    }

    public Location getProvince() {
        return province;
    }

    public Route getRoute() {
        return route;
    }
}
