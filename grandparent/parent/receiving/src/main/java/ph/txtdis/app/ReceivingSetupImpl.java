package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.ReceivingReferenceType;
import ph.txtdis.type.UomType;
import ph.txtdis.util.Login;

@Component
public class ReceivingSetupImpl implements ReceivingSetup {

    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    QualityService qualityService;

    @Autowired
    ReceivingService receivingService;

    @Autowired
    UserService userService;

    public ReceivingSetupImpl() {
    }

    @Override
    public void start() {
        SystemUser sysgen = userService.get("SYSGEN");
        Login.setUser(sysgen);

        LocalDate date = LocalDate.parse("2014-07-28");
        Quality good = qualityService.good();

        int pineSliceFlatId = 1;
        Item pineSliceFlat = itemService.get(pineSliceFlatId);
        BigDecimal pineSliceFlatQtyPerCS = itemService.getQtyPerUomMap(pineSliceFlatId).get(UomType.CS);
        BigDecimal pineSliceFlatPricePerPC = itemService.getPriceHistory(pineSliceFlatId).get(1).getPrice();
        BigDecimal pineSliceFlatPricePerCS = pineSliceFlatPricePerPC.multiply(pineSliceFlatQtyPerCS);

        int pineSlice15Id = 2;
        Item pineSlice15 = itemService.get(pineSlice15Id);
        BigDecimal pineSlice15QtyPerCS = itemService.getQtyPerUomMap(pineSlice15Id).get(UomType.CS);
        BigDecimal pineSlice15PricePerPC = itemService.getPriceHistory(pineSlice15Id).get(1).getPrice();
        BigDecimal pineSlice15PricePerCS = pineSlice15PricePerPC.multiply(pineSlice15QtyPerCS);

        Customer marina = customerService.get(3);
        BigDecimal marinaQty = BigDecimal.TEN;
        Receiving marinaReceiving = new Receiving(marina, ReceivingReferenceType.PO, 1, sysgen, date);

        ReceivingDetail marinaPineSliceFlatDetail = new ReceivingDetail(marinaReceiving, pineSliceFlat, UomType.CS,
                marinaQty, good);
        marinaPineSliceFlatDetail.setPrice(pineSliceFlatPricePerCS);
        BigDecimal marinaPineSliceFlatDetailAmount = marinaQty.multiply(pineSliceFlatPricePerCS);

        ReceivingDetail marinaPineSlice15Detail = new ReceivingDetail(marinaReceiving, pineSlice15, UomType.CS,
                marinaQty, good);
        marinaPineSlice15Detail.setPrice(pineSlice15PricePerCS);
        BigDecimal marinaPineSlice15DetailAmount = marinaQty.multiply(pineSlice15PricePerCS);

        marinaReceiving.setDetails(Arrays.asList(marinaPineSliceFlatDetail, marinaPineSlice15Detail));
        marinaReceiving.setAmount(marinaPineSliceFlatDetailAmount.add(marinaPineSlice15DetailAmount));
        receivingService.save(marinaReceiving);
    }
}
