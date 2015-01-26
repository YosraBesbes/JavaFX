package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Users;
import ph.txtdis.repository.InvoiceBookletRepository;
import ph.txtdis.repository.UserRepository;

@Service
@Transactional()
public class InvoiceBookletServiceImpl extends AbstractService<InvoiceBooklet, Integer> implements
        InvoiceBookletService {

    @Autowired
    private InvoiceBookletRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<InvoiceBooklet> list() {
        return (List<InvoiceBooklet>) repository.findAll();
    }

    @Override
    public List<Users> listUsers() {
        return userRepository.list();
    }

    @Override
    public InvoiceBooklet getBooklet(int id) {
        return repository.findByStartIdLessThanEqualAndEndIdGreaterThanEqual(id, id);
    }
}
