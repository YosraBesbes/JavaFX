package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import ph.txtdis.exception.InvalidException;
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
import ph.txtdis.util.Util;

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
        bookingLabel = new Label("S/O No.");
        bookingIdField = new IdField(orderDTO.getBookingId());

        partnerIdField.setEditable(false);
        partnerIdField.focusTraversableProperty().set(false);

        datePicker.setValue(getOrderDate());
        datePicker.setDisable(true);
        datePicker.setStyle("-fx-opacity: 1");

        idField.setEditable(true);
    }

    @Override
    protected LocalDate getOrderDate() {
        return booking.getOrderDate() == null ? LocalDate.now() : booking.getOrderDate();
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(bookingLabel, 2, 0);
        gridPane.add(bookingIdField, 3, 0);
        gridPane.add(dateLabel, 4, 0);
        gridPane.add(datePicker, 5, 0);
        gridPane.add(partnerLabel, 6, 0);
        gridPane.add(partnerBox, 7, 0);

        gridPane.add(partnerAddressLabel, 0, 1);
        gridPane.add(partnerAddressField, 1, 1, 7, 1);

        gridPane.add(remarkLabel, 0, 2);
        gridPane.add(remarkField, 1, 2, 7, 1);
    }

    @Override
    public void save() throws InvalidException {
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
        detailTableItem = new InvoicingDetail(orderDTO.get(), priced.getItem(), priced.getUom(), priced.getQty(),
                priced.getQuality());
    }

    @Override
    protected void setBindings() {
        super.setBindings();
        buttons.get("save").disableProperty().unbind();
        buttons.get("save").disableProperty()
                .bind((FX.isEmpty(detailTable).and(remarkField.textProperty().isNotEqualTo("CANCELLED"))));

        bookingIdField.disableProperty().bind(FX.isEmpty(datePicker));
    }

    @Override
    protected void setListeners() {
        idField.focusedProperty().addListener((focus, oldValue, newValue) -> {
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
        } catch (InvalidException e) {
            handleError(e);
        }
    }

    private void handleError(InvalidException e) {
        new ErrorDialog(this, e.getMessage());
        orderDTO.reset();
        refresh();
    }

    private void checkForDuplicateId() throws InvalidException {
        if (orderDTO.exists(id))
            throw new InvalidException("S/I No. " + id + "\nhas been used");
        else
            checkIdInIssuedBooklet();
    }

    private void checkIdInIssuedBooklet() throws InvalidException {
        InvoiceBooklet booklet = orderDTO.getBooklet(id);
        if (booklet == null)
            throw new InvalidException("S/I No. " + id + "\nis not in any issued booklet");
        else
            checkIdIsNextToLast(booklet);
    }

    private void checkIdIsNextToLast(InvoiceBooklet booklet) throws InvalidException {
        int nextToLastId = getNextToLastId(booklet);
        if (nextToLastId != id)
            throw new InvalidException("S/I No. " + nextToLastId + "\nmust be used first");
    }

    private int getNextToLastId(InvoiceBooklet booklet) {
        int startId = booklet.getStartId();
        Integer lastId = orderDTO.getBookletLastId(startId, booklet.getEndId());
        return lastId == null ? startId : lastId.intValue() + 1;
    }

    private void verifySalesOrder(int id) throws InvalidException {
        if (booking.exists(id))
            checkSalesOrderHasBeenInvoiced(id);
        else
            throw new NotFoundException("S/O No. " + id);
    }

    private void checkSalesOrderHasBeenInvoiced(int bookingId) throws InvalidException {
        booking.setById(bookingId);
        handleSalesOrderHasBeenInvoicedCheck(getInvoiceId());
    }

    private Integer getInvoiceId() {
        return orderDTO.getIdFromSalesOrder(booking.get());
    }

    private void handleSalesOrderHasBeenInvoicedCheck(Integer invoiceId) throws InvalidException {
        if (invoiceId == null)
            checkSalesOrderWasPicked();
        else
            throw new InvalidException("S/O No. " + booking.getId() + "\nhas been invoiced per\nS/I No. " + invoiceId);
    }

    private void checkSalesOrderWasPicked() throws InvalidException {
        handleSalesOrderHasBeenPickedCheck(getPickDate());
    }

    private LocalDate getPickDate() {
        return orderDTO.getPickDateFromSalesOrder(booking.get());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void handleSalesOrderHasBeenPickedCheck(LocalDate pickDate) throws InvalidException {
        if (pickDate == null)
            throw new InvalidException("S/O No. " + booking.getId() + "\nhas not been picked");
        else if (isPickedBeforeYesterday(pickDate))
            throw new InvalidException("S/O No. " + booking.getId() + "is not part of the delivery\n"
                    + "today nor the previous business day;\nit was picked last " + Util.formatDate(pickDate));
        else
            populateFields((OrderDTO) booking);
    }

    @Override
    protected void populateFields(OrderDTO<Ordered<Priced>, Priced> dto) {
        datePicker.setValue(getPickDate());
        super.populateFields(dto);
    }

    private boolean isPickedBeforeYesterday(LocalDate pickDate) {
        return pickDate.isBefore(LocalDate.now().minusDays(minus2DaysOnWeekends(pickDate)));
    }

    private int minus2DaysOnWeekends(LocalDate pickDate) {
        return pickDate.getDayOfWeek() == DayOfWeek.MONDAY ? 2 : 1;
    }

    @Override
    public void refresh() {
        total = BigDecimal.ZERO;
        bookingIdField.setIdNo(orderDTO.getBookingId());
        super.refresh();
    }
}
