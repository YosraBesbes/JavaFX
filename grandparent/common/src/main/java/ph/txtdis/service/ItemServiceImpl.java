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
import ph.txtdis.model.ItemPrice;
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
public class ItemServiceImpl extends AbstractService<Item, Integer> implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private VolumeDiscountRepository discountRepository;

    protected ItemServiceImpl() {
    }

    @Override
    public Integer getMinId() {
        return repository.getMinId();
    }

    @Override
    public Integer getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public List<Item> findAll(String description) {
        return repository.findByDescriptionContaining(description);
    }

    @Override
    public List<QtyPerUom> getQtyPerUom(int id) {
        return repository.getQtyPerUom(id);
    }

    @Override
    public List<Pricing> getPriceHistory(int id) {
        return repository.getPriceHistory(id);
    }

    @Override
    public List<VolumeDiscount> getVolumeDiscounts(int id) {
        return repository.getVolumeDiscounts(id);
    }

    @Override
    public List<Bom> getBoms(int id) {
        return repository.getBoms(id);
    }

    @Override
    public BigDecimal getLatestPrice(Item item, LocalDate date, PricingType type) {
        return pricingRepository.findFirstByItemAndTypeAndStartDateLessThanEqualOrderByStartDateDesc(item, type, date)
                .getPrice();
    }

    @Override
    public VolumeDiscount getLatestVolumeDiscount(Item item, LocalDate date) {
        List<VolumeDiscount> discounts = discountRepository.findByItemAndStartDateBeforeOrderByStartDateDesc(item,
                date.plusDays(1L), new PageRequest(0, 1));
        return discounts.isEmpty() ? null : discounts.get(0);
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
        return repository.getPurchasingUoms(item);
    }

    @Override
    public List<UomType> getSellingUoms(Item item) {
        return repository.getSellingUoms(item);
    }

    @Override
    public List<UomType> getReportingUoms(Item item) {
        return repository.getReportingUoms(item);
    }

    @Override
    public boolean exists(String name) {
        return !repository.findByName(name).isEmpty();
    }

    @Override
    public List<ItemPrice> list() {
        return repository.getItemsWithTheirLatestPrices();
    }
}
