package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.Account;
import ph.txtdis.repository.AccountRepository;

@Service
@Transactional()
public class AccountServiceImpl extends AbstractIdService<Account> implements AccountService {

    @Autowired
    private AccountRepository repository;

    protected AccountServiceImpl() {
    }

    @Override
    public int getMinId() {
        return repository.getMinId();
    }

    @Override
    public int getMaxId() {
        return repository.getMaxId();
    }
}
