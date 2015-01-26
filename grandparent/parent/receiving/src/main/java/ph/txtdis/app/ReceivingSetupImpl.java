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
import ph.txtdis.model.Users;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.PickingService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.ReceivingService;
import ph.txtdis.service.TruckService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.ReceivingReferenceType;
import ph.txtdis.type.UomType;

@Component
public class ReceivingSetupImpl implements ReceivingSetup {

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    QualityService qualityService;

    @Autowired
    PickingService pickingService;

    @Autowired
    ReceivingService receivingService;

    @Autowired
    TruckService truckService;

    @Autowired
    UserService userService;

    public ReceivingSetupImpl() {
    }

    @Override
    public void start() {
        Users sysgen = userService.get("SYSGEN");

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
        marinaReceiving.setValue(marinaPineSliceFlatDetailAmount.add(marinaPineSlice15DetailAmount));
        receivingService.save(marinaReceiving);
    }
}
