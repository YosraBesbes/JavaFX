package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Warehouse;

@Service
@Transactional()
public class WarehouseServiceImpl extends AbstractListedService<Warehouse> implements WarehouseService {
}
