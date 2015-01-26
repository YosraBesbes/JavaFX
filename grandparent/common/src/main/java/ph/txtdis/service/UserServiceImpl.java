package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Users;
import ph.txtdis.repository.UserRepository;
import ph.txtdis.type.UserType;

@Service
@Transactional()
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    protected UserServiceImpl() {
    }

    @Override
    public boolean exists(String username) {
        return repository.exists(username);
    }

    @Override
    public Users get(String username) {
        return repository.findOne(username);
    }

    @Override
    public Users get(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Users getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Users save(Users user) {
        return repository.save(user);
    }

    @Override
    public void delete(Users user) {
        repository.delete(user);
    }

    @Override
    public List<Users> listAll() {
        return repository.list();
    }

    @Override
    public List<Users> list(UserType type) {
        return repository.findByType(type);
    }
}
