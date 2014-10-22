package ph.txtdis.app;

import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.fx.button.CancelButton;
import ph.txtdis.fx.table.PurchasingTable;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;

public class PurchasingAppImpl extends AbstractOrderApp<Purchasing, PurchasingDetail, PurchasingDTO> {

    public PurchasingAppImpl() {
        super("Purchasing", "P/O");
    }

    @Override
    protected void setDTO() {
        dto = orderDTO = App.getContext().getBean(PurchasingDTO.class);
        super.setDTO();
    }

    @Override
    public void setCustomerDTO() {
        customer = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void setItemDTO() {
        item = App.getContext().getBean(ItemDTO.class);
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("cancel", new CancelButton<Purchasing>(this, dto).getButton());
    }

    @Override
    public void createDetailTable() {
        detailTable = new PurchasingTable(this, orderDTO).getTable();
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
        detailTableItem.setDaysLevel(999);
    }

    @Override
    public void setPrice() {
        price = item.getLatestPurchasePrice(getPickerDate()).multiply(getQtyPerUomUnit());
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = (PurchasingDetail) priced;
    }
}
