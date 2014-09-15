package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Routing;

@Service
@Transactional()
public class RoutingServiceImpl extends AbstractIdService<Routing> implements RoutingService {
}
