package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.Location;

public interface LocationDTO {
    
    List<Location> listProvinces();
    
    List<Location> listCities();
    
    List<Location> listBarangays();
}