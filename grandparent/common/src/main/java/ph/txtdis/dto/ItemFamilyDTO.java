package ph.txtdis.dto;

import ph.txtdis.dto.Listed;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.type.ItemTier;

public interface ItemFamilyDTO extends Listed<ItemFamily> {

    ItemTier getTier();

    void setTier(ItemTier tier);
}