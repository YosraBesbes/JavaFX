package ph.txtdis.fx.input;

import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class LabeledTextField<T> implements InputNode<T> {
    protected List<Node> nodes;
    protected final TextField textField;
    protected final Label label;

    public LabeledTextField(String name, TextField textField) {
        this.textField = textField;
        label = new Label(name);
        nodes = Arrays.asList(label, textField);
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public void reset() {
        textField.clear();
    }

    @Override
    public void requestFocus() {
        textField.requestFocus();
    }

    @Override
    public BooleanBinding isEmpty() {
        return textField.textProperty().isEmpty();
    }

    public TextField getTextField() {
        return textField;
    }

    protected String getText() {
        return textField.getText();
    }
}
