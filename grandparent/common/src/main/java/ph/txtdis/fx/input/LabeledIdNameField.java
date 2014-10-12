package ph.txtdis.fx.input;

import java.util.Arrays;

import ph.txtdis.fx.display.StringDisplay;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.layout.HBox;

public class LabeledIdNameField extends LabeledIdField {

    private StringDisplay nameDisplay;

    public LabeledIdNameField(String name, int width) {
        super(name);
        setNameField(width);
        nodes = Arrays.asList(label, new HBox(textField, nameDisplay));
    }

    private void setNameField(int width) {
        nameDisplay = new StringDisplay(width);
    }

    @Override
    public void reset() {
        textField.clear();
        nameDisplay.setText("");
    }

    @Override
    public BooleanBinding isEmpty() {
        return super.isEmpty().or(nameDisplay.textProperty().isEmpty());
    }

    public IdField getIdField() {
        return (IdField) textField;
    }

    public StringDisplay getNameField() {
        return nameDisplay;
    }
}
