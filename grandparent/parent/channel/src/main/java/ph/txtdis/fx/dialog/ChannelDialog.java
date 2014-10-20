package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.ChannelDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Channel;

public class ChannelDialog extends AbstractFieldDialog<Channel, ChannelDTO> {

    private LabeledStringField channelField;
    private ChannelDTO dto;

    public ChannelDialog(Stage stage, ChannelDTO dto) {
        super("Channel", stage, dto);
        addChannelFieldListener();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(channelField);
    }

    private void createInputNodes() {
        channelField = new LabeledStringField("Name");
    }

    private void addChannelFieldListener() {
        channelField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                disallowDuplicates();
        });
    }

    private void disallowDuplicates() {
        String name = channelField.getValue();
        Channel channel = dto.get(name);
        if (channel != null)
            handleDuplicateError(channel + "'s already in database");
    }

    private void handleDuplicateError(String error) {
        new ErrorDialog(this, error);
        resetNodes(inputNodes);
        channelField.getTextField().requestFocus();
    }

    @Override
    protected Channel createEntity(ChannelDTO dto, List<InputNode<?>> inputNodes) {
        return new Channel(channelField.getValue());
    }

    @Override
    protected void addItems(ChannelDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}
