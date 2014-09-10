package ph.txtdis.fx.tab;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ChannelDTO;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.LocationDTO;
import ph.txtdis.dto.LocationTreeDTO;
import ph.txtdis.exception.DuplicateException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.RoutingTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Channel;
import ph.txtdis.model.Customer;
import ph.txtdis.model.Location;
import ph.txtdis.model.Routing;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.VisitFrequency;

public class CustomerTab extends AbstractTab<Customer, CustomerDTO> {

    private ChannelDTO channel;
    private CustomerDTO customer;
    private LocationDTO location;
    private LocationTreeDTO locationTree;

    private ComboBox<Channel> channelCombo;
    private ComboBox<CustomerType> typeCombo;
    private ComboBox<Location> provinceCombo, cityCombo, barangayCombo;
    private ComboBox<VisitFrequency> visitCombo;
    private IdField idField;
    private StringField nameField, addressField;
    private TableView<Routing> routingTable;

    public CustomerTab(Stage stage, CustomerDTO dto) {
        super("Basic Information", stage, dto);
        setDisableBindings();
        setEventListeners(stage);
    }

    private void setDisableBindings() {
        addressField.disableProperty().bind(FX.isEmpty(nameField));
        provinceCombo.disableProperty().bind(FX.isEmpty(addressField));
        cityCombo.disableProperty().bind(FX.isEmpty(provinceCombo));
        barangayCombo.disableProperty().bind(FX.isEmpty(cityCombo));
        typeCombo.disableProperty().bind(FX.isEmpty(barangayCombo));
        channelCombo.disableProperty().bind(FX.isEmpty(typeCombo).or(FX.isNot(typeCombo, CustomerType.OUTLET)));
        visitCombo.disableProperty().bind(FX.isEmpty(channelCombo));
        routingTable.disableProperty().bind(FX.isEmpty(visitCombo));
    }

    private void setEventListeners(Stage stage) {
        nameField.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB && customer.exists(nameField.getText()))
                try {
                    throw new DuplicateException(nameField.getText());
                } catch (Exception e) {
                    actOnError(stage, e);
                }

        });
        
        clearTypeComboIfChanged(provinceCombo, cityCombo, barangayCombo);

        typeCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            channelCombo.getSelectionModel().clearSelection();
            visitCombo.getSelectionModel().clearSelection();
            routingTable.getItems().clear();
        });
    }

    private void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        nameField.clear();
        nameField.requestFocus();
    }

    private void clearTypeComboIfChanged(ComboBox<?>... combos) {
        Arrays.asList(combos)
                .forEach(
                        combo -> combo
                                .getSelectionModel()
                                .selectedItemProperty()
                                .addListener(
                                        (observable, oldValue, newValue) -> typeCombo.getSelectionModel()
                                                .clearSelection()));
    }

    @Override
    protected Node[] addNodes(Stage stage, CustomerDTO dto) {

        channel = App.getContext().getBean(ChannelDTO.class);
        location = App.getContext().getBean(LocationDTO.class);
        locationTree = App.getContext().getBean(LocationTreeDTO.class);
        customer = dto;

        Label idLabel = new Label("ID No.");
        idField = new IdField(customer.getId());
        idField.setEditable(false);

        Label nameLabel = new Label("Name");
        nameField = new StringField(customer.getName());

        Label addressLabel = new Label("Address");
        addressField = new StringField(customer.getAddress());

        Label provinceLabel = new Label("Province");
        provinceCombo = FX.createComboBox(location.listProvinces(), customer.getProvince());
        provinceCombo
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> cityCombo.setItems(FXCollections
                                .observableList(locationTree.listCities(newValue))));

        Label cityLabel = new Label("City / Town");
        cityCombo = FX.createComboBox(listCities(), customer.getCity());
        cityCombo
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> barangayCombo.setItems(FXCollections
                                .observableList(locationTree.listBarangays(newValue))));

        Label barangayLabel = new Label("Barangay");
        barangayCombo = FX.createComboBox(listBarangays(), customer.getBarangay());

        Label typeLabel = new Label("Type");
        typeCombo = FX.createComboBox(CustomerType.values(), customer.getType());

        Label channelLabel = new Label("Channel");
        channelCombo = FX.createComboBox(channel.list(), customer.getChannel());

        Label visitLabel = new Label("Visit per Month");
        visitCombo = FX.createComboBox(VisitFrequency.values(), customer.getVisitFrequency());

        Label routingLabel = FX.createBigLabel("Route Assignment");
        routingTable = new RoutingTable(stage, customer).getTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameLabel, 2, 0);
        gridPane.add(nameField, 3, 0, 3, 1);
        
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1, 5, 1);
        
        gridPane.add(provinceLabel, 0, 2);
        gridPane.add(provinceCombo, 1, 2);        
        gridPane.add(cityLabel, 2, 2);
        gridPane.add(cityCombo, 3, 2);
        gridPane.add(barangayLabel, 4, 2);
        gridPane.add(barangayCombo, 5, 2);
        
        gridPane.add(typeLabel, 0, 3);
        gridPane.add(typeCombo, 1, 3);
        gridPane.add(channelLabel, 2, 3);
        gridPane.add(channelCombo, 3, 3);
        gridPane.add(visitLabel, 4, 3);
        gridPane.add(visitCombo, 5, 3);

        VBox routingBox = new VBox(gridPane, routingLabel, routingTable);

        return new Node[] { routingBox };
    }

    private List<Location> listCities() {
        return customer.getProvince() == null ? location.listCities() : locationTree.listCities(customer.getProvince());
    }

    private List<Location> listBarangays() {
        return customer.getCity() == null ? location.listBarangays() : locationTree.listBarangays(customer.getCity());
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public ComboBox<CustomerType> getTypeCombo() {
        return typeCombo;
    }

    public TableView<Routing> getRoutingTable() {
        return routingTable;
    }

    @Override
    public void save() {
        customer.setName(nameField.getText());
        customer.setAddress(addressField.getText());
        customer.setProvince(provinceCombo.getValue());
        customer.setCity(cityCombo.getValue());
        customer.setBarangay(barangayCombo.getValue());

        CustomerType type = typeCombo.getValue();
        customer.setType(type);
        if (type == CustomerType.OUTLET) {
            customer.setChannel(channelCombo.getValue());
            customer.setVisitFrequency(visitCombo.getValue());
            customer.setRouteHistory(routingTable.getItems());
        }
    }

    @Override
    public void refresh() {
        idField.setIdNo(customer.getId());
        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        provinceCombo.getSelectionModel().select(customer.getProvince());
        cityCombo.getSelectionModel().select(customer.getCity());
        barangayCombo.getSelectionModel().select(customer.getBarangay());
        typeCombo.getSelectionModel().select(customer.getType());
        channelCombo.getSelectionModel().select(customer.getChannel());
        visitCombo.getSelectionModel().select(customer.getVisitFrequency());
        routingTable.getItems().clear();
        routingTable.getItems().addAll(customer.getRouteHistory());
    }
}
