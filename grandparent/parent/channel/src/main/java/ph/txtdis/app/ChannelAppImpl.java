package ph.txtdis.app;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.ChannelDTO;
import ph.txtdis.fx.table.ChannelTable;
import ph.txtdis.model.Channel;

public class ChannelAppImpl extends AbstractApp<Channel, Integer> {

    private TableView<Channel> table;
    private ChannelDTO channel;

    public ChannelAppImpl() {
        super("Channel Master", "");
    }

    @Override
    protected void setDTO() {
        dto = channel = App.getContext().getBean(ChannelDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new ChannelTable(this, channel).getTable();
        table.setItems(channel.list());
        HBox box = new HBox(table);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        return new Node[] { box };
    }

    @Override
    public void setFocus() {
        table.requestFocus();
    }

    @Override
    public void save() {
    }

    @Override
    protected void setBindings() {
    }

    @Override
    protected void addFooter(VBox box) {
    }
}
