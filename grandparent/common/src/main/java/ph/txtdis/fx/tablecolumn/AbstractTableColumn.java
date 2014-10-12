package ph.txtdis.fx.tablecolumn;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class AbstractTableColumn<S, E> extends TableColumn<S, E> {

    public AbstractTableColumn(Stage stage, String text, String field, int minWidth, Pos pos) {
        super(text);
        setCellValueFactory(new PropertyValueFactory<>(field));
        makeHeaderWrappable();
        setCellFactory(getCallback(stage, pos));
        setId(field);
        setMinWidth(minWidth);
        setPrefWidth(minWidth);
    }

    private void makeHeaderWrappable() {
        Label label = new Label(getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        setGraphic(stack);
    }

    protected abstract Callback<TableColumn<S, E>, TableCell<S, E>> getCallback(Stage stage, Pos pos);
}