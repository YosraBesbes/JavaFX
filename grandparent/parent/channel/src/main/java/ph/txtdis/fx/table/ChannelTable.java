package ph.txtdis.fx.table;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.ChannelDTO;
import ph.txtdis.fx.dialog.ChannelDialog;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.Channel;

public class ChannelTable extends AbstractInputTable<Channel, ChannelDTO> {

    public ChannelTable(Stage stage, ChannelDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<Channel, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Channel, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 120, Pos.CENTER_LEFT);
        TableColumn<Channel, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<Channel, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on",
                "timeStamp");
        table.getColumns().addAll(idCol, nameCol, createdByCol, createdOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new ChannelDialog(stage, dto);
    }

    @Override
    protected List<Channel> getAddedItems() {
        List<Channel> channels = new ArrayList<>();
        inputDialog.getAddedItems().forEach(channel -> {
            dto.set(channel);
            dto.save();
            channels.add(dto.get(dto.getId()));
        });
        return channels;
    }

    @Override
    protected ContextMenu createPerTableRowMenu(TableView<Channel> table, TableRow<Channel> row) {
        return null;
    }
}
