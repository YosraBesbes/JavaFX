package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional()
public abstract class AbstractListedService<E> extends AbstractService<E, Integer> implements ListedService<E> {

    protected AbstractListedService() {
    }

    @Override
    public List<E> list() {
        return (List<E>) repository.findAll();
    }
}
