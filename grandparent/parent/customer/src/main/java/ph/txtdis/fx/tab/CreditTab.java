package ph.txtdis.fx.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.CreditTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.CreditDetail;

public class CreditTab extends AbstractTab<CustomerDTO> {

    private StringField contactNameField, contactSurnameField, titleField, mobileField;
    private TableView<CreditDetail> creditTable;

    public CreditTab(Stage stage, CustomerDTO dto) {
        super("Credit Details", "credit", stage, dto);
        setDisableBindings();
    }

    private void setDisableBindings() {
        contactSurnameField.disableProperty().bind(FX.isEmpty(contactNameField));
        titleField.disableProperty().bind(FX.isEmpty(contactSurnameField));
        mobileField.disableProperty().bind(FX.isEmpty(titleField));
        creditTable.disableProperty().bind(FX.isEmpty(mobileField));
    }

    @Override
    protected Node[] addNodes(Stage stage, CustomerDTO dto) {

        Label contactLabel = FX.createBigLabel("Credit Contact");

        Label givenNameLabel = new Label("Given Name");
        contactNameField = new StringField(dto.getCreditContactName(), 160);

        Label surnameLabel = new Label("Surname");
        contactSurnameField = new StringField(dto.getCreditContactSurname(), 160);

        Label titleLabel = new Label("Job Title");
        titleField = new StringField(dto.getContactTitle());

        Label mobileLabel = new Label("Mobile No.");
        // mobileField = new StringField(dto.getMobile());

        Label creditLabel = FX.createBigLabel("Approved Credit History");
        creditTable = new CreditTable(stage, dto).getTable();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(contactLabel, 0, 0, 3, 1);
        gridPane.add(givenNameLabel, 0, 1);
        gridPane.add(contactNameField, 1, 1);
        gridPane.add(surnameLabel, 2, 1);
        gridPane.add(contactSurnameField, 3, 1);
        gridPane.add(titleLabel, 0, 2);
        gridPane.add(titleField, 1, 2);
        gridPane.add(mobileLabel, 2, 2);
        gridPane.add(mobileField, 3, 2);

        VBox routingBox = new VBox(gridPane, creditLabel, creditTable);

        return new Node[] { routingBox };
    }

    public TextField getContactNameField() {
        return contactNameField;
    }

    @Override
    public void save() {
        dto.setCreditContactName(contactNameField.getText());
        dto.setCreditContactSurname(contactSurnameField.getText());
        dto.setContactTitle(titleField.getText());
        // dto.setMobile(mobileField.getText());
        dto.setCreditDetails(creditTable.getItems());
    }

    @Override
    public void refresh() {
        contactNameField.setText(dto.getCreditContactName());
        contactSurnameField.setText(dto.getCreditContactSurname());
        titleField.setText(dto.getContactTitle());
        // mobileField.setText(dto.getMobile());
        creditTable.getItems().clear();
        creditTable.getItems().addAll(dto.getCreditDetails());
    }
}
