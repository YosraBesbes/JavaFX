package ph.txtdis.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.model.InvoicingDiscount;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Users;
import ph.txtdis.model.Truck;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.InvoicingService;
import ph.txtdis.service.PickingService;
import ph.txtdis.service.TruckService;
import ph.txtdis.service.UserService;

@Component
public class SalesReportSetupImpl implements SalesReportSetup {

    private static final int VARIETY = 3;
    private static final int WET_MARKET = 4;
    private static final int DRY_MARKET = 5;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private InvoicingService invoicingService;

    @Autowired
    private PickingService pickingService;

    public SalesReportSetupImpl() {
    }

    @Override
    public void start() {

        Users sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.now();

        Booking varietyBook = bookingService.get(VARIETY);
        Booking wetMarketBook = bookingService.get(WET_MARKET);
        Booking dryMarketBook = bookingService.get(DRY_MARKET);

        Truck rdm801 = truckService.get(1);
        Truck kdl170 = truckService.get(2);

        Picking rdmPick = new Picking(rdm801, sysgen, sysgen, date);
        rdmPick.setDetails(Arrays.asList(new PickingDetail(rdmPick, varietyBook)));
        pickingService.save(rdmPick);

        Picking kdlPick = new Picking(kdl170, sysgen, sysgen, date);
        PickingDetail wetPick = new PickingDetail(kdlPick, wetMarketBook);
        PickingDetail dryPick = new PickingDetail(kdlPick, dryMarketBook);
        kdlPick.setDetails(Arrays.asList(wetPick, dryPick));
        pickingService.save(kdlPick);

        List<InvoicingDetail> varietyDetails = new ArrayList<>();
        Invoicing varietyInvoice = new Invoicing(varietyBook.getPartner(), varietyBook, date);
        varietyInvoice.setValue(varietyBook.getValue());
        bookingService.getDetails(VARIETY).forEach(
                variety -> {
                    InvoicingDetail detail = new InvoicingDetail(varietyInvoice, variety.getItem(), variety.getUom(),
                            variety.getQty(), variety.getQuality());
                    detail.setPrice(variety.getPrice());
                    varietyDetails.add(detail);
                });
        varietyInvoice.setDetails(varietyDetails);
        varietyInvoice.setRoute(varietyBook.getRoute());
        varietyInvoice.setValue(varietyBook.getValue());
        varietyInvoice.setCredit(varietyBook.getCredit());
        List<InvoicingDiscount> varietyDiscounts = new ArrayList<>();
        bookingService.getDiscounts(VARIETY).forEach(
                variety -> varietyDiscounts.add(new InvoicingDiscount(varietyInvoice, variety.getLevel(), variety
                        .getPerCent(), variety.getValue())));
        invoicingService.save(varietyInvoice);

        Invoicing wetMarketInvoice = new Invoicing(wetMarketBook.getPartner(), wetMarketBook, date);
        wetMarketInvoice.setValue(wetMarketBook.getValue());
        List<InvoicingDetail> wetMarketDetails = new ArrayList<>();
        bookingService.getDetails(WET_MARKET).forEach(
                wetMarket -> {
                    InvoicingDetail detail = new InvoicingDetail(wetMarketInvoice, wetMarket.getItem(), wetMarket
                            .getUom(), wetMarket.getQty(), wetMarket.getQuality());
                    detail.setPrice(wetMarket.getPrice());
                    wetMarketDetails.add(detail);
                });
        wetMarketInvoice.setDetails(wetMarketDetails);
        wetMarketInvoice.setRoute(wetMarketBook.getRoute());
        wetMarketInvoice.setValue(wetMarketBook.getValue());
        wetMarketInvoice.setCredit(wetMarketBook.getCredit());
        List<InvoicingDiscount> wetMarketDiscounts = new ArrayList<>();
        bookingService.getDiscounts(WET_MARKET).forEach(
                wetMarket -> wetMarketDiscounts.add(new InvoicingDiscount(wetMarketInvoice, wetMarket.getLevel(),
                        wetMarket.getPerCent(), wetMarket.getValue())));
        invoicingService.save(wetMarketInvoice);

        List<InvoicingDetail> dryMarketDetails = new ArrayList<>();
        Invoicing dryMarketInvoice = new Invoicing(dryMarketBook.getPartner(), dryMarketBook, date);
        dryMarketInvoice.setValue(dryMarketBook.getValue());
        bookingService.getDetails(DRY_MARKET).forEach(
                dryMarket -> {
                    InvoicingDetail detail = new InvoicingDetail(dryMarketInvoice, dryMarket.getItem(), dryMarket
                            .getUom(), dryMarket.getQty(), dryMarket.getQuality());
                    detail.setPrice(dryMarket.getPrice());
                    dryMarketDetails.add(detail);
                });
        dryMarketInvoice.setDetails(dryMarketDetails);
        dryMarketInvoice.setRoute(dryMarketBook.getRoute());
        dryMarketInvoice.setValue(dryMarketBook.getValue());
        dryMarketInvoice.setCredit(dryMarketBook.getCredit());
        List<InvoicingDiscount> dryMarketDiscounts = new ArrayList<>();
        bookingService.getDiscounts(DRY_MARKET).forEach(
                dryMarket -> dryMarketDiscounts.add(new InvoicingDiscount(dryMarketInvoice, dryMarket.getLevel(),
                        dryMarket.getPerCent(), dryMarket.getValue())));
        invoicingService.save(dryMarketInvoice);
    }
}
