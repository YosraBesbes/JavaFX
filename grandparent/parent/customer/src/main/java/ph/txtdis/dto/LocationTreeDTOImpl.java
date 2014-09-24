package ph.txtdis.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Location;
import ph.txtdis.service.LocationTreeService;

@Component
public class LocationTreeDTOImpl implements LocationTreeDTO {
    
    @Autowired
    private LocationTreeService service;

    @Override
    public List<Location> listCities(Location province) {
        return service.listCities(province);
    }

    @Override
    public List<Location> listBarangays(Location city) {
        return service.listBarangays(city);
    }
}
