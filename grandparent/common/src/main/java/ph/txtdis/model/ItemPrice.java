package ph.txtdis.model;

import java.math.BigDecimal;

public class ItemPrice {

    private int id;

    private String name;

    private String description;

    private BigDecimal price;

    public ItemPrice(int id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPriceValue() {
        return price == null ? BigDecimal.ZERO : price;
    }
}
