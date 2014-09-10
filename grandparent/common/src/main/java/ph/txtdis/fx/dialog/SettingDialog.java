package ph.txtdis.fx.dialog;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ph.txtdis.model.Style;

public class SettingDialog extends AbstractInputDialog<Object> {
    private ColorPicker basePicker, backgroundPicker, accentPicker;
    private ComboBox<String> fontNameCombo;
    private Style setting;

    public SettingDialog(Stage stage, Style style) {
        super("Setting", stage, style);
        showAndWait();
    }

    @Override
    protected void populateGrid(GridPane gridPane, Object object) {
        Label baseLabel = new Label("Base Color");
        basePicker = new ColorPicker(Color.NAVY);
        Label backgroundLabel = new Label("Background Color");
        backgroundPicker = new ColorPicker(Color.TRANSPARENT);
        Label accentLabel = new Label("Accent Color");
        accentPicker = new ColorPicker(Color.BLUE);
        Label fontLabel = new Label("Font Family");
        fontNameCombo = new ComboBox<String>(FXCollections.observableList(Font.getFamilies()));
        fontNameCombo.getSelectionModel().select(Font.getDefault().getFamily());
        gridPane.add(baseLabel, 0, 0);
        gridPane.add(basePicker, 1, 0);
        gridPane.add(backgroundLabel, 0, 1);
        gridPane.add(backgroundPicker, 1, 1);
        gridPane.add(accentLabel, 0, 2);
        gridPane.add(accentPicker, 1, 2);
        gridPane.add(fontLabel, 0, 3);
        gridPane.add(fontNameCombo, 1, 3);
    }

    @Override
    protected Button[] createButtons(Object object) {
        return new Button[] { createCloseButton("OK") };
    }

    @Override
    protected void process() {
        saveEntity();
        super.process();
    }

    private void saveEntity() {
        setting = (Style) object;
        setting.setBase(colorToRGBA(basePicker.getValue()));
        setting.setBackground(colorToRGBA(backgroundPicker.getValue()));
        setting.setAccent(colorToRGBA(accentPicker.getValue()));
        setting.setFont(fontNameCombo.getValue());
    }

    private String colorToRGBA(Color color) {
        return String.format("rgba(%d, %d, %d, %f)", (int) Math.round(color.getRed() * 255),
                (int) Math.round(color.getGreen() * 255), (int) Math.round(color.getBlue() * 255), color.getOpacity());
    }

    public Style getSetting() {
        return setting;
    }
}