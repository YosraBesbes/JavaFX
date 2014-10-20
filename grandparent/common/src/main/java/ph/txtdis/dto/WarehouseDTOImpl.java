package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Warehouse;
import ph.txtdis.service.WarehouseService;

@Component
public class WarehouseDTOImpl extends AbstractListedNamedDTO<Warehouse, WarehouseService> implements WarehouseDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Warehouse();
    }
}
