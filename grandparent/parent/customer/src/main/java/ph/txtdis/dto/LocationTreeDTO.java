package ph.txtdis.dto;

import java.util.List;

import ph.txtdis.model.Location;

public interface LocationTreeDTO {
    
    List<Location> listCities(Location province);

    List<Location> listBarangays(Location city);
}