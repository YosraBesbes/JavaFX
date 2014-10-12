package ph.txtdis.fx.tab;

import java.time.ZonedDateTime;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.LoadSettlementAdjustmentDTO;
import ph.txtdis.dto.LoadSettlementDTO;
import ph.txtdis.dto.TruckDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.table.LoadSettlementDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.LoadSettlementAdjustment;
import ph.txtdis.model.LoadSettlementFilteredDetail;
import ph.txtdis.model.Truck;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Login;

public class LoadSettlementPerTruckTab extends AbstractTab<LoadSettlementDTO> implements SettlementTab, TabledPerTruck {

    private LoadSettlementAdjustmentDTO adjustment;
    private TimestampDisplay closedOnDisplay, reconciledOnDisplay;
    private TruckDTO truckDTO;
    private UserDisplay closedByDisplay, reconciledByDisplay;
    private TableView<LoadSettlementFilteredDetail> detailTable;

    public LoadSettlementPerTruckTab(Truck truck, Stage stage, LoadSettlementDTO dto) {
        super(truck.getName(), truck.getName(), stage, dto);
        setBindings();
    }

    @Override
    protected void setDTO(LoadSettlementDTO dto) {
        super.setDTO(dto);
        truckDTO = App.getContext().getBean(TruckDTO.class);
        adjustment = App.getContext().getBean(LoadSettlementAdjustmentDTO.class);
    }

    @Override
    protected Node[] addNodes(Stage stage, LoadSettlementDTO dto) {

        Label closedByLabel = new Label("Closed by");
        closedByDisplay = new UserDisplay(dto.getClosedBy());
        Label closedOnLabel = new Label("on");
        closedOnDisplay = new TimestampDisplay(dto.getClosedOn());

        Label reconciledByLabel = new Label("Settled by");
        reconciledByDisplay = new UserDisplay(dto.getReconciledBy());
        Label reconciledOnLabel = new Label("on");
        reconciledOnDisplay = new TimestampDisplay(dto.getReconciledOn());

        detailTable = new LoadSettlementDetailTable(stage).getTable();
        detailTable.setItems(dto.getSettlementFilteredDetail(getTruck(), dto.getDate()));
        detailTable.setId(name);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(closedByLabel, 0, 1);
        gridPane.add(closedByDisplay, 1, 1);
        gridPane.add(closedOnLabel, 2, 1);
        gridPane.add(closedOnDisplay, 3, 1);

        gridPane.add(reconciledByLabel, 4, 1);
        gridPane.add(reconciledByDisplay, 5, 1);
        gridPane.add(reconciledOnLabel, 6, 1);
        gridPane.add(reconciledOnDisplay, 7, 1);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);
        return new Node[] { routingBox };
    }

    private Truck getTruck() {
        return truckDTO.get(name);
    }

    private void setBindings() {
        detailTable.editableProperty().bind(FX.isEmpty(reconciledByDisplay));
    }

    @Override
    public boolean isDataReadyToBeSaved() {
        try {
            ensureVariancesAreJustified();
            return true;
        } catch (Exception e) {
            new ErrorDialog(stage, e.getMessage());
            return false;
        }
    }

    @Override
    public void save() {
        if (reconciledByDisplay.textProperty().isEmpty().get())
            saveAdjustments();
    }

    private void ensureVariancesAreJustified() throws InvalidException {
        for (LoadSettlementFilteredDetail item : detailTable.getItems())
            if (isVarianceUnjustified(item))
                throw new InvalidException("A specific action must be taken\non each and every " + dto.getTruck()
                        + " item variances");
    }

    private boolean isVarianceUnjustified(LoadSettlementFilteredDetail item) {
        return !DIS.isZero(item.getSoldQty().add(item.getReturnedQty()).subtract(item.getPickedQty()))
                && DIS.isEmpty(item.getActionTaken());
    }

    private void saveAdjustments() {
        for (LoadSettlementFilteredDetail item : detailTable.getItems())
            saveAdjustment(item);
        updateSettlementStamps();
    }

    private void saveAdjustment(LoadSettlementFilteredDetail item) {
        adjustment.set(new LoadSettlementAdjustment(dto.getDate(), dto.getTruck(), item.getItem(), item
                .getAdjustmentQty(), item.getActionTaken()));
        adjustment.save();
    }

    private void updateSettlementStamps() {
        dto.setReconciledBy(Login.user());
        dto.setReconciledOn(ZonedDateTime.now());
        dto.save();
    }

    @Override
    public void refresh() {
        closedByDisplay.setUser(dto.getClosedBy());
        closedOnDisplay.setTimestamp(dto.getClosedOn());
        reconciledByDisplay.setUser(dto.getReconciledBy());
        reconciledOnDisplay.setTimestamp(dto.getReconciledOn());
        detailTable.getItems().clear();
        detailTable.getItems().addAll(dto.getSettlementFilteredDetail(getTruck(), dto.getDate()));
    }

    @Override
    public BooleanBinding isAllReconciled() {
        return FX.isEmpty(reconciledByDisplay).not();
    }

    @Override
    public BooleanBinding isAnyOpen() {
        return FX.isEmpty(closedByDisplay);
    }

    @Override
    public TableView<?> getTable() {
        return detailTable;
    }
}
