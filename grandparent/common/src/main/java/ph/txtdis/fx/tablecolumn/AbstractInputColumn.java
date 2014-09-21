package ph.txtdis.fx.tablecolumn;

import javafx.stage.Stage;

import org.springframework.util.StringUtils;

import ph.txtdis.util.DIS;

public abstract class AbstractInputColumn<S, T> extends AbstractTableColumn<S, T> {

    public AbstractInputColumn(Stage stage, String text, String field, int minWidth) {
        super(stage, text, field, minWidth);
        setOnEditCommit(event -> {
            try {
                DIS.invokeOneParameterMethod(event.getRowValue(), "set" + StringUtils.capitalize(field),
                        event.getNewValue(), event.getNewValue().getClass());
                setVisible(false);
                setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
