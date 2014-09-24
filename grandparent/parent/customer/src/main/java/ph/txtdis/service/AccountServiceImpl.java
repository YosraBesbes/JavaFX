package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Account;
import ph.txtdis.repository.AccountRepository;

@Service
@Transactional()
public class AccountServiceImpl extends AbstractService<Account, Integer> implements AccountService {

    @Autowired
    private AccountRepository repository;

    protected AccountServiceImpl() {
    }

    @Override
    public Integer getMinId() {
        return repository.getMinId();
    }

    @Override
    public Integer getMaxId() {
        return repository.getMaxId();
    }
}
