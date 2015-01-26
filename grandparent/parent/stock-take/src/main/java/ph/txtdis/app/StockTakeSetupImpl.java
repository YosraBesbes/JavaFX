package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.Users;
import ph.txtdis.model.Warehouse;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.StockTakeReconciliationService;
import ph.txtdis.service.StockTakeService;
import ph.txtdis.service.UserService;
import ph.txtdis.service.WarehouseService;
import ph.txtdis.type.UomType;

@Component
public class StockTakeSetupImpl implements StockTakeSetup {

    @Autowired
    ItemService itemService;

    @Autowired
    QualityService qualityService;

    @Autowired
    StockTakeService stockService;

    @Autowired
    StockTakeReconciliationService reconService;

    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    public StockTakeSetupImpl() {
    }

    @Override
    public void start() {

        Users sysgen = userService.get("SYSGEN");
        LocalDate date = LocalDate.parse("2014-09-01");
        Quality bad = qualityService.get(3);
        Item item1 = itemService.get(1);
        Item item2 = itemService.get(2);
        Warehouse edsa = warehouseService.get(1);

        reconService.save(new StockTakeReconciliation(sysgen, date));

        StockTake st1 = stockService.save(new StockTake(edsa, sysgen, sysgen, date));
        StockTakeDetail std1 = new StockTakeDetail(st1, item1, UomType.CS, BigDecimal.ONE, qualityService.good());
        StockTakeDetail std2 = new StockTakeDetail(st1, item1, UomType.PC, BigDecimal.TEN, bad);
        StockTakeDetail std3 = new StockTakeDetail(st1, item2, UomType.PC, new BigDecimal(5), bad);
        st1.setDetails(Arrays.asList(std1, std2, std3));
        stockService.save(st1);

        StockTake st2 = stockService.save(new StockTake(edsa, sysgen, sysgen, date));
        StockTakeDetail std4 = new StockTakeDetail(st2, item2, UomType.PC, new BigDecimal(5), qualityService.good());
        st2.setDetails(Arrays.asList(std4));
        stockService.save(st2);
    }
}
