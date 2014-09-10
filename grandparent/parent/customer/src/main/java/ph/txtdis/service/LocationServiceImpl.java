package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Location;
import ph.txtdis.repository.LocationRepository;
import ph.txtdis.type.LocationType;

@Service
@Transactional()
public class LocationServiceImpl extends AbstractService<Location> implements LocationService {
    
    @Autowired
    private LocationRepository repository;

    @Override
    public List<Location> listProvinces() {
        return repository.findByType(LocationType.PROVINCE);
    }

    @Override
    public List<Location> listCities() {
        return repository.findByType(LocationType.CITY);
    }

    @Override
    public List<Location> listBarangays() {
        return repository.findByType(LocationType.BARANGAY);
    }
}
