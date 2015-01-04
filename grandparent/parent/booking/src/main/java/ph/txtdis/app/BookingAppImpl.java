package ph.txtdis.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.Reorder;
import ph.txtdis.fx.button.EditButton;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.table.BookingTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Booking;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.model.BookingDiscount;
import ph.txtdis.model.Priced;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class BookingAppImpl extends AbstractOrderApp<Booking, BookingDetail, BookingDTO> implements Reorder {

    private UserDisplay printedByDisplay;
    private TimestampDisplay printedOnDisplay;
    private BookingTable bookingTable;

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
    protected void setButtons() {
        super.setButtons();
        buttons.put("re-use", new EditButton<Booking>(this).getButton());
    }

    @Override
    protected void createOrderedLabeledInputs() {
        super.createOrderedLabeledInputs();
        dateLabel.setText("Pick Date");
    }

    @Override
    protected void setBindings() {
        super.setBindings();
        buttons.get("re-use")
                .disableProperty()
                .bind(buttons.get("save").disabledProperty().not()
                        .or(datePicker.valueProperty().isEqualTo(LocalDate.now())));

        partnerIdField.editableProperty().bind(FX.isEmpty(partnerNameField));
        partnerIdField.focusTraversableProperty().bind(partnerIdField.editableProperty());
    }

    @Override
    public void createDetailTable() {
        bookingTable = new BookingTable(this, orderDTO);
        detailTable = bookingTable.getTable();
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = (BookingDetail) priced;
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
        discountList.clear();
        List<BookingDiscount> discounts = orderDTO.getDiscounts();
        if (discounts != null && !discounts.isEmpty())
            setDiscountList(discounts);
    }

    private void setDiscountList(List<BookingDiscount> discounts) {
        if (discounts.size() == 1) {
            BookingDiscount discount = discounts.get(0);
            discountList.add("[" + discount.getPerCent() + "%]: " + DIS.formatCurrency(discount.getValue()));
        } else {
            discountList.add("[TOTAL]: ");
            BigDecimal total = BigDecimal.ZERO;
            for (BookingDiscount discount : discounts) {
                BigDecimal value = discount.getValue();
                discountList.add("[" + discount.getLevel() + "- " + discount.getPerCent() + "%]: "
                        + DIS.formatCurrency(value));
                total = total.add(value);
            }
            discountList.set(0, discountList.get(0) + DIS.formatCurrency(total));
        }
    }

    @Override
    protected void setUserBox() {
        super.setUserBox();
        userHBox.getChildren().addAll(addPrinterNodes());
    }

    private Node[] addPrinterNodes() {
        Label printedByLabel = new Label("Printed By");
        printedByDisplay = new UserDisplay(orderDTO.getPrintedBy());
        Label printedOnLabel = new Label("On");
        printedOnDisplay = new TimestampDisplay(orderDTO.getPrintedOn());
        return new Node[] { printedByLabel, printedByDisplay, printedOnLabel, printedOnDisplay };
    }

    @Override
    public void refresh() {
        bookingTable.resetLineNo();
        updatePrintStamps();
        super.refresh();
    }

    private void updatePrintStamps() {
        printedByDisplay.setText(DIS.toString(orderDTO.getPrintedBy()));
        printedOnDisplay.setText(Util.formatZonedDateTime(orderDTO.getPrintedOn()));
    }

    @Override
    public void reorder() {
        orderDTO.reorder();
        updateDisplayValues();
    }

    private void updateDisplayValues() {
        updateInputFields();
        updateCreationStamps();
        updatePrintStamps();
    }
}
