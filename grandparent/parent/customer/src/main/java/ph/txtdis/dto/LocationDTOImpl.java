package ph.txtdis.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Location;
import ph.txtdis.service.LocationService;

@Component
public class LocationDTOImpl implements LocationDTO {
    
    @Autowired
    private LocationService service;

    @Override
    public List<Location> listProvinces() {
        return service.listProvinces();
    }

    @Override
    public List<Location> listCities() {
        return service.listCities();
    }

    @Override
    public List<Location> listBarangays() {
        return service.listBarangays();
    }
}
