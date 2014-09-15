package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.sql.Date;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.CompensationDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Compensation;

public class CompensationBoxedTable extends
		AbstractBoxedTable<Compensation, EmployeeDTO> {

	public CompensationBoxedTable(Stage stage, EmployeeDTO dto) {
		super("Compensation", stage, dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void addTableColumns() {

		TableColumn<Compensation, Date> startCol = FX.addDateColumn("Start",
				"startDate");
		TableColumn<Compensation, BigDecimal> dailyRateCol = FX.addPriceColumn(
				"Daily Rate", "dailyRate");
		table.getColumns().addAll(startCol, dailyRateCol);
	}

	@Override
	protected void setInputDialog() {
		inputDialog = new CompensationDialog(stage, dto);
	}
}
