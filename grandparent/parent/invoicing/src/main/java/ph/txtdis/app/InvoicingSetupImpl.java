package ph.txtdis.app;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.service.BookingService;
import ph.txtdis.service.PickingService;
import ph.txtdis.service.TruckService;
import ph.txtdis.service.UserService;

@Component
public class InvoicingSetupImpl implements InvoicingSetup {

    @Autowired
    UserService userService;

    @Autowired
    TruckService truckService;

    @Autowired
    BookingService bookingService;

    @Autowired
    PickingService pickingService;

    public InvoicingSetupImpl() {
    }

    @Override
    public void start() {

        SystemUser sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.now();

        Booking booking = bookingService.get(1);
        Truck truck = truckService.get(3);
        Picking pick = new Picking(truck, sysgen, sysgen, date);
        pick.setDetails(Arrays.asList(new PickingDetail(pick, booking)));
        pickingService.save(pick);
    }
}
