package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Truck;
import ph.txtdis.repository.TruckRepository;

@Service
@Transactional()
public class TruckServiceImpl extends AbstractListedService<Truck> implements TruckService {

    @Autowired
    private TruckRepository repository;

    @Override
    public Truck get(String name) {
        return repository.findOneByName(name);
    }
}
