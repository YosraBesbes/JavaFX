package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Truck;
import ph.txtdis.service.TruckService;

@Component
public class TruckDTOImpl extends AbstractListedName<Truck, TruckService> implements TruckDTO {
}
