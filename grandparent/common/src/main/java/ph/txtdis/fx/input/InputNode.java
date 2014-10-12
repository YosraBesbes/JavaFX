package ph.txtdis.fx.input;

import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;

public interface InputNode<T> {
    
    List<Node> getNodes();

    T getValue();

    void reset();

    void requestFocus();

    BooleanBinding isEmpty();
}