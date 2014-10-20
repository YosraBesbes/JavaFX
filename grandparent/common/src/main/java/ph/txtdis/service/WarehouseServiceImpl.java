package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Warehouse;
import ph.txtdis.repository.WarehouseRepository;

@Service
@Transactional()
public class WarehouseServiceImpl extends AbstractListedService<Warehouse> implements WarehouseService {

    @Autowired
    private WarehouseRepository repository;

    @Override
    public Warehouse get(String name) {
        return repository.findOneByName(name);
    }
}
