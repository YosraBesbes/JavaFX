package ph.txtdis.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.service.PurchasingService;

@Component
public class PurchasingDTOImpl extends AbstractOrderDTO<Purchasing, PurchasingService, PurchasingDetail> implements
        PurchasingDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Purchasing();
    }

    @Override
    public ObservableList<PurchasingDetail> getDetails() {
        return FXCollections.observableList(service.getDetails(id));
    }
}
