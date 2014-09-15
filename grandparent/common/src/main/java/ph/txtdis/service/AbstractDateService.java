package ph.txtdis.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional()
public abstract class AbstractDateService<E> implements DateService<E> {

    @Autowired
    protected CrudRepository<E, LocalDate> repository;

    protected AbstractDateService() {
    }

    @Override
    public boolean exists(LocalDate idDate) {
        return repository.exists(idDate);
    }

    @Override
    public E get(LocalDate idDate) {
        return repository.findOne(idDate);
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
