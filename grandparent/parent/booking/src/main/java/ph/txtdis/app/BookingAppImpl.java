package ph.txtdis.app;

import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.table.BookingDetailTable;
import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Priced;

public class BookingAppImpl extends AbstractOrderApp<Booking, BookingDetail, BookingDTO> {

    public BookingAppImpl() {
        super("Booking", "S/O");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(BookingDTO.class);
        orderDTO = (BookingDTO) dto;
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
        detailTable = new BookingDetailTable(this, orderDTO).getTable();
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = (BookingDetail) priced;
    }
}
