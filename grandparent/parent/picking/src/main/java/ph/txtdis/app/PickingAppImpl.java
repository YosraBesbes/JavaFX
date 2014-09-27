package ph.txtdis.app;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.BookingDTO;
import ph.txtdis.dto.PickingDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.exception.TxtdisException;
import ph.txtdis.fx.button.PrintButton;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.StringDisplay;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.PickListTable;
import ph.txtdis.fx.table.PickingDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.PickList;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;
import ph.txtdis.printer.PicklistPrinter;
import ph.txtdis.printer.SalesOrderPrinter;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class PickingAppImpl extends AbstractIdApp<Picking> implements Printed, Referenced<PickList> {

    private BookingDTO booking;
    private PickingDTO picking;
    private UserDTO user;

    private IdField idField;
    private ComboBox<SystemUser> driverCombo, helper1Combo, helper2Combo;
    private ComboBox<Truck> truckCombo;
    private DatePicker datePicker;
    private StringField remarkField;
    private TableView<PickingDetail> detailTable;
    private TableView<PickList> pickListTable;
    private PickingDetailTable pickingTable;
    private StringDisplay printedByDisplay, printedOnDisplay;

    public PickingAppImpl() {
        super("Picking", "List");
    }

    @Override
    public void start() {
        super.start();
        truckCombo.setItems(picking.getEmptyTrucks(getPickerDate()));
        detailTable.getItems().clear();
        pickingTable.createTableContextMenu(new ContextMenu());
    }

    @Override
    protected void setDTO() {
        dto = picking = App.getContext().getBean(PickingDTO.class);
        booking = App.getContext().getBean(BookingDTO.class);
        user = App.getContext().getBean(UserDTO.class);
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("print", new PrintButton<Picking>(this, dto).getButton());
    }

    @Override
    public void setFocus() {
        truckCombo.requestFocus();
    }

    @Override
    protected Node[] addVBoxNodes() {

        Label idLabel = new Label("ID No.");
        idField = new IdField(picking.getId());
        idField.setEditable(false);

        Label dateLabel = new Label("Date");
        datePicker = new DatePicker(getDate());
        datePicker.setEditable(false);
        datePicker.focusTraversableProperty().set(false);
        datePicker.setValue(getDate());
        datePicker.setMaxWidth(140);

        Label truckLabel = new Label("Truck");
        truckCombo = FX.createComboBox(picking.getEmptyTrucks(getPickerDate()), picking.getTruck());

        Label driverLabel = new Label("Driver");
        driverCombo = FX.createComboBox(user.list(), picking.getDriver());

        Label helper1Label = new Label("Helper 1");
        helper1Combo = FX.createComboBox(user.list(), picking.getHelper1());

        Label helper2Label = new Label("Helper 2");
        helper2Combo = FX.createComboBox(user.list(), picking.getHelper2());

        Label remarkLabel = new Label("Remarks");
        remarkField = new StringField(picking.getRemarks());

        pickingTable = new PickingDetailTable(this, picking);
        detailTable = pickingTable.getTable();

        pickListTable = new PickListTable(this).getTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(truckLabel, 4, 0);
        gridPane.add(truckCombo, 5, 0);

        gridPane.add(driverLabel, 0, 1);
        gridPane.add(driverCombo, 1, 1);
        gridPane.add(helper1Label, 2, 1);
        gridPane.add(helper1Combo, 3, 1);
        gridPane.add(helper2Label, 4, 1);
        gridPane.add(helper2Combo, 5, 1);

        gridPane.add(remarkLabel, 0, 2);
        gridPane.add(remarkField, 1, 2, 5, 2);

        HBox box = new HBox(detailTable, pickListTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);

        return new Node[] { routingBox };
    }

    private LocalDate getDate() {
        return isNew() ? LocalDate.now() : picking.getPickDate();
    }

    private boolean isNew() {
        return idField.getText().isEmpty();
    }

    @Override
    protected void setBindings() {
        buttons.get("save").disableProperty().bind(FX.isEmpty(detailTable).or(FX.isEmpty(idField).not()));
        buttons.get("print").disableProperty().bind(FX.isEmpty(pickListTable).or(FX.isEmpty(printedByDisplay).not()));

        truckCombo.disableProperty().bind(FX.isEmpty(datePicker));
        driverCombo.disableProperty().bind(FX.isEmpty(truckCombo));
        helper1Combo.disableProperty().bind(FX.isEmpty(driverCombo));
        helper2Combo.disableProperty().bind(FX.isEmpty(driverCombo));
        remarkField.disableProperty().bind(FX.isEmpty(helper1Combo));
        detailTable.disableProperty().bind(FX.isEmpty(helper1Combo));
        pickListTable.disableProperty().bind(FX.isEmpty(idField).not());
    }

    @Override
    protected void setUserBox() {
        super.setUserBox();
        userHBox.getChildren().addAll(addPrinterNodes());
    }

    private Node[] addPrinterNodes() {
        Label printedByLabel = new Label("Printed By");
        printedByDisplay = new StringDisplay(DIS.toString(picking.getPrintedBy()));
        Label printedOnLabel = new Label("On");
        printedOnDisplay = new StringDisplay(Util.formatZonedDateTime(picking.getPrintedOn()));
        return new Node[] { printedByLabel, printedByDisplay, printedOnLabel, printedOnDisplay };
    }

    @Override
    public void listFoundReferences(PickList entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public void print() throws TxtdisException {
        for (PickingDetail detail : picking.getDetails()) {
            booking.set(detail.getBooking());
            new SalesOrderPrinter(booking);
        }
        new PicklistPrinter(picking);
    }

    @Override
    public void save() throws TxtdisException {
        picking.setPickDate(datePicker.getValue());
        picking.setTruck(truckCombo.getValue());
        picking.setDriver(driverCombo.getValue());
        picking.setHelper1(helper1Combo.getValue());
        picking.setHelper2(helper2Combo.getValue());
        picking.setRemarks(remarkField.getText());
        picking.setDetails(detailTable.getItems());
        picking.save();
    }

    @Override
    public void refresh() {
        idField.setIdNo(dto.getId());
        datePicker.setValue(picking.getPickDate());
        truckCombo.setValue(picking.getTruck());
        driverCombo.setValue(picking.getDriver());
        helper1Combo.setValue(picking.getHelper1());
        helper2Combo.setValue(picking.getHelper2());
        remarkField.setText(picking.getRemarks());
        detailTable.getItems().clear();
        detailTable.getItems().addAll(picking.getDetails());
        pickListTable.getItems().clear();
        pickListTable.getItems().addAll(picking.getPickList());
        printedByDisplay.setText(DIS.toString(picking.getPrintedBy()));
        printedOnDisplay.setText(Util.formatZonedDateTime(picking.getPrintedOn()));
        super.refresh();
    }

    public LocalDate getPickerDate() {
        return datePicker.getValue();
    }
}
