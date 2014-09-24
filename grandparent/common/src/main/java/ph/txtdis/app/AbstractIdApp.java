package ph.txtdis.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.dto.Audited;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.NewButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.button.OpenButton;
import ph.txtdis.fx.button.SaveButton;
import ph.txtdis.fx.input.StringDisplay;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public abstract class AbstractIdApp<E> extends AbstractApp<E, Integer> {
    protected Audited<E> dto;
    protected StringDisplay encoderField, timestampField;
    protected HBox summaryBox, userHBox;

    public AbstractIdApp(String module, String abbr) {
        super(module, abbr);
    }

    @Override
    public void refresh() {
        setEncoderData();
        super.refresh();
    }

    private void setEncoderData() {
        if (encoderField != null) {
            encoderField.setText(DIS.toString(dto.getCreatedBy()));
            timestampField.setText(Util.formatZonedDateTime(dto.getTimeStamp()));
        }
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("new", new NewButton<E>(this, dto).getButton());
        buttons.put("back", new BackButton<E, Integer>(this, dto).getButton());
        buttons.put("open", new OpenButton<E>(this, dto).getButton());
        buttons.put("next", new NextButton<E, Integer>(this, dto).getButton());
        buttons.put("save", new SaveButton<E, Integer>(this, dto).getButton());
    }

    @Override
    protected void addFooter(VBox box) {
        setUserBox();
        box.getChildren().addAll(addSummaryBox(), userHBox);
    }

    protected void setUserBox() {
        userHBox = new HBox(addEncoderNodes());
        setHBoxProperties(userHBox);
    }

    private Node[] addEncoderNodes() {
        Label encoderLabel = new Label("Created By");
        encoderField = new StringDisplay(DIS.toString(dto.getCreatedBy()));
        Label timestampLabel = new Label("On");
        timestampField = new StringDisplay(Util.formatZonedDateTime(dto.getTimeStamp()));
        return new Node[] { encoderLabel, encoderField, timestampLabel, timestampField };
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setAlignment(Pos.CENTER);
    }

    private HBox addSummaryBox() {
        HBox summaryBox = new HBox(addSummaryNodes());
        setHBoxProperties(summaryBox);
        return summaryBox;
    }

    protected Node[] addSummaryNodes() {
        return new Node[] {};
    };

    @Override
    protected String titleName() {
        return module + (dto.getId() == 0 ? newModule() : moduleNo());
    }

    private String newModule() {
        return "[New " + abbr + "]";
    }

    private String moduleNo() {
        return "[" + abbr + " No. " + dto.getId() + "]";
    }
}
