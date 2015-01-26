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
public class AgingSetupImpl implements AgingSetup {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TruckService truckService;

    @Autowired
    BookingService bookingService;

    @Autowired
    InvoicingService invoicingService;

    @Autowired
    PickingService pickingService;

    public AgingSetupImpl() {
    }

    @Override
    public void start() {

        Users sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.now();

        Booking varietyBook = bookingService.get(3);
        Booking wetMarketBook = bookingService.get(4);
        Booking dryMarketBook = bookingService.get(5);

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
        bookingService.getDetails(3).forEach(
                variety -> {
                    InvoicingDetail detail = new InvoicingDetail(varietyInvoice, variety.getItem(), variety.getUom(),
                            variety.getQty(), variety.getQuality());
                    detail.setPrice(variety.getPrice());
                    varietyDetails.add(detail);
                });
        varietyInvoice.setDetails(varietyDetails);
        invoicingService.save(varietyInvoice);

        List<InvoicingDetail> wetMarketDetails = new ArrayList<>();
        Invoicing wetMarketInvoice = new Invoicing(wetMarketBook.getPartner(), wetMarketBook, date);
        wetMarketInvoice.setValue(wetMarketBook.getValue());
        bookingService.getDetails(4).forEach(
                wetMarket -> {
                    InvoicingDetail detail = new InvoicingDetail(wetMarketInvoice, wetMarket.getItem(), wetMarket
                            .getUom(), wetMarket.getQty(), wetMarket.getQuality());
                    detail.setPrice(wetMarket.getPrice());
                    wetMarketDetails.add(detail);
                });
        wetMarketInvoice.setDetails(wetMarketDetails);
        invoicingService.save(wetMarketInvoice);

        List<InvoicingDetail> dryMarketDetails = new ArrayList<>();
        Invoicing dryMarketInvoice = new Invoicing(dryMarketBook.getPartner(), dryMarketBook, date);
        dryMarketInvoice.setValue(dryMarketBook.getValue());
        bookingService.getDetails(5).forEach(
                dryMarket -> {
                    InvoicingDetail detail = new InvoicingDetail(dryMarketInvoice, dryMarket.getItem(), dryMarket
                            .getUom(), dryMarket.getQty(), dryMarket.getQuality());
                    detail.setPrice(dryMarket.getPrice());
                    dryMarketDetails.add(detail);
                });
        dryMarketInvoice.setDetails(dryMarketDetails);
        invoicingService.save(dryMarketInvoice);
    }
}
