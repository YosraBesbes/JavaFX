package ph.txtdis.app;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ph.txtdis.dto.AuditedDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.DeleteButton;
import ph.txtdis.fx.button.NewButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.button.OpenButton;
import ph.txtdis.fx.button.SaveButton;
import ph.txtdis.fx.button.SearchByTextButton;
import ph.txtdis.fx.input.StringDisplay;
import ph.txtdis.fx.util.FontToImage;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public abstract class AbstractApp<E> extends Stage implements Apped {

    protected AuditedDTO<E> dto;
    protected Map<String, Button> buttons;
    protected String module, abbr;
    protected StringDisplay encoderField, timestampField;
    protected HBox summaryBox, userHBox;

    public AbstractApp(String module, String abbr) {
        this.module = module;
        this.abbr = abbr;
    }

    @Override
    public void start() {
        setDTO();
        initialize();
        setDisableBindings();
        setListeners();
        show();
        setFocus();
    }

    @Override
    public void save() throws InvalidException {
        dto.save();
    }

    @Override
    public void refresh() {
        setTitle(titleName());
        if (encoderField != null) {
            encoderField.setText(DIS.toString(dto.getCreatedBy()));
            timestampField.setText(Util.formatTimestamp(dto.getTimeStamp()));
        }
        setFocus();
    }

    protected abstract void setDTO();

    protected abstract void setDisableBindings();

    protected abstract Node[] addVBoxNodes();

    protected abstract String getTitleName();

    protected void setListeners() {
    }

    private void initialize() {
        setButtons();
        setData();
        setStage(placeNodes());
    }

    protected void setButtons() {
        buttons = new LinkedHashMap<>();
        buttons.put("search", new SearchByTextButton<E>(this, dto).getButton());
        buttons.put("new", new NewButton<E>(this, dto).getButton());
        buttons.put("delete", new DeleteButton<E>(this, dto).getButton());
        buttons.put("back", new BackButton<E>(this, dto).getButton());
        buttons.put("open", new OpenButton<E>(this, dto).getButton());
        buttons.put("next", new NextButton<E>(this, dto).getButton());
        buttons.put("save", new SaveButton<E>(this, dto).getButton());
    }

    private void setData() {
        if (!isNew())
            refresh();
    }

    private HBox addHeader() {
        return createHeaderBox(createTitle(), createTilePane());
    }

    private Label createTitle() {
        Label text = new Label(module);
        text.setStyle("-fx-font-size: 26pt;");
        return text;
    }

    private TilePane createTilePane() {
        TilePane tile = new TilePane();
        tile.getChildren().addAll(buttons.values());
        tile.setHgap(5);
        tile.setAlignment(Pos.TOP_RIGHT);
        return tile;
    }

    private HBox createHeaderBox(Label text, TilePane tile) {
        HBox hBox = new HBox(text, tile);
        HBox.setHgrow(tile, Priority.ALWAYS);
        hBox.setPadding(new Insets(10, 10, 0, 10));
        return hBox;
    }

    private Node[] addEncoderNodes() {
        Label encoderLabel = new Label("Created By");
        encoderField = new StringDisplay(DIS.toString(dto.getCreatedBy()));
        Label timestampLabel = new Label("On");
        timestampField = new StringDisplay(Util.formatTimestamp(dto.getTimeStamp()));
        return new Node[] { encoderLabel, encoderField, timestampLabel, timestampField };
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
    }

    protected void setUserBox() {
        userHBox = new HBox(addEncoderNodes());
        setHBoxProperties(userHBox);
    }

    protected Node[] addSummaryNodes() {
        return new Node[] {};
    };

    private HBox addSummaryBox() {
        HBox summaryBox = new HBox(addSummaryNodes());
        setHBoxProperties(summaryBox);
        return summaryBox;
    }

    protected void addFooter(VBox box) {
        setUserBox();
        box.getChildren().addAll(addSummaryBox(), userHBox);
    }

    private VBox placeNodes() {
        VBox box = new VBox(addHeader());
        box.getChildren().addAll(addVBoxNodes());
        addFooter(box);
        return box;
    }

    private Image icon() {
        return new FontToImage("icomoon", "\ue601", Color.NAVY).getImage();
    }

    protected String titleName() {
        return getTitleName() + (isNew() ? newModule() : moduleNo());
    }

    private String newModule() {
        return "[New " + abbr + "]";
    }

    private String moduleNo() {
        return "[" + abbr + " No. " + dto.getId() + "]";
    }

    private Scene createScene(VBox box) {
        Scene scene = new Scene(box, 1024, 512);
        scene.getStylesheets().addAll("/css/base.css");
        return scene;
    }

    private void setStage(VBox box) {
        getIcons().add(icon());
        setTitle(titleName());
        setScene(createScene(box));
    }

    protected boolean isNew() {
        return dto.getId() == 0;
    };

    public void setDTO(E entity) {
        dto.set(entity);
    }
}
