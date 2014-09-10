package ph.txtdis.app;

import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.fx.table.PurchasingDetailTable;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;

public class PurchasingAppImpl extends AbstractOrderApp<Purchasing, PurchasingDetail, PurchasingDTO> {

    public PurchasingAppImpl() {
        super("Purchasing", "P/O");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(PurchasingDTO.class);
        orderDTO = (PurchasingDTO) dto;
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
    public void createDetailTable() {
        detailTable = new PurchasingDetailTable(this, orderDTO).getTable();
    }

    @Override
    protected String getTitleName() {
        return App.title();
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
        detailTableItem.setDaysLevelBefore(999);
        detailTableItem.setDaysLevelAfter(999);
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
