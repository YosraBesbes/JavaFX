package ph.txtdis.fx.dialog;

import java.math.BigDecimal;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.dto.ReceivingDTO;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.model.Receiving;
import ph.txtdis.model.ReceivingDetail;
import ph.txtdis.type.UomType;

public class ReceivingDialog extends AbstractOrderDialog<Receiving, ReceivingDetail, ReceivingDTO> {

    public ReceivingDialog(Stage stage, ReceivingDTO dto) {
        super("Receiving", stage, dto, 0);
    }

    @Override
    protected QualityRated getQuality() {
        return App.getContext().getBean(QualityRated.class);
    }

    @Override
    protected ItemDTO getItemDTO() {
        return App.getContext().getBean(ItemDTO.class);
    }

    @Override
    protected ReceivingDetail getEntity(Receiving receiving, Item item, UomType uom, BigDecimal qty, Quality quality) {
        return new ReceivingDetail(receiving, item, uom, qty, quality);
    }
}
