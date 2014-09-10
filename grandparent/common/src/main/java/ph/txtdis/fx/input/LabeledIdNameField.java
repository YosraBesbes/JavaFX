package ph.txtdis.fx.input;

import java.util.Arrays;

import javafx.scene.layout.HBox;

public class LabeledIdNameField extends LabeledIdField {
        
    private StringField nameField; 
    
    public LabeledIdNameField(String name, int width) {
        super(name);
        setNameField(width);
        nodes = Arrays.asList(label, new HBox(textField, nameField));
    }

    private void setNameField(int width) {
        nameField = new StringField(width);
        nameField.setEditable(false);
        nameField.focusTraversableProperty().set(false);
    }

    @Override
    public void reset() {
        textField.clear();
        nameField.setText("");
    }

    public IdField getIdField() {
        return (IdField) textField;
    }

    public StringField getNameField() {
        return nameField;
    }
}
