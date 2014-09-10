package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Location;

public interface LocationService extends Serviced<Location> {
    
    List<Location> listProvinces();
    
    List<Location> listCities();
    
    List<Location> listBarangays();
}
