package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Customer;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Quality;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.PurchasingService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;
import ph.txtdis.util.Login;

@Component
public class PurchasingSetupImpl implements PurchasingSetup {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    QualityService qualityService;

    @Autowired
    ItemService itemService;

    @Autowired
    PurchasingService purchasingService;

    public PurchasingSetupImpl() {
    }

    @Override
    public void start() {
        Login.setUser(userService.get("SYSGEN"));
        Customer marina = customerService.get(3);
        Item item = itemService.get(1);
        LocalDate date = LocalDate.parse("2014-07-28");
        BigDecimal price = itemService.getLatestPrice(item, date, PricingType.PURCHASE);
        BigDecimal pricePerCS = price.multiply(itemService.getQtyPerUomMap(1).get(UomType.CS));
        BigDecimal qty = BigDecimal.ONE;
        Quality good = qualityService.good();

        Purchasing purchasing = new Purchasing(marina, date);

        PurchasingDetail detail = new PurchasingDetail(purchasing, item, UomType.CS, qty);
        detail.setPrice(pricePerCS);
        detail.setQuality(good);

        List<PurchasingDetail> details = Arrays.asList(detail);
        purchasing.setDetails(details);
        purchasing.setTotalValue(qty.multiply(pricePerCS));

        purchasingService.save(purchasing);
    }
}
