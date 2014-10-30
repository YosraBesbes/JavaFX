package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.ItemPrice;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.service.ItemService;
import ph.txtdis.type.ItemType;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;

@Component
public class ItemDTOImpl extends AbstractSearchedSpunDTO<Item, String, ItemService> implements ItemDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Item();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public void setName(String name) {
        entity.setName(name);
    }

    @Override
    public String getDescription() {
        return entity.getDescription();
    }

    @Override
    public void setDescription(String description) {
        entity.setDescription(description);
    }

    @Override
    public ItemType getType() {
        return entity.getType();
    }

    @Override
    public void setType(ItemType type) {
        entity.setType(type);
    }

    @Override
    public ItemFamily getFamily() {
        return entity.getFamily();
    }

    @Override
    public void setFamily(ItemFamily family) {
        entity.setFamily(family);
    }

    @Override
    public long getVendorId() {
        return entity.getVendorId();
    }

    @Override
    public void setVendorId(long vendorId) {
        entity.setVendorId(vendorId);
    }

    @Override
    public Boolean isNotDiscounted() {
        return entity.isNotDiscounted();
    }

    @Override
    public void setNotDiscounted(Boolean notDiscounted) {
        entity.setNotDiscounted(notDiscounted);
    }

    @Override
    public ObservableList<QtyPerUom> getQtyPerUom() {
        return FXCollections.observableList(service.getQtyPerUom(id));
    }

    @Override
    public void setQtyPerUom(List<QtyPerUom> qtyPerUom) {
        entity.setQtyPerUom(qtyPerUom);
    }

    @Override
    public ObservableList<Pricing> getPriceHistory() {
        return FXCollections.observableList(service.getPriceHistory(id));
    }

    @Override
    public void setPriceHistory(List<Pricing> priceHistory) {
        entity.setPriceHistory(priceHistory);
    }

    @Override
    public ObservableList<VolumeDiscount> getVolumeDiscounts() {
        return FXCollections.observableList(service.getVolumeDiscounts(id));
    }

    @Override
    public void setVolumeDiscounts(List<VolumeDiscount> volumeDiscounts) {
        entity.setVolumeDiscounts(volumeDiscounts);
    }

    @Override
    public ObservableList<Bom> getBoms() {
        return FXCollections.observableList(service.getBoms(id));
    }

    @Override
    public void setBoms(List<Bom> boms) {
        entity.setBoms(boms);
    }

    @Override
    public BigDecimal getLatestPurchasePrice(LocalDate date) {
        return service.getLatestPrice(entity, date, PricingType.PURCHASE);
    }

    @Override
    public BigDecimal getLatestSellingPrice(LocalDate date) {
        return service.getLatestPrice(entity, date, PricingType.LIST);
    }

    @Override
    public VolumeDiscount getLatestVolumeDiscount(LocalDate date) {
        return service.getLatestVolumeDiscount(entity, date);
    }

    @Override
    public Map<UomType, BigDecimal> getQtyPerUomMap() {
        return service.getQtyPerUomMap(id);
    }

    @Override
    public List<UomType> getPurchasingUoms() {
        return service.getPurchasingUoms(entity);
    }

    @Override
    public List<UomType> getSellingUoms() {
        return service.getSellingUoms(entity);
    }

    @Override
    public List<UomType> getReportingUoms() {
        return service.getReportingUoms(entity);
    }

    @Override
    public boolean exists(String name) {
        return service.exists(name);
    }

    @Override
    public ObservableList<ItemPrice> list() {
        return FXCollections.observableList(service.list());
    }
}
