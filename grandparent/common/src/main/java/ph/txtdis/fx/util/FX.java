package ph.txtdis.fx.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.StringUtils;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.tab.ImageStreamed;
import ph.txtdis.fx.tablecell.BooleanTableCell;
import ph.txtdis.fx.tablecell.DatePickerTableCell;
import ph.txtdis.fx.tablecell.DoubleClickTableCell;
import ph.txtdis.fx.tablecell.FourPlaceFieldTableCell;
import ph.txtdis.fx.tablecell.ImageViewTableCell;
import ph.txtdis.fx.tablecell.IntegerFieldTableCell;
import ph.txtdis.fx.tablecell.MonetaryFieldTableCell;
import ph.txtdis.util.DIS;

public class FX {
    private static String style = " -fx-alignment: center-right; -fx-text-fill: ";
    private static String positive = " -fx-text-background-color";

    public static ImageView getDefaultImageView(String name) {
        return toImageView(getDefaultImageStream(name));
    }

    public static InputStream getDefaultImageStream(String name) {
        return FX.class.getResourceAsStream("/image/" + name + ".jpg");
    }

    public static ImageView toImageView(InputStream imageStream) {
        Image image = new Image(imageStream, 200, 0, true, true);
        return new ImageView(image);
    }

    public static ImageView toImageView(Byte[] imageBytes) {
        byte[] imagebytes = ArrayUtils.toPrimitive(imageBytes);
        InputStream imageStream = new ByteArrayInputStream(imagebytes);
        return toImageView(imageStream);
    }

    public static InputStream loadInputStream(Stage stage, String fileName, String defaultImageName) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load " + fileName);
            File file = fileChooser.showOpenDialog(stage);
            return file == null ? FX.getDefaultImageStream(defaultImageName) : new FileInputStream(file);
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Byte[] loadBytes(Stage stage, String fileName, String defaultImageName) {
        try {
            InputStream inputStream = FX.loadInputStream(stage, fileName, defaultImageName);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return ArrayUtils.toObject(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void loadFont(String fontName) {
        Font.loadFont(FX.class.getResource("/font/" + fontName + ".ttf").toExternalForm(), 24);
    }

    public static void loadIcomoon() {
        loadFont("icomoon");
    }

    public static void loadTxtdisIcons() {
        loadFont("txtdis");
    }

    public static Button createButton(String name) {
        Button button = new Button(name);
        button.setTooltip(new Tooltip("Use tab to traverse;\nspace to select"));
        return button;
    }

    public static Button createLargeButton(String name) {
        Button button = createButton(name);
        button.setStyle("-fx-font-size: 14pt;");
        HBox.setMargin(button, new Insets(0, 0, 0, 10));
        return button;
    }

    public static <T> ComboBox<T> createComboBox(T[] items, T item) {
        return createComboBox(Arrays.asList(items), item);
    }

    public static <T> ComboBox<T> createComboBox(List<T> items, T item) {
        ObservableList<T> obsItems = FXCollections.observableList(items);
        ComboBox<T> comboBox = new ComboBox<>(obsItems);
        comboBox.getSelectionModel().select(items.size() == 1 ? items.get(0) : item);
        comboBox.setTooltip(new Tooltip("Use tab to traverse;\nup/down arrows to scroll"));
        return comboBox;
    }

    public static DatePicker createDatePicker(LocalDate date) {
        DatePicker datePicker = new DatePicker(date);
        datePicker.setPromptText("mm/dd/yyyy");
        datePicker.setTooltip(new Tooltip("Use tab to traverse"));
        return datePicker;
    }

    public static Label createImageLabel(Stage stage, ImageStreamed dto) {
        Label imageLabel = new Label();
        imageLabel.setGraphic(toImageView(dto.getImageStream()));
        imageLabel.setTooltip(new Tooltip("Double-click to load image"));
        imageLabel.addEventFilter(MouseEvent.MOUSE_CLICKED, new ImageLoader(stage, imageLabel, dto));
        return imageLabel;
    }

    public static Label createBigLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 16pt;");
        return label;
    }

    public static <S, T> TableColumn<S, T> createColumn(String name, String field, int minWidth) {
        TableColumn<S, T> tableColumn = new TableColumn<>(name);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field));
        tableColumn.setMinWidth(minWidth);
        return tableColumn;
    }

    public static <S, T> TableColumn<S, T> addDisplayColumn(Stage stage, String name, String field, int minWidth,
            Audited<S> dto) {
        TableColumn<S, T> tableColumn = createColumn(name, field, minWidth);
        tableColumn.setCellFactory(column -> {
            return new DoubleClickTableCell<S, T>(stage, dto);
        });
        return tableColumn;
    }

    public static <S> TableColumn<S, Boolean> addBooleanColumn(String name, String field) {
        TableColumn<S, Boolean> tableColumn = createColumn(name, field, 70);
        tableColumn.setCellFactory(column -> {
            return new BooleanTableCell<S>() {
                @Override
                protected void setBoolean(S item, Boolean bool) {
                    DIS.invokeOneParameterMethod(item, "set" + StringUtils.capitalize(field), bool, boolean.class);
                }
            };
        });
        return tableColumn;
    }

