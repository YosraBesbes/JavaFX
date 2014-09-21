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
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.UomType;

@Component
public class BookingSetupImpl implements BookingSetup {

    @Autowired
    BookingService bookingService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    QualityService qualityService;

    @Autowired
    UserService userService;

    public BookingSetupImpl() {
    }

    @Override
    public void start() {
        SystemUser sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.parse("2014-08-31");
        Quality good = qualityService.good();

        int pineSliceFlat = 1;
        Item item = itemService.get(pineSliceFlat);
        BigDecimal qtyPerCS = itemService.getQtyPerUomMap(pineSliceFlat).get(UomType.CS);
        BigDecimal pricePerPC = itemService.getPriceHistory(pineSliceFlat).get(2).getPrice();
        BigDecimal pricePerCS = pricePerPC.multiply(qtyPerCS);

        Customer sarisari = customerService.get(4);
        BigDecimal sarisariQty = BigDecimal.TEN;
        Booking sarisariBooking = new Booking(sarisari, date);
        BookingDetail sarisariDetail = new BookingDetail(sarisariBooking, item, UomType.PC, sarisariQty, good);
        sarisariDetail.setPrice(pricePerPC);
        sarisariDetail.setCreatedBy(sysgen);

        sarisariBooking.setDetails(Arrays.asList(sarisariDetail));
        sarisariBooking.setRoute(customerService.getLatestRoute(sarisari, date));
        sarisariBooking.setAmount(sarisariQty.multiply(pricePerPC));
        sarisariBooking.setCreatedBy(sysgen);
        bookingService.save(sarisariBooking);

        Customer palengke = customerService.get(5);
        BigDecimal palengkeQty = BigDecimal.ONE;
        Booking palengkeBooking = new Booking(palengke, date);
        BookingDetail palengkeDetail = new BookingDetail(palengkeBooking, item, UomType.CS, palengkeQty, good);
        palengkeDetail.setPrice(pricePerCS);
        palengkeDetail.setCreatedBy(sysgen);
        palengkeBooking.setDetails(Arrays.asList(palengkeDetail));
        palengkeBooking.setRoute(customerService.getLatestRoute(palengke, date));
        palengkeBooking.setAmount(palengkeQty.multiply(pricePerCS));
        palengkeBooking.setCreatedBy(sysgen);
        bookingService.save(palengkeBooking);

        Customer variety = customerService.get(6);
        BigDecimal varietyQty = new BigDecimal(20);
        Booking varietyBooking = new Booking(variety, date);
        BookingDetail varietyDetail = new BookingDetail(varietyBooking, item, UomType.PC, varietyQty, good);
        varietyDetail.setPrice(pricePerPC);
        varietyDetail.setCreatedBy(sysgen);
        varietyBooking.setDetails(Arrays.asList(varietyDetail));
        varietyBooking.setRoute(customerService.getLatestRoute(variety, date));
        varietyBooking.setAmount(varietyQty.multiply(pricePerPC));
        varietyBooking.setCreatedBy(sysgen);
        bookingService.save(varietyBooking);

        Customer wetMarket = customerService.get(7);
        BigDecimal wetMarketQty = new BigDecimal(5);
        Booking wetMarketBooking = new Booking(wetMarket, date);
        BookingDetail wetMarketDetail = new BookingDetail(wetMarketBooking, item, UomType.PC, wetMarketQty, good);
        wetMarketDetail.setPrice(pricePerPC);
        wetMarketDetail.setCreatedBy(sysgen);
        wetMarketBooking.setDetails(Arrays.asList(wetMarketDetail));
        wetMarketBooking.setRoute(customerService.getLatestRoute(wetMarket, date));
        wetMarketBooking.setAmount(wetMarketQty.multiply(pricePerPC));
        wetMarketBooking.setCreatedBy(sysgen);
        bookingService.save(wetMarketBooking);

        Customer dryMarket = customerService.get(8);
        BigDecimal dryMarketQty = new BigDecimal(2);
        Booking dryMarketBooking = new Booking(dryMarket, date);
        BookingDetail dryMarketDetail = new BookingDetail(dryMarketBooking, item, UomType.CS, dryMarketQty, good);
        dryMarketDetail.setPrice(pricePerCS);
        dryMarketDetail.setCreatedBy(sysgen);
        dryMarketBooking.setDetails(Arrays.asList(dryMarketDetail));
        dryMarketBooking.setRoute(customerService.getLatestRoute(dryMarket, date));
        dryMarketBooking.setAmount(dryMarketQty.multiply(pricePerCS));
        dryMarketBooking.setCreatedBy(sysgen);
        bookingService.save(dryMarketBooking);
    }
}
