package ph.txtdis.app;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.StockTakeDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.dto.WarehouseDTO;
import ph.txtdis.exception.TxtdisException;
import ph.txtdis.fx.dialog.CurrentStockTakeClosureOptionDialog;
import ph.txtdis.fx.dialog.CutoffOptionDialog;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.table.StockTakeDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Warehouse;

public class StockTakeAppImpl extends AbstractIdApp<StockTake> {

    private StockTakeDTO stockTake;
    private UserDTO user;
    private WarehouseDTO warehouse;

    private IdField idField;
    private ComboBox<SystemUser> takerCombo, checkerCombo;
    private ComboBox<Warehouse> warehouseCombo;
    private DatePicker datePicker;
    private TableView<StockTakeDetail> detailTable;

    public StockTakeAppImpl() {
        super("Stock Take", "Tag");
    }

    @Override
    protected void setDTO() {
        dto = stockTake = App.getContext().getBean(StockTakeDTO.class);
        user = App.getContext().getBean(UserDTO.class);
        warehouse = App.getContext().getBean(WarehouseDTO.class);
    }

    @Override
    public void setFocus() {
        datePicker.requestFocus();
    }

    @Override
    protected Node[] addVBoxNodes() {

        Label idLabel = new Label("ID No.");
        idField = new IdField(stockTake.getId());
        idField.setEditable(false);

        Label dateLabel = new Label("Date");
        datePicker = new DatePicker(stockTake.getStockTakeDate());

        Label warehouseLabel = new Label("Warehouse");
        warehouseCombo = FX.createComboBox(warehouse.list(), stockTake.getWarehouse());

        Label takerLabel = new Label("Taker");
        takerCombo = FX.createComboBox(user.list(), stockTake.getTaker());

        Label checkerLabel = new Label("Checker");
        checkerCombo = FX.createComboBox(user.list(), stockTake.getChecker());

        detailTable = new StockTakeDetailTable(this, stockTake).getTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(warehouseLabel, 4, 0);
        gridPane.add(warehouseCombo, 5, 0);
        gridPane.add(takerLabel, 6, 0);
        gridPane.add(takerCombo, 7, 0);
        gridPane.add(checkerLabel, 8, 0);
        gridPane.add(checkerCombo, 9, 0);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);

        return new Node[] { routingBox };
    }

    @Override
    protected void setBindings() {
        buttons.get("save").disableProperty().bind(FX.isEmpty(detailTable).or(FX.isEmpty(idField).not()));

        warehouseCombo.disableProperty().bind(FX.isEmpty(datePicker));
        takerCombo.disableProperty().bind(FX.isEmpty(warehouseCombo));
        checkerCombo.disableProperty().bind(FX.isEmpty(takerCombo));
        detailTable.disableProperty().bind(FX.isEmpty(checkerCombo));
    }

    @Override
    protected void setListeners() {
        datePicker.setOnAction(event -> {
            try {
                handleDateChange(datePicker.getValue());
            } catch (TxtdisException e) {
                new ErrorDialog(this, e.getMessage());
                refresh();
            }
        });
    }

    private void handleDateChange(LocalDate newDate) throws TxtdisException {
        if (newDate != null) {
            verifyDate(newDate);
            cutOtherTransactionsWhileOnGoing(newDate);
        }
    }

    private void verifyDate(LocalDate date) throws TxtdisException {
        verifyNotInTheFuture(date);
        verifyNoStockTakeAfter(date);
        verifyNoTransactionsAfter(date);
        verifyNoOpenStockTake(date);
        verifyNotClosed(date);
    }

    private void verifyNotInTheFuture(LocalDate date) throws TxtdisException {
        if (date.isAfter(LocalDate.now()))
            throw new TxtdisException("A new stock take cannot\noccur in the future");
    }

    private void verifyNoStockTakeAfter(LocalDate date) throws TxtdisException {
        String laterStockTake = stockTake.getStockTakeAfter(date);
        if (laterStockTake != null)
            throw new TxtdisException("A new stock take must be the latest;\n" + laterStockTake
                    + " is of a later date.");
    }

    private void verifyNoTransactionsAfter(LocalDate date) throws TxtdisException {
        String laterTransaction = stockTake.getOneTransactionAfter(date);
        if (laterTransaction != null)
            throw new TxtdisException("A new stock take must precede other transactions;\n" + laterTransaction
                    + " has been posted on a later date");
    }

    private void verifyNoOpenStockTake(LocalDate date) throws TxtdisException {
        String onGoingStockTake = stockTake.getOnGoingStockTake(date);
        if (onGoingStockTake != null)
            throw new TxtdisException(onGoingStockTake);
    }

    private void verifyNotClosed(LocalDate date) throws TxtdisException {
        String closureInfo = stockTake.getClosureInfo(date);
        if (closureInfo != null)
            throw new TxtdisException(closureInfo);
    }

    private void cutOtherTransactionsWhileOnGoing(LocalDate date) {
        if (isFirstTag(date) && !new CutoffOptionDialog(this, date).isAffirmative())
            this.close();
    }

    private boolean isFirstTag(LocalDate date) {
        LocalDate latest = stockTake.getLatestCutoffDate();
        return latest == null ? true : latest.isBefore(date);
    }

    @Override
    public void save() throws TxtdisException {
        stockTake.setWarehouse(warehouseCombo.getValue());
        stockTake.setStockTakeDate(datePicker.getValue());
        stockTake.setTaker(takerCombo.getValue());
        stockTake.setChecker(checkerCombo.getValue());
        stockTake.setDetails(detailTable.getItems());
        stockTake.save();
    }

    @Override
    public void refresh() {
        idField.setIdNo(dto.getId());
        warehouseCombo.setValue(stockTake.getWarehouse());
        datePicker.setValue(stockTake.getStockTakeDate());
        takerCombo.setValue(stockTake.getTaker());
        checkerCombo.setValue(stockTake.getChecker());
        detailTable.getItems().clear();
        detailTable.getItems().addAll(stockTake.getDetails());
        new CurrentStockTakeClosureOptionDialog(this, datePicker.getValue());
        super.refresh();
    }
}
