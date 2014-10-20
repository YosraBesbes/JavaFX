package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.ItemFamily;
import ph.txtdis.repository.ItemFamilyRepository;

@Service
@Transactional()
public class ItemFamilyServiceImpl extends AbstractListedService<ItemFamily> implements ItemFamilyService {

    @Autowired
    private ItemFamilyRepository repository;

    @Override
    public ItemFamily get(String name) {
        return repository.findOneByName(name);
    }
}
