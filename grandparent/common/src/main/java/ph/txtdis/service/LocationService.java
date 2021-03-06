package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Location;

public interface LocationService extends Serviced<Location, Integer> {

    List<Location> listProvinces();

    List<Location> listCities();

    List<Location> listBarangays();
}
