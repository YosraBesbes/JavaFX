package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Customer;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;
import ph.txtdis.util.Login;

@Component
public class BookingSetupImpl implements BookingSetup {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    QualityService qualityService;

    @Autowired
    ItemService itemService;

    @Autowired
    BookingService bookingService;

    public BookingSetupImpl() {
    }

    @Override
    public void start() {
        Login.setUser(userService.get("SYSGEN"));
        LocalDate oldDate = LocalDate.parse("2014-08-31");
        LocalDate newDate = LocalDate.now();
        Quality good = qualityService.good();

        int pineSliceFlatCode = 1;
        Item pineSliceFlat = itemService.get(pineSliceFlatCode);
        BigDecimal pineSliceFlatqtyPerCS = itemService.getQtyPerUomMap(pineSliceFlatCode).get(UomType.CS);
        BigDecimal pineSliceFlatpricePerPC = itemService.getLatestPrice(pineSliceFlat, oldDate, PricingType.LIST);
        BigDecimal pineSliceFlatpricePerCS = pineSliceFlatpricePerPC.multiply(pineSliceFlatqtyPerCS);

        int pineSlice15Code = 2;
        Item pineSlice15 = itemService.get(pineSlice15Code);
        BigDecimal pineSlice15qtyPerCS = itemService.getQtyPerUomMap(pineSlice15Code).get(UomType.CS);
        BigDecimal pineSlice15pricePerPC = itemService.getLatestPrice(pineSlice15, newDate, PricingType.LIST);
        BigDecimal pineSlice15pricePerCS = pineSlice15pricePerPC.multiply(pineSlice15qtyPerCS);

        Customer sarisari = customerService.get(4);
        BigDecimal sarisariQty = BigDecimal.TEN;
        Booking sarisariBooking = new Booking(sarisari, newDate);
        BookingDetail sarisariDetail = new BookingDetail(sarisariBooking, pineSliceFlat, UomType.PC, sarisariQty, good);
        sarisariDetail.setPrice(pineSliceFlatpricePerPC);

        sarisariBooking.setDetails(Arrays.asList(sarisariDetail));
        sarisariBooking.setRoute(customerService.getLatestRoute(sarisari, newDate));
        sarisariBooking.setAmount(sarisariQty.multiply(pineSliceFlatpricePerPC));
        bookingService.save(sarisariBooking);

        Customer palengke = customerService.get(5);
        Booking palengkeBooking = new Booking(palengke, newDate);

        BigDecimal palengkeDetail1Qty = BigDecimal.ONE;
        BookingDetail palengkeDetail1 = new BookingDetail(palengkeBooking, pineSliceFlat, UomType.CS,
                palengkeDetail1Qty, good);
        palengkeDetail1.setPrice(pineSliceFlatpricePerCS);
        BigDecimal palengkeDetail1Amt = palengkeDetail1Qty.multiply(pineSliceFlatpricePerCS);

        BigDecimal palengkeDetail2Qty = new BigDecimal(2);
        BookingDetail palengkeDetail2 = new BookingDetail(palengkeBooking, pineSlice15, UomType.CS, palengkeDetail2Qty,
                good);
        palengkeDetail2.setPrice(pineSlice15pricePerCS);
        BigDecimal palengkeDetail2Amt = palengkeDetail2Qty.multiply(pineSlice15pricePerCS);

        BigDecimal palengkeDetail3Qty = new BigDecimal(12);
        BookingDetail palengkeDetail3 = new BookingDetail(palengkeBooking, pineSlice15, UomType.PC, palengkeDetail3Qty,
                good);
        palengkeDetail3.setPrice(pineSlice15pricePerPC);
        BigDecimal palengkeDetail3Amt = palengkeDetail3Qty.multiply(pineSlice15pricePerPC);

        palengkeBooking.setDetails(Arrays.asList(palengkeDetail1, palengkeDetail2, palengkeDetail3));
        palengkeBooking.setRoute(customerService.getLatestRoute(palengke, newDate));
        palengkeBooking.setAmount(palengkeDetail1Amt.add(palengkeDetail2Amt).add(palengkeDetail3Amt));

        bookingService.save(palengkeBooking);

        Customer variety = customerService.get(6);
        BigDecimal varietyQty = new BigDecimal(20);
        Booking varietyBooking = new Booking(variety, oldDate);
        BookingDetail varietyDetail = new BookingDetail(varietyBooking, pineSliceFlat, UomType.PC, varietyQty, good);
        varietyDetail.setPrice(pineSliceFlatpricePerPC);
        varietyBooking.setDetails(Arrays.asList(varietyDetail));
        varietyBooking.setRoute(customerService.getLatestRoute(variety, oldDate));
        varietyBooking.setAmount(varietyQty.multiply(pineSliceFlatpricePerPC));
        bookingService.save(varietyBooking);

        Customer wetMarket = customerService.get(7);
        BigDecimal wetMarketQty = new BigDecimal(5);
        Booking wetMarketBooking = new Booking(wetMarket, oldDate);
        BookingDetail wetMarketDetail = new BookingDetail(wetMarketBooking, pineSliceFlat, UomType.PC, wetMarketQty,
                good);
        wetMarketDetail.setPrice(pineSliceFlatpricePerPC);
        wetMarketBooking.setDetails(Arrays.asList(wetMarketDetail));
        wetMarketBooking.setRoute(customerService.getLatestRoute(wetMarket, oldDate));
        wetMarketBooking.setAmount(wetMarketQty.multiply(pineSliceFlatpricePerPC));
        bookingService.save(wetMarketBooking);

        Customer dryMarket = customerService.get(8);
        BigDecimal dryMarketQty = new BigDecimal(2);
        Booking dryMarketBooking = new Booking(dryMarket, oldDate);
        BookingDetail dryMarketDetail = new BookingDetail(dryMarketBooking, pineSliceFlat, UomType.CS, dryMarketQty,
                good);
        dryMarketDetail.setPrice(pineSliceFlatpricePerCS);
        dryMarketBooking.setDetails(Arrays.asList(dryMarketDetail));
        dryMarketBooking.setRoute(customerService.getLatestRoute(dryMarket, oldDate));
        dryMarketBooking.setAmount(dryMarketQty.multiply(pineSliceFlatpricePerCS));
        bookingService.save(dryMarketBooking);
    }
}
