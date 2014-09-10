package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Truck;

@Service
@Transactional()
public class TruckServiceImpl extends AbstractListedService<Truck> implements TruckService {
}
