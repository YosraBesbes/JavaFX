package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Route;
import ph.txtdis.service.RouteService;

@Component
public class RouteDTOImpl extends AbstractListed<Route, RouteService> implements RouteDTO {
}
