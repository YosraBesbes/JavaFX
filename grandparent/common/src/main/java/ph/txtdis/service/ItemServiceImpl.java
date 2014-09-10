package ph.txtdis.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.repository.ItemRepository;
import ph.txtdis.repository.PricingRepository;
import ph.txtdis.repository.VolumeDiscountRepository;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;

@Service
@Transactional()
public class ItemServiceImpl extends AbstractService<Item> implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private VolumeDiscountRepository discountRepository;

    protected ItemServiceImpl() {
    }

    public int getMinId() {
        return itemRepository.getMinId();
    }

    public int getMaxId() {
        return itemRepository.getMaxId();
    }

    public List<Item> findAll(String description) {
        return itemRepository.findByDescriptionContaining(description);
    }

    @Override
    public List<QtyPerUom> getQtyPerUom(int id) {
        return itemRepository.getQtyPerUom(id);
    }

    @Override
    public List<Pricing> getPriceHistory(int id) {
        return itemRepository.getPriceHistory(id);
    }

    @Override
    public List<VolumeDiscount> getVolumeDiscounts(int id) {
        return itemRepository.getVolumeDiscounts(id);
    }

    @Override
    public List<Bom> getBoms(int id) {
        return itemRepository.getBoms(id);
    }

    @Override
    public BigDecimal getLatestPrice(Item item, LocalDate date, PricingType type) {
        List<Pricing> prices = pricingRepository.findByItemAndTypeAndStartDateBeforeOrderByStartDateDesc(item, type,
                date.plusDays(1L), new PageRequest(0, 1));
        BigDecimal price = prices.get(0).getPrice();
        return price;
    }

    @Override
    public VolumeDiscount getLatestVolumeDiscount(Item item, LocalDate date) {
        List<VolumeDiscount> discounts = discountRepository.findByItemAndStartDateBeforeOrderByStartDateDesc(item,
                date.plusDays(1L), new PageRequest(0, 1));
        return discounts.get(0);
    }

    @Override
    public Map<UomType, BigDecimal> getQtyPerUomMap(int id) {
        Map<UomType, BigDecimal> map = new HashMap<>();
        List<QtyPerUom> qtyPerUom = getQtyPerUom(id);
        qtyPerUom.forEach(qpu -> map.put(qpu.getUom(), qpu.getQty()));
        return map;
    }

    @Override
    public List<UomType> getPurchasingUoms(Item item) {
        return itemRepository.getPurchasingUoms(item);
    }

    @Override
    public List<UomType> getSellingUoms(Item item) {
        return itemRepository.getSellingUoms(item);
    }

    @Override
    public List<UomType> getReportingUoms(Item item) {
        return itemRepository.getReportingUoms(item);
    }

    @Override
    public boolean exists(String name) {
        return !itemRepository.findByName(name).isEmpty();
    }
}
