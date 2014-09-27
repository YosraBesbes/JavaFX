package ph.txtdis.app;

import java.math.BigDecimal;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.InvoicingDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.OrderDTO;
import ph.txtdis.exception.TxtdisException;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.table.InvoicingDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.model.Ordered;
import ph.txtdis.model.Priced;

public class InvoicingAppImpl extends AbstractOrderApp<Invoicing, InvoicingDetail, InvoicingDTO> {

    private BookingDTO booking;
    private IdField bookingIdField;
    private Label bookingLabel;
    private int id;

    public InvoicingAppImpl() {
        super("Invoicing", "S/I");
    }

    @Override
    protected void setDTO() {
        dto = orderDTO = App.getContext().getBean(InvoicingDTO.class);
        booking = App.getContext().getBean(BookingDTO.class);
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
    public void setFocus() {
        idField.requestFocus();
    }

    @Override
    public void createDetailTable() {
        detailTable = new InvoicingDetailTable(this, orderDTO).getTable();
    }

    @Override
    protected void createOrderedLabeledInputs() {
        super.createOrderedLabeledInputs();
        bookingLabel = new Label("S/O ID No.");
        bookingIdField = new IdField(orderDTO.getBookingId());
        idField.setEditable(true);
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);

        gridPane.add(bookingLabel, 0, 1);
        gridPane.add(bookingIdField, 1, 1);
        gridPane.add(partnerLabel, 2, 1);
        gridPane.add(partnerBox, 3, 1);

        gridPane.add(partnerAddressLabel, 0, 2);
        gridPane.add(partnerAddressField, 1, 2, 3, 1);

        gridPane.add(remarkLabel, 0, 3);
        gridPane.add(remarkField, 1, 3, 3, 1);
    }

    @Override
    public void save() throws TxtdisException {
        orderDTO.setBooking(booking.get(bookingIdField.getIdNo()));
        super.save();
    }

    @Override
    public void createDetailTableItems(OrderDTO<Ordered<Priced>, Priced> dto) {
        detailTableItems = new ArrayList<>();
        iterateDetailTableItems(dto.getDetails());
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
        detailTableItems.add(detailTableItem);
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = new InvoicingDetail(orderDTO.get(), priced.getItem(), priced.getUom(), priced.getQty());
    }

    @Override
    protected void setBindings() {
        super.setBindings();
        partnerIdField.setEditable(false);
        partnerIdField.setFocusTraversable(false);
        bookingIdField.disableProperty().bind(FX.isEmpty(datePicker));

        buttons.get("save").disableProperty().unbind();
        buttons.get("save").disableProperty()
                .bind((FX.isEmpty(detailTable).and(remarkField.textProperty().isNotEqualTo("CANCELLED"))));
    }

    @Override
    protected void setListeners() {
        idField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            id = idField.getIdNo();
            if (dto.getId() == 0 && id != 0)
                handleIdInput(newValue);
        });

        bookingIdField.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                try {
                    verifySalesOrder(bookingIdField.getIdNo());
                } catch (Exception e) {
                    new ErrorDialog(this, e.getMessage());
                }
        });
    }

    private void handleIdInput(Boolean newValue) {
        if (!newValue)
            validateId();
        else
            refresh();
    }

    private void validateId() {
        try {
            checkForDuplicateId();
            ;
        } catch (TxtdisException e) {
            handleError(e);
        }
    }

    private void handleError(TxtdisException e) {
        new ErrorDialog(this, e.getMessage());
        refresh();
    }

    private void checkForDuplicateId() throws TxtdisException {
        if (orderDTO.exists(id))
            throw new TxtdisException("S/I No. " + id + "\nhas been used");
        else
            checkIdInIssuedBooklet();
    }

    private void checkIdInIssuedBooklet() throws TxtdisException {
        InvoiceBooklet booklet = orderDTO.getBooklet(id);
        if (booklet == null)
            throw new TxtdisException("S/I No. " + id + "\nis not in any issued booklet");
        else
            checkIdIsNextToLast(booklet);
    }

    private void checkIdIsNextToLast(InvoiceBooklet booklet) throws TxtdisException {
        int nextToLastId = getNextToLastId(booklet);
        if (nextToLastId != id)
            throw new TxtdisException("S/I No. " + nextToLastId + "\nmust be used first");
    }

    private int getNextToLastId(InvoiceBooklet booklet) {
        int startId = booklet.getStartId();
        Integer lastId = orderDTO.getBookletLastId(startId, booklet.getEndId());
        return lastId == null ? startId : lastId.intValue() + 1;
    }

    private void verifySalesOrder(int id) throws NotFoundException, TxtdisException {
        if (booking.exists(id))
            checkSalesOrderIsUnused(id);
        else
            throw new NotFoundException("S/O No. " + id);
    }

    private void checkSalesOrderIsUnused(int bookingId) throws TxtdisException {
        booking.setById(bookingId);
        Integer invoiceId = orderDTO.getIdBySalesOrder(booking.get());
        handleUnusedSalesOrderCheck(bookingId, invoiceId);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void handleUnusedSalesOrderCheck(int bookingId, Integer invoiceId) throws TxtdisException {
        if (invoiceId == null)
            populateFields((OrderDTO) booking);
        else
            throw new TxtdisException("S/O No. " + bookingId + "\nhas been used in\nS/I No. " + invoiceId);
    }

    @Override
    public void refresh() {
        total = BigDecimal.ZERO;
        bookingIdField.setIdNo(orderDTO.getBookingId());
        super.refresh();
    }
}
