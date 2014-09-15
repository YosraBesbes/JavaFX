package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Location;

public interface LocationService extends IdService<Location> {
    
    List<Location> listProvinces();
    
    List<Location> listCities();
    
    List<Location> listBarangays();
}
