package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Route;
import ph.txtdis.service.RouteService;

@Component
public class RouteDTOImpl extends AbstractListedNamedDTO<Route, RouteService> implements RouteDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new Route();
    }
}
