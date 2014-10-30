package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.ItemPrice;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.UomType;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query("select min(i.id) from Item i")
    int getMinId();

    @Query("select max(i.id) from Item i")
    int getMaxId();

    @Query("select i.qtyPerUom from Item i where i.id = ?1")
    List<QtyPerUom> getQtyPerUom(int id);

    @Query("select i.priceHistory from Item i where i.id = ?1")
    List<Pricing> getPriceHistory(int id);

    @Query("select i.volumeDiscounts from Item i where i.id = ?1")
    List<VolumeDiscount> getVolumeDiscounts(int id);

    @Query("select i.boms from Item i where i.id = ?1")
    List<Bom> getBoms(int id);

    @Query("select q.uom from QtyPerUom q where q.item = ?1 and q.isPurchased = true")
    List<UomType> getPurchasingUoms(Item item);

    @Query("select q.uom from QtyPerUom q where q.item = ?1 and q.isSold = true")
    List<UomType> getSellingUoms(Item item);

    @Query("select q.uom from QtyPerUom q where q.item = ?1 and q.isReported = true")
    List<UomType> getReportingUoms(Item item);

    @Query("select new ph.txtdis.model.ItemPrice(i.id, i.name, i.description, "
            + "   (select distinct p.price from Pricing p where p.item = i and p.type = ph.txtdis.type.PricingType.LIST "
            + "       and p.startDate = "
            + "          (select max(p.startDate) from Pricing p where p.type = ph.txtdis.type.PricingType.LIST and "
            + "                  p.item = i))) from Item i where i.disabledBy is null ")
    List<ItemPrice> getItemsWithTheirLatestPrices();

    List<Item> findByDescriptionContaining(String description);

    List<Item> findByName(String name);
}
