package ph.txtdis.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Inventory;
import ph.txtdis.model.Item;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.PurchasingService;
import ph.txtdis.service.QualityService;
import ph.txtdis.service.StockTakeReconciliationService;

@Component
public class PurchasingDTOImpl extends AbstractOrderDTO<Purchasing, PurchasingService, PurchasingDetail> implements
        PurchasingDTO {

    private Inventory inventory;

    @Autowired
    protected QualityService qualityService;

    @Autowired
    protected StockTakeReconciliationService reconService;

    @Override
    public void reset() {
        id = 0;
        entity = new Purchasing();
    }

    @Override
    public ObservableList<PurchasingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }

    @Override
    public int getDaysLevel(Item item, BigDecimal qty) {
        setStockOnHand(item);
        return getTotalQty(qty).divide(get4WkSoldQty(), 0, RoundingMode.HALF_EVEN).intValue();
    }

    private BigDecimal getTotalQty(BigDecimal qty) {
        return getOnHandQty().add(qty);
    }

    private BigDecimal getOnHandQty() {
        return inventory.getEndQty();
    }

    private BigDecimal get4WkSoldQty() {
        return inventory.get4WkSoldQty();
    }

    private void setStockOnHand(Item item) {
        inventory = service.getStockOnHand(reconService.getMaxId(), LocalDate.now(), LocalDate.now().minusWeeks(4),
                item, qualityService.good());
    }

    @Override
    public SystemUser getCancelledBy() {
        return entity.getCancelledBy();
    }

    @Override
    public void setCancelledBy(SystemUser cancelledBy) {
        entity.setCancelledBy(cancelledBy);
    }

    @Override
    public ZonedDateTime getCancelledOn() {
        return entity.getCancelledOn();
    }

    @Override
    public void setCancelledOn(ZonedDateTime cancelledOn) {
        entity.setCancelledOn(cancelledOn);
    }

    @Override
    public SystemUser getMailedBy() {
        return entity.getMailedBy();
    }

    @Override
    public void setMailedBy(SystemUser mailedBy) {
        entity.setMailedBy(mailedBy);
    }

    @Override
    public ZonedDateTime getMailedOn() {
        return entity.getMailedOn();
    }

    @Override
    public void setMailedOn(ZonedDateTime mailedOn) {
        entity.setMailedOn(mailedOn);
    }

    @Override
    public SystemUser getSentBy() {
        return entity.getSentBy();
    }

    @Override
    public void setSentBy(SystemUser sentBy) {
        entity.setSentBy(sentBy);
    }

    @Override
    public ZonedDateTime getSentOn() {
        return entity.getSentOn();
    }

    @Override
    public void setSentOn(ZonedDateTime sentOn) {
        entity.setSentOn(sentOn);
        ;
    }

    @Override
    public SystemUser getReceivedBy() {
        return entity.getReceivedBy();
    }

    @Override
    public void setReceivedBy(SystemUser receivedBy) {
        entity.setReceivedBy(receivedBy);
    }

    @Override
    public ZonedDateTime getReceivedOn() {
        return entity.getReceivedOn();
    }

    @Override
    public void setReceivedOn(ZonedDateTime receivedOn) {
        entity.setReceivedOn(receivedOn);
    }
}
