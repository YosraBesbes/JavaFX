package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.LoanDialog;
import ph.txtdis.fx.dialog.PaymentTableDialog;
import ph.txtdis.fx.dialog.Tabled;
import ph.txtdis.fx.tablecell.TabledPriceFieldTableCell;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Loan;
import ph.txtdis.model.Payment;
import ph.txtdis.type.LoanType;

public class LoanBoxedTable extends AbstractBoxedTable<Loan, EmployeeDTO> {

    public LoanBoxedTable(Stage stage, EmployeeDTO dto) {
        super("Loan", stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<Loan, LoanType> typeCol = FX.addComboColumn("Type", "type", LoanType.values());
        TableColumn<Loan, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
        TableColumn<Loan, BigDecimal> amountCol = FX.addPriceColumn("Amount", "amount");
        amountCol.setOnEditCommit(event -> {
            Loan loan = event.getRowValue();
            loan.setAmount(event.getNewValue());
            loan.setBalance();
            ObservableList<Loan> loans = event.getTableView().getItems();
            int index = loans.indexOf(loan);
            loans.set(index, loan);
        });

        TableColumn<Loan, BigDecimal> paymentCol = FX.createColumn("Payment", "payment", 100);
        paymentCol.setCellFactory(column -> {
            return new TabledPriceFieldTableCell<Loan, Payment>(stage) {
                @Override
                protected Loan addItems(Loan loan, Tabled<Payment, Loan> dialog) {
                    loan.setPayments(dialog.getAddedItems(loan));
                    return loan;
                }

                @Override
                protected Tabled<Payment, Loan> addDialog(Stage stage, Loan loan) {
                    return new PaymentTableDialog(stage, loan);
                }
            };
        });
        paymentCol.setOnEditCommit(event -> event.getRowValue().setPayment(event.getNewValue()));

        TableColumn<Loan, BigDecimal> balanceCol = FX.addPriceColumn("Balance", "balance");
        balanceCol.setEditable(false);

        table.getColumns().addAll(typeCol, startCol, amountCol, paymentCol, balanceCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new LoanDialog(stage, dto);
    }
}
