package ph.txtdis.fx.table;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import ph.txtdis.dto.TruckDTO;
import ph.txtdis.fx.dialog.TruckDialog;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.Truck;
import ph.txtdis.util.Login;

public class TruckTable extends AbstractInputTable<Truck, TruckDTO> {

    public TruckTable(Stage stage, TruckDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<Truck, Integer> idCol = new IdDisplayColumn<>(stage, "ID No.", "id");
        TableColumn<Truck, String> nameCol = new TextDisplayColumn<>(stage, "Name", "name", 120, Pos.CENTER_LEFT);
        TableColumn<Truck, String> createdByCol = new TextDisplayColumn<>(stage, "Created by", "createdBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<Truck, ZonedDateTime> createdOnCol = new TimestampDisplayColumn<>(stage, "Created on", "timeStamp");
        TableColumn<Truck, String> disabledByCol = new TextDisplayColumn<>(stage, "Disabled by", "disabledBy", 120,
                Pos.CENTER_LEFT);
        TableColumn<Truck, ZonedDateTime> disabledOnCol = new TimestampDisplayColumn<>(stage, "Disabled on",
                "disabledOn");
        table.getColumns().addAll(idCol, nameCol, createdByCol, createdOnCol, disabledByCol, disabledOnCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new TruckDialog(stage, dto);
    }

    @Override
    protected List<Truck> getAddedItems() {
        List<Truck> trucks = new ArrayList<>();
        inputDialog.getAddedItems().forEach(truck -> {
            dto.set(truck);
            dto.save();
            trucks.add(dto.get(dto.getId()));
        });
        return trucks;
    }

    @Override
    protected MenuItem createRowMenuItem() {
        return new MenuItem("Disable");
    }

    @Override
    protected void handleRowMenuItemSelection(TableRow<Truck> row) {
        Truck truck = row.getItem();
        if (truck.getDisabledBy() == null)
            disableSelectedTruck(truck);
    }

    protected void disableSelectedTruck(Truck truck) {
        setDisableStamps(truck);
        saveDisableStamps(truck);
        resetTable();
    }

    protected void setDisableStamps(Truck truck) {
        truck.setDisabledBy(Login.user());
        truck.setDisabledOn(ZonedDateTime.now());
    }

    protected void saveDisableStamps(Truck truck) {
        dto.save(truck);
        dto.setById(dto.getId());
    }

    protected void resetTable() {
        table.getItems().clear();
        table.getItems().addAll(dto.list());
    }
}
