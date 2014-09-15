package ph.txtdis.fx.table;

import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.LeaveDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Leave;
import ph.txtdis.type.LeaveType;

public class LeaveBoxedTable extends AbstractBoxedTable<Leave, EmployeeDTO> {

    public LeaveBoxedTable(Stage stage, EmployeeDTO dto) {
        super("Leaves", stage, dto);
    }

    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<Leave, LeaveType> typeCol = FX.addComboColumn("Type", "type", LeaveType.values());
        TableColumn<Leave, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
        TableColumn<Leave, Integer> dayCountCol = FX.addIntegerColumn("No. of Days", "dayCount");
        table.getColumns().addAll(typeCol, startCol, dayCountCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new LeaveDialog(stage, dto);
    }
}
