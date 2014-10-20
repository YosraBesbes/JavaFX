package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Route;
import ph.txtdis.repository.RouteRepository;

@Service
@Transactional()
public class RouteServiceImpl extends AbstractListedService<Route> implements RouteService {

    @Autowired
    private RouteRepository repository;

    @Override
    public Route get(String name) {
        return repository.findOneByName(name);
    }
}
