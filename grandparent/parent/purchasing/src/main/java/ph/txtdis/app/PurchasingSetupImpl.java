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
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.CustomerService;
import ph.txtdis.service.ItemService;
import ph.txtdis.service.PurchasingService;
import ph.txtdis.service.UserService;
import ph.txtdis.type.PricingType;
import ph.txtdis.type.UomType;

@Component
public class PurchasingSetupImpl implements PurchasingSetup {

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PurchasingService purchasingService;

    @Autowired
    ItemService itemService;

    public PurchasingSetupImpl() {
    }

    @Override
    public void start() {
        SystemUser sysgen = userService.get("SYSGEN");
        Customer marina = customerService.get(3);
        Item item = itemService.get(1);
        LocalDate date = LocalDate.parse("2014-07-28");
        BigDecimal price = itemService.getLatestPrice(item, date, PricingType.PURCHASE);
        BigDecimal pricePerCS = price.multiply(itemService.getQtyPerUomMap(1).get(UomType.CS));
        BigDecimal qty = BigDecimal.ONE;
        
        Purchasing purchasing = new Purchasing(marina, date);
        
        PurchasingDetail detail = new PurchasingDetail(purchasing, item, UomType.CS, qty);
        detail.setCreatedBy(sysgen);
        detail.setPrice(pricePerCS);
        
        List<PurchasingDetail> details = Arrays.asList(detail);
        purchasing.setDetails(details);
        purchasing.setAmount(qty.multiply(pricePerCS));
        purchasing.setCreatedBy(sysgen);
        
        purchasingService.save(purchasing);
    }
}
