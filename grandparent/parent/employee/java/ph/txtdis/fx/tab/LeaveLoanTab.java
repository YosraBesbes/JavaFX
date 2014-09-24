package ph.txtdis.fx.tab;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.table.LeaveBoxedTable;
import ph.txtdis.fx.table.LoanBoxedTable;
import ph.txtdis.model.Leave;

public class LeaveLoanTab extends AbstractTab<Leave, EmployeeDTO> {

	private LeaveBoxedTable leaveTable;
	private LoanBoxedTable loanTable;

	public LeaveLoanTab(Stage stage, EmployeeDTO dto) {
		super("Leave/Loan", stage, dto);
	}

	@Override
	protected Node[] addNodes(Stage stage, EmployeeDTO dto) {
		leaveTable = new LeaveBoxedTable(stage, dto);
		VBox leaveBox = leaveTable.getBox();

		loanTable = new LoanBoxedTable(stage, dto);
		VBox loanBox = loanTable.getBox();

		return new Node[] { leaveBox, loanBox };
	}

	@Override
	public void save() {
		dto.setLeaves(leaveTable.getTable().getItems());
		dto.setLoans(loanTable.getTable().getItems());
	}

	@Override
	public void refresh() {
		leaveTable.getTable().setItems(dto.getLeaves());
		loanTable.getTable().setItems(dto.getLoans());
	}
}
