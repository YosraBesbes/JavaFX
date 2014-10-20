package ph.txtdis.dto;

import ph.txtdis.model.Disable;
import ph.txtdis.model.Truck;

public interface TruckDTO extends DTO<Truck, Integer>, ListedNamed<Truck>, Disable {
}