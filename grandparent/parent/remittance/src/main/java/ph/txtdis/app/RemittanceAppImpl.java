package ph.txtdis.app;

import java.sql.Date;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.exception.TxtdisException;
import ph.txtdis.exception.NotFoundException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.MonetaryField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.RemittanceDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.type.RemittanceType;
import ph.txtdis.util.Util;

public class RemittanceAppImpl extends AbstractIdApp<Remittance> implements Searched {

    private RemittanceDTO remittance;
    private CustomerDTO customer;

    private List<RemittanceDetail> details;

    private ComboBox<RemittanceType> typeCombo;
    private DatePicker datePicker;
    private HBox partnerBox;
    private IdField idField, partnerIdField;
    private MonetaryField amountField;
    private StringField partnerNameField, partnerAddressField, referenceField, remarkField;
    private TableView<RemittanceDetail> detailTable;

    private RemittanceDetailTable remittanceTable;

    public RemittanceAppImpl() {
        super("Remittance", "");
    }

    @Override
    protected void setDTO() {
        dto = remittance = App.getContext().getBean(RemittanceDTO.class);
        customer = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void setFocus() {
        datePicker.requestFocus();
    }

    @Override
    protected Node[] addVBoxNodes() {

        remittanceTable = new RemittanceDetailTable(this, remittance);
        detailTable = remittanceTable.getTable();

        Label idLabel = new Label("ID No.");
        idField = new IdField(remittance.getId());
        idField.setEditable(false);

        Label dateLabel = new Label("Date");
        datePicker = new DatePicker(remittance.getOrderDate());

        Label partnerLabel = new Label("Partner ID No.");
        partnerIdField = new IdField(remittance.getPartnerId());
        partnerNameField = new StringField(remittance.getPartnerName());
        partnerBox = new HBox(partnerLabel, partnerIdField, partnerNameField);

        Label partnerAddressLabel = new Label("Address");
        partnerAddressField = new StringField(remittance.getPartnerAddress());

        Label typeLabel = new Label("Type");
        typeCombo = FX.createComboBox(RemittanceType.values(), remittance.getType());

        Label referenceLabel = new Label("Reference ID");
        referenceField = new StringField(remittance.getReference());

        Label amountLabel = new Label("Amount");
        amountField = new MonetaryField(remittance.getAmount());

        Label remarkLabel = new Label("Remarks");
        remarkField = new StringField(remittance.getRemarks());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(dateLabel, 2, 0);
        gridPane.add(datePicker, 3, 0);
        gridPane.add(partnerLabel, 4, 0);
        gridPane.add(partnerBox, 5, 0);

        gridPane.add(partnerAddressLabel, 0, 1);
        gridPane.add(partnerAddressField, 1, 1, 5, 1);

        gridPane.add(typeLabel, 0, 2);
        gridPane.add(typeCombo, 1, 2);
        gridPane.add(referenceLabel, 2, 2);
        gridPane.add(referenceField, 3, 2);
        gridPane.add(amountLabel, 4, 2);
        gridPane.add(amountField, 5, 2);

        gridPane.add(remarkLabel, 0, 3);
        gridPane.add(remarkField, 1, 3, 5, 3);

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

        partnerIdField.disableProperty().bind(FX.isEmpty(datePicker));

        partnerNameField.setEditable(false);
        partnerNameField.setFocusTraversable(false);

        partnerAddressField.setEditable(false);
        partnerAddressField.setFocusTraversable(false);

        remarkField.disableProperty().bind(FX.isEmpty(partnerNameField));
        detailTable.disableProperty().bind(FX.isEmpty(partnerNameField));
    }

    @Override
    protected void setListeners() {
        partnerIdField.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.TAB)
                validatePartnerId(partnerIdField.getIdNo());
        });
    }

    private void validatePartnerId(int id) {
        if (customer.exists(id))
            actWhenFound(id);
        else
            throwNotFoundException(id);
    }

    private void actWhenFound(int id) {
        customer.setById(id);
        partnerNameField.setText(customer.getName());
        partnerAddressField.setText(customer.getSpecific());
    }

    private void throwNotFoundException(int id) {
        try {
            throw new NotFoundException("Partner ID No. " + id);
        } catch (Exception e) {
            actOnError(this, e);
        }
    }

    private void actOnError(Stage stage, Exception e) {
        new ErrorDialog(stage, e.getMessage());
        partnerIdField.clear();
        partnerNameField.setText("");
        partnerAddressField.setText("");
        typeCombo.getSelectionModel().clearSelection();
        referenceField.clear();
        amountField.clear();
        detailTable.getItems().clear();
    }

    @Override
    public String setSearchedCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void listFoundEntities() {
        // TODO Auto-generated method stub

    }

    @Override
    public void save() throws TxtdisException {
        remittance.setPartner(customer.get(partnerIdField.getIdNo()));
        remittance.setOrderDate(datePicker.getValue());
        remittance.setType(typeCombo.getValue());
        remittance.setReference(referenceField.getText());
        remittance.setAmount(amountField.getValue());
        remittance.setDetails(detailTable.getItems());
        remittance.save();
    }

    @Override
    public void refresh() {
        populateInputs();
        populatePartnerRelatedInputs();
        super.refresh();
    }

    private void populateInputs() {
        idField.setIdNo(remittance.getId());
        datePicker.setValue(remittance.getOrderDate());
        amountField.setValue(remittance.getAmount());
        remarkField.setText(remittance.getRemarks());
        details = remittance.getDetails();
    }

    private void populatePartnerRelatedInputs() {
        populatePartnerFields();
        iterateDetails();
        updateDetails();
    }

    private void populatePartnerFields() {
        partnerIdField.setIdNo(remittance.getPartnerId());
        partnerNameField.setText(remittance.getPartnerName());
        partnerAddressField.setText(remittance.getPartnerAddress());
    }

    private void iterateDetails() {
        for (RemittanceDetail detail : details)
            setValues(detail);
    }

    private void setValues(RemittanceDetail detail) {
        // TODO
    }

    private void updateDetails() {
        detailTable.getItems().clear();
        detailTable.getItems().addAll(details);
    }

    public Date getPickerDate() {
        return datePicker == null ? null : Util.localToSqlDate(datePicker.getValue());
    }
}
