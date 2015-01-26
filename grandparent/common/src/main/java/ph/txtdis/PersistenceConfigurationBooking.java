package ph.txtdis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.BookingDiscount;
import ph.txtdis.model.Customer;
import ph.txtdis.model.Item;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.model.Quality;
import ph.txtdis.repository.BookingRepository;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.ItemRepository;
import ph.txtdis.repository.PricingRepository;
import ph.txtdis.repository.QualityRepository;
import ph.txtdis.repository.RoutingRepository;
import ph.txtdis.repository.UserRepository;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;

@Configuration
@EnableJpaRepositories
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistenceConfigurationBooking {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PricingRepository pricingRepository;

    @Autowired
    QualityRepository qualityRepository;

    @Autowired
    RoutingRepository routingRepository;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void start() {

        System.out.println("Was here");

        if (bookingRepository.count() > 0)
            return;

        LocalDate oldDate = LocalDate.parse("2014-08-31");
        LocalDate newDate = LocalDate.now();
        Quality good = qualityRepository.findOne(1);

        int pineSliceFlatCode = 1;
        Item pineSliceFlat = itemRepository.findOne(pineSliceFlatCode);
        BigDecimal pineSliceFlatqtyPerCS = getQtyPerUomMap(pineSliceFlatCode).get(UomType.CS);
        BigDecimal pineSliceFlatpricePerPC = pricingRepository
                .findFirstByItemAndTypeAndStartDateLessThanEqualOrderByStartDateDesc(pineSliceFlat, PricingType.LIST,
                        oldDate).getPrice();
        BigDecimal pineSliceFlatpricePerCS = pineSliceFlatpricePerPC.multiply(pineSliceFlatqtyPerCS);

        int pineSlice15Code = 2;
        Item pineSlice15 = itemRepository.findOne(pineSlice15Code);
        BigDecimal pineSlice15qtyPerCS = getQtyPerUomMap(pineSlice15Code).get(UomType.CS);
        BigDecimal pineSlice15pricePerPC = pricingRepository
                .findFirstByItemAndTypeAndStartDateLessThanEqualOrderByStartDateDesc(pineSlice15, PricingType.LIST,
                        newDate).getPrice();
        BigDecimal pineSlice15pricePerCS = pineSlice15pricePerPC.multiply(pineSlice15qtyPerCS);

        Customer sarisari = customerRepository.findOne(4);
        BigDecimal sarisariQty = BigDecimal.TEN;
        Booking sarisariBooking = new Booking(sarisari, newDate);
        BookingDetail sarisariDetail = new BookingDetail(sarisariBooking, pineSliceFlat, UomType.PC, sarisariQty, good);
        sarisariDetail.setPrice(pineSliceFlatpricePerPC);

        sarisariBooking.setDetails(Arrays.asList(sarisariDetail));
        sarisariBooking.setRoute(routingRepository.findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(
                sarisari, newDate).getRoute());
        sarisariBooking.setValue(sarisariQty.multiply(pineSliceFlatpricePerPC));
        bookingRepository.save(sarisariBooking);

        Customer palengke = customerRepository.findOne(5);
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
        palengkeBooking.setRoute(routingRepository.findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(
                palengke, newDate).getRoute());
        palengkeBooking.setValue(palengkeDetail1Amt.add(palengkeDetail2Amt).add(palengkeDetail3Amt));

        bookingRepository.save(palengkeBooking);

        Customer variety = customerRepository.findOne(6);
        BigDecimal varietyQty = new BigDecimal(20);
        Booking varietyBooking = new Booking(variety, oldDate);
        BookingDetail varietyDetail = new BookingDetail(varietyBooking, pineSliceFlat, UomType.PC, varietyQty, good);
        varietyDetail.setPrice(pineSliceFlatpricePerPC);
        varietyBooking.setDetails(Arrays.asList(varietyDetail));
        varietyBooking.setRoute(routingRepository.findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(
                variety, oldDate).getRoute());
        varietyBooking.setValue(varietyQty.multiply(pineSliceFlatpricePerPC));
        varietyBooking.setDiscounts(Arrays.asList(new BookingDiscount(varietyBooking, 1, new BigDecimal("10.00"),
                varietyBooking.getValue().multiply(new BigDecimal("0.10")))));
        bookingRepository.save(varietyBooking);

        Customer wetMarket = customerRepository.findOne(7);
        BigDecimal wetMarketQty = new BigDecimal(5);
        Booking wetMarketBooking = new Booking(wetMarket, oldDate);
        BookingDetail wetMarketDetail = new BookingDetail(wetMarketBooking, pineSliceFlat, UomType.PC, wetMarketQty,
                good);
        BookingDiscount wetBooking5Discount = new BookingDiscount(wetMarketBooking, 1, new BigDecimal("5.00"),
                varietyBooking.getValue().multiply(new BigDecimal("0.05")));
        BookingDiscount wetBooking1Discount = new BookingDiscount(wetMarketBooking, 2, new BigDecimal("1.00"),
                varietyBooking.getValue().multiply(new BigDecimal("0.95")).multiply(new BigDecimal("0.10")));
        wetMarketDetail.setPrice(pineSliceFlatpricePerPC);
        wetMarketBooking.setDetails(Arrays.asList(wetMarketDetail));
        wetMarketBooking.setRoute(routingRepository.findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(
                wetMarket, oldDate).getRoute());
        wetMarketBooking.setValue(wetMarketQty.multiply(pineSliceFlatpricePerPC));
        wetMarketBooking.setDiscounts(Arrays.asList(wetBooking5Discount, wetBooking1Discount));
        bookingRepository.save(wetMarketBooking);

        Customer dryMarket = customerRepository.findOne(8);
        BigDecimal dryMarketQty = new BigDecimal(2);
        Booking dryMarketBooking = new Booking(dryMarket, oldDate);
        BookingDetail dryMarketDetail = new BookingDetail(dryMarketBooking, pineSliceFlat, UomType.CS, dryMarketQty,
                good);
        dryMarketDetail.setPrice(pineSliceFlatpricePerCS);
        dryMarketBooking.setDetails(Arrays.asList(dryMarketDetail));
        dryMarketBooking.setRoute(routingRepository.findFirstByCustomerAndStartDateLessThanEqualOrderByStartDateDesc(
                dryMarket, oldDate).getRoute());
        dryMarketBooking.setValue(dryMarketQty.multiply(pineSliceFlatpricePerCS));
        bookingRepository.save(dryMarketBooking);
    }

    private Map<UomType, BigDecimal> getQtyPerUomMap(int id) {
        Map<UomType, BigDecimal> map = new HashMap<>();
        List<QtyPerUom> qtyPerUom = itemRepository.getQtyPerUom(id);
        qtyPerUom.forEach(qpu -> map.put(qpu.getUom(), qpu.getQty()));
        return map;
    }

}
