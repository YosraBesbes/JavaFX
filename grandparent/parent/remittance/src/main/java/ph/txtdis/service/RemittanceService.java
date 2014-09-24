package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;

public interface RemittanceService extends SpunService<Remittance, Integer> {

    List<RemittanceDetail> getDetails(int id);
}