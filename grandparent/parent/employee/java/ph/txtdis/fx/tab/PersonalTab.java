package ph.txtdis.fx.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.AddressTable;
import ph.txtdis.fx.table.ContactInfoTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Employee;
import ph.txtdis.model.EmployeeAddress;
import ph.txtdis.model.EmployeeContactInfo;
import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.FamilyType;

public class PersonalTab extends AbstractTab<Employee, EmployeeDTO> {
	private DatePicker birthdatePicker;
	private ComboBox<CivilStatus> civilStatusCombo;
	private ComboBox<FamilyType> emergencyRelationCombo;
	private IdField idField;
	private Label photoLabel;
	private TableView<EmployeeAddress> addressTable;
	private TableView<EmployeeContactInfo> contactInfoTable;
	private StringField surnameField, nameField, middleInitialField,
			birthplaceField, emergencyContactField, emergencyPhoneField;
	
	public PersonalTab(Stage stage, EmployeeDTO dto) {
		super("Personal", stage, dto);
	}

	@Override
	protected Node[] addNodes(Stage stage, EmployeeDTO dto) {

		photoLabel = FX.createImageLabel(stage, dto);
		photoLabel.setPadding(new Insets(0, 20, 0, 0));
		
		Label idLabel = new Label("ID No.");
		VBox idBox = new VBox(idLabel);
		idBox.setAlignment(Pos.CENTER_RIGHT);
		idField = new IdField(dto.getId());
		idField.setEditable(false);

		Label surnameLabel = new Label("Surname");
		surnameField = new StringField(dto.getSurname(), 12);
		surnameField.setPromptText("LAST NAME");

		Label nameLabel = new Label("Name");
		nameField = new StringField(dto.getName(), 18);
		nameField.setPromptText("GIVEN NAME/S");

		Label middleInitialLabel = new Label("M.I.");
		middleInitialField = new StringField(dto.getMiddleInitial(), 1);

		Label addressLabel = new Label("Address");
		addressTable = new AddressTable(stage, dto).getTable();

		Label birthdateLabel = new Label("Birthdate");
		birthdatePicker = FX.createDatePicker(dto.getBirthdate());

		Label birthPlaceLabel = new Label("Birthplace");
		birthplaceField = new StringField(dto.getBirthplace());
		birthplaceField.setPromptText("CITY/TOWN, PROVINCE");

		Label civilStatusLabel = new Label("Civil Status");
		civilStatusCombo = FX.createComboBox(CivilStatus.values(),
				dto.getCivilStatus());

		Label emergencyLabel = new Label("Emergency Contact");
		emergencyLabel.setStyle("-fx-font-size: 16pt;");

		Label emergencyContactLabel = new Label("Name");
		emergencyContactField = new StringField(dto.getEmergencyContact());

		Label emergencyRelationLabel = new Label("Relation");
		emergencyRelationCombo = FX.createComboBox(FamilyType.values(),
				dto.getEmergencyRelation());

		Label emergencyPhoneLabel = new Label("Phone");
		emergencyPhoneField = new StringField(dto.getEmergencyPhone());

		Label contactInfoLabel = new Label("Contact Information Details");
		contactInfoLabel.setStyle("-fx-font-size: 16pt;");
		contactInfoTable = new ContactInfoTable(stage, dto).getTable();

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(photoLabel, 0, 0, 1, 10);
		gridPane.add(idBox, 5, 0);
		gridPane.add(idField, 6, 0, 2, 1);
		gridPane.add(surnameLabel, 1, 1);
		gridPane.add(surnameField, 2, 1);
		gridPane.add(nameLabel, 3, 1);
		gridPane.add(nameField, 4, 1, 2, 1);
		gridPane.add(middleInitialLabel, 6, 1);
		gridPane.add(middleInitialField, 7, 1);
		gridPane.add(addressLabel, 1, 2, 1, 2);
		gridPane.add(addressTable, 2, 2, 6, 2);
		gridPane.add(birthdateLabel, 1, 4);
		gridPane.add(birthdatePicker, 2, 4);
		gridPane.add(birthPlaceLabel, 3, 4, 2, 1);
		gridPane.add(birthplaceField, 5, 4, 3, 1);
		gridPane.add(civilStatusLabel, 1, 5);
		gridPane.add(civilStatusCombo, 2, 5);
		gridPane.add(emergencyLabel, 1, 6, 2, 1);
		gridPane.add(emergencyContactLabel, 1, 7);
		gridPane.add(emergencyContactField, 2, 7);
		gridPane.add(emergencyRelationLabel, 1, 8);
		gridPane.add(emergencyRelationCombo, 2, 8);
		gridPane.add(emergencyPhoneLabel, 1, 9);
		gridPane.add(emergencyPhoneField, 2, 9);
		gridPane.add(contactInfoLabel, 3, 5, 5, 1);
		gridPane.add(contactInfoTable, 3, 6, 5, 4);

		return new GridPane[] { gridPane };
	}

	public TextField getIdField() {
		return idField;
	}

	public TextField getSurnameField() {
		return surnameField;
	}

	public TextField getNameField() {
		return nameField;
	}

	@Override
	public void save() {
		dto.setSurname(surnameField.getText());
		dto.setName(nameField.getText());
		dto.setMiddleInitial(middleInitialField.getText());
		dto.setAddresses(addressTable.getItems());
		dto.setBirthdate(birthdatePicker.getValue());
		dto.setBirthplace(birthplaceField.getText());
		dto.setCivilStatus(civilStatusCombo.getValue());
		dto.setEmergencyContact(emergencyContactField.getText());
		dto.setEmergencyRelation(emergencyRelationCombo.getValue());
		dto.setEmergencyPhone(emergencyPhoneField.getText());
		dto.setContactInfo(contactInfoTable.getItems());
	}

	@Override
	public void refresh() {
		idField.setIdNo(dto.getId());
		photoLabel.setGraphic(FX.toImageView(dto.getImageStream()));
		surnameField.setText(dto.getSurname());
		nameField.setText(dto.getName());
		middleInitialField.setText(dto.getMiddleInitial());
		addressTable.setItems(dto.getAddresses());
		birthdatePicker.setValue(dto.getBirthdate());
		birthplaceField.setText(dto.getBirthplace());
		civilStatusCombo.getSelectionModel().select(dto.getCivilStatus());
		emergencyContactField.setText(dto.getEmergencyContact());
		emergencyRelationCombo.getSelectionModel().select(dto.getEmergencyRelation());
		emergencyPhoneField.setText(dto.getEmergencyPhone());
		contactInfoTable.setItems(dto.getContactInfo());
	}
}
