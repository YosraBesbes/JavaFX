package ph.txtdis.dto;

import ph.txtdis.model.ItemFamily;
import ph.txtdis.type.ItemTier;

public interface ItemFamilyDTO extends DTO<ItemFamily, Integer>, ListedNamed<ItemFamily> {

    ItemTier getTier();

    void setTier(ItemTier tier);
}