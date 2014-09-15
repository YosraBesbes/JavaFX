package ph.txtdis.app;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Booking;
import ph.txtdis.model.PickList;
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
        Booking variety = bookingService.get(3);
        Booking wetMarket = bookingService.get(4);
        Booking dryMarket = bookingService.get(5);
        
        Truck rdm801 = new Truck("RDM801");
        rdm801.setCreatedBy(sysgen);
        truckService.save(rdm801);
        
        Truck kdl170 = new Truck("KDL170");
        kdl170.setCreatedBy(sysgen);
        truckService.save(kdl170);

        Truck wsn519 = new Truck("WSN519");
        wsn519.setCreatedBy(sysgen);
        truckService.save(wsn519);

        Picking rdmPick = new Picking(rdm801, sysgen, sysgen, date);
        rdmPick.setDetails(Arrays.asList(new PickingDetail(rdmPick, variety)));
        pickingService.save(rdmPick);
        
        Picking kdlPick = new Picking(kdl170, sysgen, sysgen, date);
        PickingDetail wetPick = new PickingDetail(kdlPick, wetMarket);
        PickingDetail dryPick = new PickingDetail(kdlPick, dryMarket);
        kdlPick.setDetails(Arrays.asList(wetPick, dryPick));
        pickingService.save(kdlPick);
        
        List<PickList> rdmPickList = pickingService.generatePickList(1);
        rdmPickList.forEach(p -> System.err.println(p.getItemId() + ", " + p.getItemName() + ", " + p.getQty()));
        System.err.println();

        List<PickList> kdlPickList = pickingService.generatePickList(2);
        kdlPickList.forEach(p -> System.err.println(p.getItemId() + ", " + p.getItemName() + ", " + p.getQty()));
    }
}
