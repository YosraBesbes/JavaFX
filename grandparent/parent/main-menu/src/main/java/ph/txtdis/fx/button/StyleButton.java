package ph.txtdis.fx.button;

import javafx.stage.Stage;
import ph.txtdis.fx.dialog.SettingDialog;
import ph.txtdis.fx.util.StyleSheet;
import ph.txtdis.model.Style;
import ph.txtdis.service.StyleService;

public class StyleButton extends FontButton<Object> {

    public <C> StyleButton(Stage stage, StyleSheet styleSheet, StyleService styleService) {
        super("\ue825", "Style Setting", 44);
        button.setOnAction(event -> {
            styleSheet.update(getStyle(stage, styleSheet));
            styleService.save(getStyle(styleSheet));
        });
    }

    private Style getStyle(StyleSheet styleSheet) {
        return styleSheet.getStyle();
    }

    private Style getStyle(Stage stage, StyleSheet styleSheet) {
        return new SettingDialog(stage, getStyle(styleSheet)).getSetting();
    }
}
