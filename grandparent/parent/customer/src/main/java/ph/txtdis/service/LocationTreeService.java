package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Location;
import ph.txtdis.model.LocationTree;

public interface LocationTreeService extends Serviced<LocationTree> {

    List<Location> listCities(Location province);

    List<Location> listBarangays(Location city);
}