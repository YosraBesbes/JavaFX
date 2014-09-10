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
import ph.txtdis.dto.PickingDTO;
import ph.txtdis.dto.TruckDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.fx.button.PrintButton;
import ph.txtdis.fx.button.SearchByDateButton;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.PickingDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Picking;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.model.Truck;

public class PickingAppImpl extends AbstractApp<Picking> implements Searched, Printable {
    
    private PickingDTO picking;
    private TruckDTO truck;
    private UserDTO user;

    private IdField idField;
    private ComboBox<SystemUser> driverCombo, helper1Combo, helper2Combo;
    private ComboBox<Truck> truckCombo;
    private DatePicker datePicker;
    private StringField remarkField;
    private TableView<PickingDetail> detailTable;
    private PickingDetailTable pickingTable;

    public PickingAppImpl() {
        super("Picking", "Pick List");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(PickingDTO.class);
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.replace("search", new SearchByDateButton<Picking>(this, dto).getButton());
        buttons.put("print", new PrintButton<Picking>(this, dto).getButton());
   }

    @Override
    public void setFocus() {
        truckCombo.requestFocus();
    }

    @Override
    protected Node[] addVBoxNodes() {
        truck = App.getContext().getBean(TruckDTO.class);
        user = App.getContext().getBean(UserDTO.class);
        picking = (PickingDTO) dto;

        Label idLabel = new Label("ID No.");
        idField = new IdField(picking.getId());
        idField.setEditable(false);

        Label dateLabel = new Label("Date");
        datePicker = new DatePicker(picking.getPickDate());

        Label truckLabel = new Label("Truck");
        truckCombo = FX.createComboBox(truck.list(), picking.getTruck());

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

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(truckLabel, 2, 0);
        gridPane.add(truckCombo, 3, 0);
        gridPane.add(dateLabel, 4, 0);
        gridPane.add(datePicker, 5, 0);
        
        gridPane.add(driverLabel, 0, 1);
        gridPane.add(driverCombo, 1, 1);
        gridPane.add(helper1Label, 2, 1);
        gridPane.add(helper1Combo, 3, 1);
        gridPane.add(helper2Label, 4, 1);
        gridPane.add(helper2Combo, 5, 1);

        gridPane.add(remarkLabel, 0, 2);
        gridPane.add(remarkField, 1, 2, 5, 2);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);

        return new Node[] { routingBox };
    }

    @Override
    protected void setDisableBindings() {
        buttons.get("delete").setDisable(true);
        buttons.get("save").disableProperty().bind(FX.isEmpty(detailTable).or(FX.isEmpty(idField).not()));

        datePicker.disableProperty().bind(FX.isEmpty(truckCombo));
        driverCombo.disableProperty().bind(FX.isEmpty(datePicker));
        helper1Combo.disableProperty().bind(FX.isEmpty(driverCombo));
        helper2Combo.disableProperty().bind(FX.isEmpty(driverCombo));
        remarkField.disableProperty().bind(FX.isEmpty(helper1Combo));
        detailTable.disableProperty().bind(FX.isEmpty(helper1Combo));
    }

    @Override
    protected void setListeners() {
        datePicker.valueProperty().addListener((date, oldDate, newDate) -> {
            detailTable.getItems().clear();
            pickingTable.createTableContextMenu(new ContextMenu());
        });
    }

    @Override
    protected String getTitleName() {
        return App.title();
    }

    @Override
    public String setSearchedCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void listFind() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void save() {
        picking.setPickDate(datePicker.getValue());
        picking.setTruck(truckCombo.getValue());
        picking.setDriver(driverCombo.getValue());
        picking.setHelper1(helper1Combo.getValue());
        picking.setHelper2(helper2Combo.getValue());
        picking.setRemarks(remarkField.getText());
        picking.setDetails(detailTable.getItems());
        super.save();
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
        super.refresh();    
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        
    }

    public LocalDate getPickerDate() {
        return datePicker.getValue();
    }
}
