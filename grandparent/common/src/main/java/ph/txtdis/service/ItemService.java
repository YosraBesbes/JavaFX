package ph.txtdis.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.ItemPrice;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;

public interface ItemService extends SearchedSpunService<Item, Integer, String>, Unique {

    List<QtyPerUom> getQtyPerUom(int id);

    List<Pricing> getPriceHistory(int id);

    List<VolumeDiscount> getVolumeDiscounts(int id);

    List<Bom> getBoms(int id);

    BigDecimal getLatestPrice(Item item, LocalDate date, PricingType type);

    VolumeDiscount getLatestVolumeDiscount(Item item, LocalDate date);

    Map<UomType, BigDecimal> getQtyPerUomMap(int id);

    List<UomType> getPurchasingUoms(Item item);

    List<UomType> getSellingUoms(Item item);

    List<UomType> getReportingUoms(Item item);

    List<ItemPrice> list();
}