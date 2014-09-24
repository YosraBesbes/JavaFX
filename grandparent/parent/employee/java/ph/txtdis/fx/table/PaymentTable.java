package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.sql.Date;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.dialog.PaymentInputDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Loan;
import ph.txtdis.model.Payment;

public class PaymentTable extends AbstractTable<Payment, Loan> {

	public PaymentTable(Stage stage, Loan loan) {
		super(stage, loan);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addTableColumns() {
		TableColumn<Payment, Date> paymentCol = FX.addDateColumn("Date",
				"paymentDate");

		TableColumn<Payment, BigDecimal> amountCol = FX.addPriceColumn(
				"Amount", "amount");

		table.getColumns().addAll(paymentCol, amountCol);
	}

	@Override
	protected void setInputDialog() {
		inputDialog = new PaymentInputDialog(stage, dto);
	}
}
