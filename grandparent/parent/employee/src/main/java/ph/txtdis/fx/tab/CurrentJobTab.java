package ph.txtdis.fx.tab;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.table.AssignmentBoxedTable;
import ph.txtdis.fx.table.CompensationBoxedTable;

public class CurrentJobTab extends AbstractTab<EmployeeDTO> {
    private AssignmentBoxedTable assignmentBoxedTable;
    private CompensationBoxedTable compensationBoxedTable;

    public CurrentJobTab(Stage stage, EmployeeDTO dto) {
        super("Current Job", "job", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, EmployeeDTO dto) {
        assignmentBoxedTable = new AssignmentBoxedTable(stage, dto);
        VBox assignmentBox = assignmentBoxedTable.getBox();

        compensationBoxedTable = new CompensationBoxedTable(stage, dto);
        VBox compensationBox = compensationBoxedTable.getBox();

        return new Node[] { assignmentBox, compensationBox };
    }

    @Override
    public void save() {
        dto.setAssignments(assignmentBoxedTable.getTable().getItems());
        dto.setDailyRates(compensationBoxedTable.getTable().getItems());
    }

    @Override
    public void refresh() {
        assignmentBoxedTable.getTable().setItems(dto.getAssignments());
        compensationBoxedTable.getTable().setItems(dto.getDailyRates());
    }
}
