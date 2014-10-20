package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import ph.txtdis.model.ItemFamily;
import ph.txtdis.service.ItemFamilyService;
import ph.txtdis.type.ItemTier;

@Component
public class ItemFamilyDTOImpl extends AbstractListedNamedDTO<ItemFamily, ItemFamilyService> implements ItemFamilyDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new ItemFamily();
    }

    @Override
    public ItemTier getTier() {
        return entity.getTier();
    }

    @Override
    public void setTier(ItemTier tier) {
        entity.setTier(tier);
    }
}
