package ph.txtdis.fx.input;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ph.txtdis.app.Apped;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.fx.button.SearchByTextButton;

public class SearchedIdField<E> extends Group {
    
    private IdField idField;

    public SearchedIdField(Apped app, AuditedDTO<E> dto) {

        idField = new IdField();
        idField.setEditable(true);

        Button button = new SearchByTextButton<E>(app, dto).getButton();
        button.setStyle("-fx-font: 13pt 'txtdis';");
        button.disableProperty().bind(idField.textProperty().isNotEmpty());

        GridPane grid = new GridPane();
        grid.add(idField, 0, 0);
        grid.add(button, 1, 0);

        this.getChildren().add(grid);
    }

    public IdField getIdField() {
        return idField;
    }
}
