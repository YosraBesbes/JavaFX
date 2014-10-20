package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Quality;
import ph.txtdis.service.QualityService;

@Component
public class QualityDTOImpl extends AbstractListedDTO<Quality, QualityService> implements QualityRated {

    @Override
    public void reset() {
        id = 0;
        entity = new Quality();
    }

    @Override
    public Quality good() {
        return service.good();
    }
}
