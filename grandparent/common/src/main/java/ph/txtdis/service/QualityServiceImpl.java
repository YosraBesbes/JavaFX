package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.Quality;

@Service
@Transactional()
public class QualityServiceImpl extends AbstractListedService<Quality> implements QualityService {

    @Override
    public Quality good() {
        return repository.findOne(1);
    }
}
