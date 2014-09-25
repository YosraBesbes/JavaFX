package ph.txtdis.fx.tab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.exception.DuplicateException;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.input.IdField;
import ph.txtdis.fx.input.IntegerField;
import ph.txtdis.fx.input.StringField;
import ph.txtdis.fx.table.BomTable;
import ph.txtdis.fx.table.QtyPerUomTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Bom;
import ph.txtdis.model.Item;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.type.ItemType;

public class ItemTab extends AbstractTab<Item, ItemDTO> {

    private CheckBox notDiscountedCheckbox;
    private ComboBox<ItemType> typeCombo;
    private ComboBox<ItemFamily> familyCombo;
    private IdField idField;
    private IntegerField vendorIdField;
    private StringField nameField, descriptionField;
    private TableView<Bom> bomTable;
    private TableView<QtyPerUom> qtyPerUomTable;

    public ItemTab(Stage stage, ItemDTO dto) {
        super("Basic Information", stage, dto);
        setDisableBindings();
        setEventListeners(stage);
    }

    @Override
    protected Node[] addNodes(Stage stage, ItemDTO dto) {

        ItemFamilyDTO family = App.getContext().getBean(ItemFamilyDTO.class);

        Label idLabel = new Label("Item No.");
        idField = new IdField(dto.getId());
        idField.setEditable(false);

        Label nameLabel = new Label("Name");
        nameField = new StringField(dto.getName(), 180);

        Label descriptionLabel = new Label("Description");
        descriptionField = new StringField(dto.getDescription());

        Label typeLabel = new Label("Type");
        typeCombo = FX.createComboBox(ItemType.values(), dto.getType());

        Label familyLabel = new Label("Family");
        familyCombo = FX.createComboBox(family.list(), dto.getFamily());

        Label vendorIdLabel = new Label("Vendor ID");
        vendorIdField = new IntegerField(dto.getVendorId());

        notDiscountedCheckbox = new CheckBox("Customer Discount NOT Applied");
        notDiscountedCheckbox.setSelected(dto.isNotDiscounted());

        Label qtyPerUomLabel = FX.createBigLabel("Quantity per UOM Relative to 'PC'");
        qtyPerUomTable = new QtyPerUomTable(stage, dto).getTable();

        Label bomLabel = FX.createBigLabel("Bill of Materials");
        bomTable = new BomTable(stage, dto).getTable();

        VBox bomBox = new VBox(bomLabel, bomTable);
        VBox qtyPerUomBox = new VBox(qtyPerUomLabel, qtyPerUomTable);
        HBox tableBox = new HBox(qtyPerUomBox, bomBox);
        tableBox.setSpacing(20);
        tableBox.setAlignment(Pos.TOP_CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameLabel, 2, 0);
        gridPane.add(nameField, 3, 0);
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionField, 1, 1, 3, 1);
        gridPane.add(typeLabel, 0, 2);
        gridPane.add(typeCombo, 1, 2);
        gridPane.add(familyLabel, 2, 2);
        gridPane.add(familyCombo, 3, 2);
        gridPane.add(vendorIdLabel, 0, 3);
        gridPane.add(vendorIdField, 1, 3);
        gridPane.add(notDiscountedCheckbox, 2, 3, 2, 1);

        HBox gridBox = new HBox(gridPane);
        gridBox.setAlignment(Pos.TOP_CENTER);

        VBox vBox = new VBox(gridBox, tableBox);
        return new Node[] { vBox };
    }

    private void setDisableBindings() {
        descriptionField.disableProperty().bind(FX.isEmpty(nameField));
        typeCombo.disableProperty().bind(FX.isEmpty(descriptionField));
        familyCombo.disableProperty().bind(FX.isEmpty(typeCombo).or(FX.is(typeCombo, ItemType.MONETARY)));
        vendorIdField.disableProperty().bind(FX.isEmpty(familyCombo).or(FX.isNot(typeCombo, ItemType.PURCHASED)));
        notDiscountedCheckbox.disableProperty().bind(FX.isEmpty(familyCombo).or(FX.is(typeCombo, ItemType.FREE)));
        qtyPerUomTable.disableProperty().bind(
                FX.isEmpty(familyCombo).or(FX.is(typeCombo, ItemType.PURCHASED).and(FX.isEmpty(vendorIdField))));
        bomTable.disableProperty()
                .bind(FX.isEmpty(qtyPerUomTable).or(FX.is(typeCombo, ItemType.PURCHASED))
                        .or(FX.is(typeCombo, ItemType.FREE)));
    }

    private void setEventListeners(Stage stage) {
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && dto.exists(nameField.getText()))
                try {
                    throw new DuplicateException(nameField.getText());
                } catch (Exception e) {
                    new ErrorDialog(stage, e.getMessage());
                    nameField.clear();
                    nameField.requestFocus();
                }
        });
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public ComboBox<ItemType> getTypeCombo() {
        return typeCombo;
    }

    public ComboBox<ItemFamily> getFamilyCombo() {
        return familyCombo;
    }

    public TableView<QtyPerUom> getQtyPerUomTable() {
        return qtyPerUomTable;
    }

    public TableView<Bom> getBomTable() {
        return bomTable;
    }

    @Override
    public void save() {
        dto.setName(nameField.getText());
        dto.setDescription(descriptionField.getText());
        dto.setNotDiscounted(notDiscountedCheckbox.isSelected());

        ItemType type = typeCombo.getValue();
        dto.setType(type);

        if (type != ItemType.MONETARY) {
            dto.setFamily(familyCombo.getValue());
            dto.setQtyPerUom(qtyPerUomTable.getItems());
        }

        if (type == ItemType.PURCHASED)
            dto.setVendorId(vendorIdField.getLong());

        if (type == ItemType.BUNDLED || type == ItemType.REPACKED || type == ItemType.MADE)
            dto.setBoms(bomTable.getItems());
    }

    @Override
    public void refresh() {
        idField.setIdNo(dto.getId());
        nameField.setText(dto.getName());
        descriptionField.setText(dto.getDescription());
        typeCombo.setValue(dto.getType());
        familyCombo.setValue(dto.getFamily());
        vendorIdField.setLong(dto.getVendorId());
        notDiscountedCheckbox.setSelected(dto.isNotDiscounted());
        qtyPerUomTable.getItems().clear();
        qtyPerUomTable.getItems().addAll(dto.getQtyPerUom());
        bomTable.getItems().clear();
        bomTable.getItems().addAll(dto.getBoms());
    }
}
