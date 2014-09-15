package ph.txtdis.app;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.StockTakeReconciliationDTO;
import ph.txtdis.dto.StockTakeStatusDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.input.StringDisplay;
import ph.txtdis.fx.table.StockTakeReconciliationDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class StockTakeReconciliationAppImpl extends AbstractApp<StockTakeReconciliation> {

    private StockTakeReconciliationDTO reconciliation;
    private StockTakeStatusDTO status;
    private StringDisplay dateDisplay, closedByDisplay, closedOnDisplay, reconciledByDisplay, reconciledOnDisplay;

    private TableView<StockTakeReconciliationDetail> detailTable;

    public StockTakeReconciliationAppImpl() {
        super("Stock Take Reconciliation", "Date");
    }

    @Override
    protected void setDTO() {
        reconciliation = App.getContext().getBean(StockTakeReconciliationDTO.class);
        status = App.getContext().getBean(StockTakeStatusDTO.class);
    }

    @Override
    public void start() {
        super.start();
        setDisableBindings();
    }

    @Override
    public void setFocus() {
    }

    @Override
    protected Node[] addVBoxNodes() {
        Label dateLabel = new Label("Date");
        dateDisplay = new StringDisplay(getIdDate(), 100);
        Label closedByLabel = new Label("Closed By");
        closedByDisplay = new StringDisplay(getClosedBy(), 120);
        Label closedOnLabel = new Label("On");
        closedOnDisplay = new StringDisplay(getClosedOn(), 140);
        Label reconciledByLabel = new Label("Reconciled By");
        reconciledByDisplay = new StringDisplay(DIS.toString(reconciliation.getReconciledBy()), 120);
        Label reconciledOnLabel = new Label("On");
        reconciledOnDisplay = new StringDisplay(Util.formatTimestamp(reconciliation.getReconciledOn()), 140);

        detailTable = new StockTakeReconciliationDetailTable(this).getTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateDisplay, 1, 0);
        gridPane.add(closedByLabel, 2, 0);
        gridPane.add(closedByDisplay, 3, 0);
        gridPane.add(closedOnLabel, 4, 0);
        gridPane.add(closedOnDisplay, 5, 0);
        gridPane.add(reconciledByLabel, 6, 0);
        gridPane.add(reconciledByDisplay, 7, 0);
        gridPane.add(reconciledOnLabel, 8, 0);
        gridPane.add(reconciledOnDisplay, 9, 0);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);

        return new Node[] { routingBox };
    }

    private String getIdDate() {
        LocalDate date = reconciliation.getIdDate();
        return Util.formatLocalDate(date == null ? LocalDate.now() : date);
    }

    private String getClosedBy() {
        SystemUser user = reconciliation.getClosedBy();
        return DIS.toString(user == null ? status.get(LocalDate.now()).getClosedBy() : user);
    }

    private String getClosedOn() {
        ZonedDateTime zdt = reconciliation.getClosedOn();
        return Util.formatTimestamp(zdt == null ? status.getClosedOn() : zdt);
    }

    @Override
    protected void setDisableBindings() {
        buttons.get("delete").setDisable(true);
        buttons.get("save").disableProperty().bind(FX.isEmpty(detailTable));

        // detailTable.disableProperty().bind(FX.isEmpty(checkerCombo));
    }

    @Override
    protected String getTitleName() {
        return App.title();
    }

    @Override
    public void save() throws InvalidException {
        reconciliation.setDetails(detailTable.getItems());
        super.save();
    }

    @Override
    public void refresh() {
        dateDisplay.setText(getIdDate());
        detailTable.getItems().clear();
        // detailTable.getItems().addAll(stockTake.getDetails());
        super.refresh();
    }

    @Override
    protected void addFooter(VBox box) {
    }

    @Override
    protected boolean isNew() {
        return reconciliation.getIdDate() == null;
    }
}
