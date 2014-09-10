package ph.txtdis.model;

import java.math.BigDecimal;

import ph.txtdis.type.QualityType;
import ph.txtdis.type.UomType;

public interface ItemDetailed {

    Item getItem();

    void setItem(Item item);

    int getItemId();

    String getItemName();

    String getItemDescription();

    UomType getUom();

    void setUom(UomType uom);

    BigDecimal getQty();

    void setQty(BigDecimal qty);

    QualityType getQuality();

    void setQuality(QualityType quality);
}