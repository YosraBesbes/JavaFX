package ph.txtdis.fx.input;

import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ph.txtdis.fx.util.FX;

public class LabeledComboBox<T> implements InputNode<T> {
    private final List<Node> nodes;
    private final ComboBox<T> comboBox;

    public LabeledComboBox(String name, T[] types) {
        this(name, FXCollections.observableArrayList(types));
    }

    public LabeledComboBox(String name, ObservableList<T> items) {
        Label label = new Label(name);
        label.setMinWidth(name.length() * 9);
        comboBox = FX.createComboBox(items, null);
        nodes = Arrays.asList(label, comboBox);
        selectItemIfOnlyOne();
    }

    private void selectItemIfOnlyOne() {
        comboBox.itemsProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.size() == 1) {
                comboBox.setValue(newValue.get(0));
                comboBox.focusTraversableProperty().set(false);
            }
        });
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public T getValue() {
        return comboBox.getValue();
    }

    @Override
    public void reset() {
        comboBox.setValue(null);
    }

    @Override
    public void requestFocus() {
        comboBox.requestFocus();
    }

    @Override
    public BooleanBinding isEmpty() {
        return comboBox.valueProperty().isNull();
    }

    public void setItems(List<T> list) {
        comboBox.setItems(FXCollections.observableList(list));
    }

    public void setSelection(T item) {
        comboBox.getSelectionModel().select(item);
    }
}
