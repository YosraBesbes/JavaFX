package ph.txtdis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ph.txtdis.model.ItemTree;

@Service
@Transactional()
public class ItemTreeServiceImpl extends AbstractListedService<ItemTree> implements ItemTreeService {
}
