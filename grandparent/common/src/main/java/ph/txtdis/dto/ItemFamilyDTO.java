package ph.txtdis.dto;

import ph.txtdis.dto.TypedDTO;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.type.ItemTier;

public interface ItemFamilyDTO extends TypedDTO<ItemFamily> {

    ItemTier getTier();

    void setTier(ItemTier tier);
}