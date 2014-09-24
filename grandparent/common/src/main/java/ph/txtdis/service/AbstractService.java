package ph.txtdis.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional()
public abstract class AbstractService<E, K extends Serializable> implements Serviced<E, K> {

    @Autowired
    protected CrudRepository<E, K> repository;

    protected AbstractService() {
    }

    @Override
    public boolean exists(K id) {
        return repository.exists(id);
    }

    @Override
    public E get(K id) {
        return repository.findOne(id);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }
}
