package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Truck;
import ph.txtdis.service.TruckService;

@Component
public class TruckDTOImpl extends AbstractDisabledDTO<Truck, TruckService> implements TruckDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Truck();
    }
}
