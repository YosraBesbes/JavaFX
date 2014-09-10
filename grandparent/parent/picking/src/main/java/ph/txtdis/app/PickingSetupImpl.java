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
public class PickingSetupImpl implements PickingSetup {

    @Autowired
    UserService userService;

    @Autowired
    TruckService truckService;

    @Autowired
    BookingService bookingService;

    @Autowired
    PickingService pickingService;

    public PickingSetupImpl() {
    }

    @Override
    public void start() {
        
        SystemUser sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.parse("2014-09-01");
        Booking variety = bookingService.get(2);
        Booking wetMarket = bookingService.get(3);
        Booking dryMarket = bookingService.get(4);
        
        Truck abc = new Truck("ABC123");
        abc.setCreatedBy(sysgen);
        truckService.save(abc);
        
        Truck def = new Truck("DEF456");
        def.setCreatedBy(sysgen);
        truckService.save(def);

        Truck ghi = new Truck("GHI789");
        ghi.setCreatedBy(sysgen);
        truckService.save(ghi);
        
        Truck jkl = new Truck("JKL012");
        jkl.setCreatedBy(sysgen);
        truckService.save(jkl);

        Picking ghiTruck = new Picking(abc, sysgen, sysgen, date);
        ghiTruck.setDetails(Arrays.asList(new PickingDetail(ghiTruck, variety)));
        pickingService.save(ghiTruck);
        
        Picking jklTruck = new Picking(jkl, sysgen, sysgen, date);
        PickingDetail wetPick = new PickingDetail(jklTruck, wetMarket);
        PickingDetail dryPick = new PickingDetail(jklTruck, dryMarket);
        jklTruck.setDetails(Arrays.asList(wetPick, dryPick));
        pickingService.save(jklTruck);
    }
}
