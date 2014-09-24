package ph.txtdis.fx.dialog;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.fx.dialog.AbstractTableDialog;
import ph.txtdis.fx.table.PaymentTable;
import ph.txtdis.model.Loan;
import ph.txtdis.model.Payment;

public class PaymentTableDialog extends AbstractTableDialog<Payment, Loan> {

	public PaymentTableDialog(Stage stage, Loan loan) {
		super("Payment", stage, loan);
	}

	@Override
	protected TableView<Payment> createTable(Loan loan) {
		return new PaymentTable(getStage(), loan).getTable();
	}

	@Override
	public List<Payment> getAddedItems(Loan loan) {
		return table.getItems();
	}

	@Override
	protected TableView<Payment> addTableItems(TableView<Payment> table,
			Loan dto) {
		ObservableList<Payment> payments = FXCollections.observableArrayList(dto.getPayments());
		table.setItems(payments);
		return table;
	}
}
