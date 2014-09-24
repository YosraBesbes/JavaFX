package ph.txtdis.app;

import java.time.LocalDate;

import javafx.scene.layout.GridPane;
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

    private LocalDate getDate() {
        return isNew() ? tomorrow() : orderDTO.getOrderDate();
    }

    private boolean isNew() {
        return idField.getText().isEmpty();
    }

    private LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    @Override
    public void setFocus() {
        partnerIdField.requestFocus();
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        super.addGridPaneNodes(gridPane);
        setAdditionalDatePickerProperties();
    }

    private void setAdditionalDatePickerProperties() {
        datePicker.setEditable(false);
        datePicker.focusTraversableProperty().set(false);
        datePicker.setValue(getDate());
    }

    @Override
    protected void setDTO() {
        dto = orderDTO = App.getContext().getBean(BookingDTO.class);
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

    @Override
    public void refresh() {
        super.refresh();
        datePicker.setValue(isNew() ? tomorrow() : orderDTO.getOrderDate());
    }
}
