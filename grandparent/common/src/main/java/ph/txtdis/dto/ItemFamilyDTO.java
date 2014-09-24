package ph.txtdis.dto;

import ph.txtdis.dto.ListedNamed;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.type.ItemTier;

public interface ItemFamilyDTO extends ListedNamed<ItemFamily> {

    ItemTier getTier();

    void setTier(ItemTier tier);
}