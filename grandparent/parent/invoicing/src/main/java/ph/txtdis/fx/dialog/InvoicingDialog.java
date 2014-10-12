package ph.txtdis.fx.dialog;

import java.math.BigDecimal;

import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.QualityRated;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.model.Item;
import ph.txtdis.model.Quality;
import ph.txtdis.type.UomType;

public class InvoicingDialog extends AbstractOrderDialog<Invoicing, InvoicingDetail, InvoicingDTO> {

    public InvoicingDialog(Stage stage, InvoicingDTO dto, int lineNo) {
        super("Invoicing", stage, dto, lineNo);
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
    protected InvoicingDetail getEntity(Invoicing invoicing, Item item, UomType uom, BigDecimal qty, Quality quality) {
        return new InvoicingDetail(invoicing, item, uom, qty, quality);
    }
}
