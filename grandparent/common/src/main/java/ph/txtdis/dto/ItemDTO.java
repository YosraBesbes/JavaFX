package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.ItemType;
import ph.txtdis.type.UomType;

public interface ItemDTO extends SearchedDTO<Item, String>, Spun, UniqueName, Named, Audited<Item> {

    ItemType getType();

    void setType(ItemType type);
    
    String getDescription();
    
    void setDescription(String description);

    long getVendorId();

    void setVendorId(long vendorId);

    ItemFamily getFamily();

    void setFamily(ItemFamily family);

    Boolean isNotDiscounted();

    void setNotDiscounted(Boolean notDiscounted);

    ObservableList<QtyPerUom> getQtyPerUom();

    void setQtyPerUom(List<QtyPerUom> qtyPerUom);

    ObservableList<Pricing> getPriceHistory();

    void setPriceHistory(List<Pricing> priceHistory);

    ObservableList<VolumeDiscount> getVolumeDiscounts();

    void setVolumeDiscounts(List<VolumeDiscount> volumeDiscounts);

    ObservableList<Bom> getBoms();

    void setBoms(List<Bom> boms);
    
    BigDecimal getLatestPurchasePrice(LocalDate date);
    
    BigDecimal getLatestSellingPrice(LocalDate date);
    
    VolumeDiscount getLatestVolumeDiscount(LocalDate date);
    
    Map<UomType, BigDecimal> getQtyPerUomMap();

    List<UomType> getPurchasingUoms();

    List<UomType> getSellingUoms();

    List<UomType> getReportingUoms();
}
