package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.Quality;
import ph.txtdis.service.QualityService;

@Component
public class QualityDTOImpl extends AbstractTypedDTO<Quality, QualityService> implements QualityDTO {

    @Override
    public Quality good() {
        return service.good();
    }
}
