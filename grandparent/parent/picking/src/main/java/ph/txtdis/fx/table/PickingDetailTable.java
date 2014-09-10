package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.app.PickingAppImpl;
import ph.txtdis.dto.PickingDTO;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.PickingDetail;
import ph.txtdis.model.Route;

public class PickingDetailTable extends AbstractTable<PickingDetail, PickingDTO> {

    public PickingDetailTable(Stage stage, PickingDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<PickingDetail, Integer> bookingIdCol = FX.addIntegerColumn("S/O No.", "bookingId");
        TableColumn<PickingDetail, String> partnerNameCol = FX.addStringColumn("Customer Name", "partnerName", 180);
        TableColumn<PickingDetail, LocalDate> dateCol = FX.addDateColumn("Date", "bookingDate");
        table.getColumns().addAll(bookingIdCol, partnerNameCol, dateCol);
    }

    @Override
    public void createTableContextMenu(ContextMenu contextMenu) {
        LocalDate date = ((PickingAppImpl) stage).getPickerDate();
        dto.getUnpickedRoutes(date).forEach(route -> createRouteContextMenuItem(contextMenu, date, route));
        table.setContextMenu(contextMenu);
    }

    private void createRouteContextMenuItem(ContextMenu contextMenu, LocalDate date, Route route) {
        MenuItem menuItem = new MenuItem(route.toString());
        contextMenu.getItems().add(menuItem);
        menuItem.setOnAction(event -> handleMenuItemClick(contextMenu, date, route, menuItem));
    }

    private void handleMenuItemClick(ContextMenu contextMenu, LocalDate date, Route route, MenuItem menuItem) {
        contextMenu.getItems().remove(menuItem);
        dto.getUnpickedBookings(route, date).forEach(
                booking -> table.getItems().add(new PickingDetail(dto.get(), booking)));
    }

    @Override
    protected void addTableMenuToRowMenu(TableView<PickingDetail> tableView, ContextMenu rowMenu) {
    }
}
