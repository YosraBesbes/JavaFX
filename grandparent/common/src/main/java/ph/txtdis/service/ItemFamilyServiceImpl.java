package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.ItemFamily;

@Service
@Transactional()
public class ItemFamilyServiceImpl extends AbstractListedService<ItemFamily> implements ItemFamilyService {

    protected ItemFamilyServiceImpl() {
    }
}
