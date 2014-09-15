package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Location;
import ph.txtdis.model.LocationTree;
import ph.txtdis.repository.LocationTreeRepository;

@Service
@Transactional()
public class LocationTreeServiceImpl extends AbstractIdService<LocationTree> implements LocationTreeService {
    
    @Autowired
    private LocationTreeRepository repository;

    @Override
    public List<Location> listCities(Location province) {
        return repository.findChildren(province);
    }

    @Override
    public List<Location> listBarangays(Location city) {
        return repository.findChildren(city);
    }
}
