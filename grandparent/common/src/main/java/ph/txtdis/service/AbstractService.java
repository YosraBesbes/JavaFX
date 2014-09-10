package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional()
public abstract class AbstractService<E> implements Serviced<E> {

    @Autowired
    protected CrudRepository<E, Integer> repository;

    protected AbstractService() {
    }

    @Override
    public boolean exists(int id) {
        return repository.exists(id);
    }

    @Override
    public E get(int id) {
        return repository.findOne(id);
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }
}
