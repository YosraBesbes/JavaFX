package ph.txtdis.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Users;
import ph.txtdis.repository.RoleRepository;

@Service
@Transactional()
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<String> getRoles(Users username) {
        return repository.getRoles(username);
    }
}
