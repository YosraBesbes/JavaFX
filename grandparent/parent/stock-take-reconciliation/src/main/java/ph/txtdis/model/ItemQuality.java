package ph.txtdis.model;

public class ItemQuality {

    private Item item;

    private Quality quality;

    protected ItemQuality() {
    }

    public ItemQuality(Item item, Quality quality) {
        this.item = item;
        this.quality = quality;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}
