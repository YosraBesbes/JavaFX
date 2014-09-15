package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Item;
import ph.txtdis.model.Pricing;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.service.ItemFamilyService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.ItemType;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;

@Component
public class ItemSetupImpl implements ItemSetup {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemFamilyService familyService;

    public ItemSetupImpl() {
    }

    @Override
    public void start() {

        SystemUser sysgen = userService.get("SYSGEN");
        LocalDate startDate = LocalDate.parse("2014-07-28");
        LocalDate newDate = LocalDate.parse("2015-07-28");

        Item pineSliceFlat = new Item("PINE SLCE FLT 227G", "DEL MONTE SLICED PINEAPPLE FLAT 227G X 24",
                ItemType.PURCHASED);
        pineSliceFlat.setFamily(familyService.get(13));
        pineSliceFlat.setVendorId(364);
        pineSliceFlat.setCreatedBy(sysgen);

        QtyPerUom pc = new QtyPerUom(pineSliceFlat, UomType.PC, BigDecimal.ONE, false, true, false);
        pc.setCreatedBy(sysgen);

        QtyPerUom cs = new QtyPerUom(pineSliceFlat, UomType.CS, new BigDecimal(24), true, true, true);
        cs.setCreatedBy(sysgen);

        List<QtyPerUom> qtyPerUom = Arrays.asList(pc, cs);
        pineSliceFlat.setQtyPerUom(qtyPerUom);

        Pricing purchase1 = new Pricing(pineSliceFlat, PricingType.PURCHASE, new BigDecimal(20.70), startDate);
        purchase1.setCreatedBy(sysgen);

        Pricing purchase2 = new Pricing(pineSliceFlat, PricingType.PURCHASE, new BigDecimal(21.70), newDate);
        purchase2.setCreatedBy(sysgen);

        Pricing list = new Pricing(pineSliceFlat, PricingType.LIST, new BigDecimal(23.48), startDate);
        list.setCreatedBy(sysgen);

        List<Pricing> prices = Arrays.asList(purchase1, purchase2, list);
        pineSliceFlat.setPriceHistory(prices);

        VolumeDiscount discount = new VolumeDiscount(pineSliceFlat, VolumeDiscountType.SET, UomType.PC, 24,
                new BigDecimal(0.15), startDate);
        discount.setCreatedBy(sysgen);

        List<VolumeDiscount> discounts = Arrays.asList(discount);
        pineSliceFlat.setVolumeDiscounts(discounts);

        itemService.save(pineSliceFlat);

        Item pineSlice15 = new Item("PINE SLCE 1.5 432G", "DEL MONTE SLICED PINEAPPLE 1 1/2 432G X 24",
                ItemType.PURCHASED);
        pineSlice15.setFamily(familyService.get(13));
        pineSlice15.setVendorId(1596);
        pineSlice15.setCreatedBy(sysgen);

        QtyPerUom pc15 = new QtyPerUom(pineSlice15, UomType.PC, BigDecimal.ONE, false, true, false);
        pc15.setCreatedBy(sysgen);

        QtyPerUom cs15 = new QtyPerUom(pineSlice15, UomType.CS, new BigDecimal(24), true, true, true);
        cs15.setCreatedBy(sysgen);

        List<QtyPerUom> qtyPerUom15 = Arrays.asList(pc15, cs15);
        pineSlice15.setQtyPerUom(qtyPerUom15);

        Pricing purchase15 = new Pricing(pineSlice15, PricingType.PURCHASE, new BigDecimal(37.66), startDate);
        purchase15.setCreatedBy(sysgen);

        Pricing list15 = new Pricing(pineSlice15, PricingType.LIST, new BigDecimal(42.48), startDate);
        list15.setCreatedBy(sysgen);

        List<Pricing> prices15 = Arrays.asList(purchase15, list15);
        pineSlice15.setPriceHistory(prices15);

        VolumeDiscount discount15 = new VolumeDiscount(pineSlice15, VolumeDiscountType.SET, UomType.PC, 24,
                new BigDecimal(0.15), startDate);
        discount15.setCreatedBy(sysgen);

        List<VolumeDiscount> discounts15 = Arrays.asList(discount15);
        pineSlice15.setVolumeDiscounts(discounts15);

        itemService.save(pineSlice15);
    }
}
