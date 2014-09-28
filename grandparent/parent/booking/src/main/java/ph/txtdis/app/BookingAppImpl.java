package ph.txtdis.app;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.Label;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.input.StringDisplay;
import ph.txtdis.fx.table.BookingDetailTable;
import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.Priced;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class BookingAppImpl extends AbstractOrderApp<Booking, BookingDetail, BookingDTO> {

    private StringDisplay printedByDisplay, printedOnDisplay;

    public BookingAppImpl() {
        super("Booking", "S/O");
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
    protected void createOrderedLabeledInputs() {
        super.createOrderedLabeledInputs();
        dateLabel.setText("Pick Date");
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        datePicker.setOnAction(event -> validateDate(datePicker.getValue()));
    }

    private void validateDate(LocalDate date) {
        if (date != null && idField.getText().isEmpty() && date.isBefore(LocalDate.now()))
            handleError(this, "Pick date cannot be in the past");
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
    protected void setUserBox() {
        super.setUserBox();
        userHBox.getChildren().addAll(addPrinterNodes());
    }

    private Node[] addPrinterNodes() {
        Label printedByLabel = new Label("Printed By");
        printedByDisplay = new StringDisplay(DIS.toString(orderDTO.getPrintedBy()));
        Label printedOnLabel = new Label("On");
        printedOnDisplay = new StringDisplay(Util.formatZonedDateTime(orderDTO.getPrintedOn()));
        return new Node[] { printedByLabel, printedByDisplay, printedOnLabel, printedOnDisplay };
    }

    @Override
    public void refresh() {
        printedByDisplay.setText(DIS.toString(orderDTO.getPrintedBy()));
        printedOnDisplay.setText(Util.formatZonedDateTime(orderDTO.getPrintedOn()));
        super.refresh();
    }
}
