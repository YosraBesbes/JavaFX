package ph.txtdis.fx.input;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import ph.txtdis.fx.util.FX;

public class LabeledDatePicker implements InputNode<LocalDate> {
    private final List<Node> nodes;
    private final DatePicker datePicker;

    public LabeledDatePicker(String name) {
        Label label = new Label(name);
        label.setMinWidth(name.length() * 9);
        datePicker = FX.createDatePicker(null);
        nodes = Arrays.asList(label, datePicker);
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public LocalDate getValue() {
        return datePicker.getValue();
    }

    @Override
    public void reset() {
        datePicker.setValue(null);
    }

    @Override
    public void requestFocus() {
        datePicker.requestFocus();
    }

    @Override
    public BooleanBinding isEmpty() {
        return datePicker.valueProperty().isNull();
    }
}