    public static <S, T> TableColumn<S, T> addComboColumn(String name, String field, T[] types, int minWidth) {
        ObservableList<T> items = FXCollections.observableArrayList(types);
        return addComboColumn(name, field, items, minWidth);
    }

    public static <S, T> TableColumn<S, T> addComboColumn(String name, String field, ObservableList<T> items,
            int minWidth) {
        TableColumn<S, T> tableColumn = createColumn(name, field, minWidth);
        tableColumn.setCellFactory(ComboBoxTableCell.<S, T> forTableColumn(items));
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S, T> TableColumn<S, T> addComboColumn(String name, String field, ObservableList<T> items) {
        return addComboColumn(name, field, items, 120);
    }

    public static <S, T> TableColumn<S, T> addComboColumn(String name, String field, T[] types) {
        return addComboColumn(name, field, types, 120);
    }

    public static <S> TableColumn<S, LocalDate> addDateColumn(String name, String field) {
        TableColumn<S, LocalDate> tableColumn = createColumn(name, field, 130);
        tableColumn.setCellFactory(column -> new DatePickerTableCell<S>());
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S> TableColumn<S, Byte[]> addImageColumn(Stage stage, String name, String field) {
        TableColumn<S, Byte[]> tableColumn = createColumn("Image", "image", 210);
        tableColumn.setCellFactory(column -> new ImageViewTableCell<S>(stage));
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S> TableColumn<S, Integer> addIntegerColumn(String name, String field) {
        TableColumn<S, Integer> tableColumn = createColumn(name, field, 100);
        tableColumn.setCellFactory(column -> new IntegerFieldTableCell<S>());
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S> TableColumn<S, BigDecimal> add4PlaceColumn(String name, String field) {
        TableColumn<S, BigDecimal> tableColumn = createColumn(name, field, 100);
        tableColumn.setCellFactory(column -> new FourPlaceFieldTableCell<S>());
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S> TableColumn<S, String> addStringColumn(String name, String field, int width) {
        TableColumn<S, String> tableColumn = createColumn(name, field, width);
        tableColumn.setCellFactory(TextFieldTableCell.<S> forTableColumn());
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    public static <S> TableColumn<S, BigDecimal> addPriceColumn(String name, String field) {
        TableColumn<S, BigDecimal> tableColumn = createColumn(name, field, 120);
        tableColumn.setCellFactory(column -> new MonetaryFieldTableCell<S>());
        setOnEditCommit(field, tableColumn);
        return tableColumn;
    }

    private static <S, T> void setOnEditCommit(String field, TableColumn<S, T> tableColumn) {
        tableColumn.setOnEditCommit(event -> {
            try {
                DIS.invokeOneParameterMethod(event.getRowValue(), "set" + StringUtils.capitalize(field),
                        event.getNewValue(), event.getNewValue().getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static Tooltip tip(String tooltip) {
        Tooltip tip = new Tooltip(tooltip);
        tip.setStyle("-fx-font-size: 10pt;");
        return tip;
    }

    public static void styleInt(TextStyled styled, int number) {
        styled.setText(DIS.formatInt(number));
        styled.setStyle(style + (number < 0 ? "red" : positive));
    }

    public static void styleId(TextStyled styled, int number) {
        styled.setText(DIS.formatId(number));
        styled.setStyle(style + (number < 0 ? "red" : positive));
    }

    public static void styleDecimal(TextStyled styled, BigDecimal number) {
        styled.setText(DIS.formatDecimal(number));
        styled.setStyle(style + (DIS.isNegative(number) ? "red" : positive));
    }

    public static void styleQuantity(TextStyled styled, BigDecimal number) {
        styled.setText(DIS.formatQuantity(number));
        styled.setStyle(style + (DIS.isNegative(number) ? "red" : positive));
    }

    public static void styleMonetary(TextStyled styled, BigDecimal number) {
        styled.setText("₱" + DIS.formatDecimal(number));
        styled.setStyle(style + (DIS.isNegative(number) ? "red" : positive));
    }

    public static String moduleName(Apped app) {
        return app.getClass().getSimpleName().replace("AppImpl", "");
    }

    public static BooleanBinding isEmpty(DatePicker datePicker) {
        return datePicker.valueProperty().isNull();
    }

    public static <T> BooleanBinding isEmpty(TableView<T> table) {
        return Bindings.isEmpty(table.getItems());
    }

    public static BooleanBinding isEmpty(TextField field) {
        return field.textProperty().isEmpty();
    }

    public static <T> BooleanBinding isEmpty(ComboBox<T> combo) {
        return combo.getSelectionModel().selectedItemProperty().isNull();
    }

    public static <T> BooleanBinding is(ComboBox<T> combo, T item) {
        return combo.getSelectionModel().selectedItemProperty().isEqualTo(item);
    }

    public static <T> BooleanBinding isNot(ComboBox<T> combo, T item) {
        return combo.getSelectionModel().selectedItemProperty().isNotEqualTo(item);
    }

    public static void putIconAndTitle(Stage stage) {
        Image icon = new FontToImage("icomoon", "\ue601", Color.NAVY).getImage();
        stage.getIcons().add(icon);
    }

    public static void setMinWidth(Control control, int letterCount) {
        int width = letterCount * 14 + 24;
        control.setMinWidth(width);
        control.setPrefWidth(width);
    }
}
