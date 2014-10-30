package ph.txtdis.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.dto.Audited;
import ph.txtdis.dto.Spun;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.NewButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.button.OpenButton;
import ph.txtdis.fx.button.SaveButton;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;

public abstract class AbstractIdApp<E> extends AbstractApp<E, Integer> {
    protected Audited<E> dto;
    protected UserDisplay encoderDisplay;
    protected TimestampDisplay timestampDisplay;
    protected HBox summaryBox, userHBox;

    public AbstractIdApp(String module, String abbr) {
        super(module, abbr);
    }

    @Override
    public void refresh() {
        updateCreationStamps();
        super.refresh();
    }

    protected void updateCreationStamps() {
        if (encoderDisplay != null) {
            encoderDisplay.setUser(dto.getCreatedBy());
            timestampDisplay.setTimestamp(dto.getTimeStamp());
        }
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("new", new NewButton<E>(this, dto).getButton());
        buttons.put("back", new BackButton(this, (Spun) dto).getButton());
        buttons.put("open", new OpenButton<E>(this, dto).getButton());
        buttons.put("next", new NextButton(this, (Spun) dto).getButton());
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
        Label encoderLabel = new Label("Created by");
        encoderDisplay = new UserDisplay(dto.getCreatedBy());
        Label timestampLabel = new Label("on");
        timestampDisplay = new TimestampDisplay(dto.getTimeStamp());
        return new Node[] { encoderLabel, encoderDisplay, timestampLabel, timestampDisplay };
    }

    protected void setHBoxProperties(HBox hBox) {
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
    protected String getTitleName() {
        return module + (dto.getId() == 0 ? newModule() : moduleNo());
    }

    private String newModule() {
        return "[ New " + abbr + " ]";
    }

    private String moduleNo() {
        return "[" + abbr + " No. " + dto.getId() + " ]";
    }
}
