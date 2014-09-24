package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Route;

@Service
@Transactional()
public class RouteServiceImpl extends AbstractListedService<Route> implements RouteService {
}
