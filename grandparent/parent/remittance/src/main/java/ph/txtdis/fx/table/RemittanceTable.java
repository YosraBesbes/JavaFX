package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.app.RemittanceAppImpl;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.fx.dialog.RemittanceDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.RemittanceDetail;

public class RemittanceTable extends AbstractInputTable<RemittanceDetail, RemittanceDTO> {
    private static SimpleObjectProperty<BigDecimal> remainingPayment;

    public RemittanceTable(Stage stage, RemittanceDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new RemittanceDialog(stage, dto, remainingPayment.get());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        remainingPayment = ((RemittanceAppImpl) stage).getRemainingPayment();

        TableColumn<RemittanceDetail, Integer> invoiceIdCol = FX.addIntegerColumn("ID No.", "invoiceId");
        TableColumn<RemittanceDetail, String> partnerCol = FX.addStringColumn("Customer", "partner", 180);
        TableColumn<RemittanceDetail, LocalDate> dateCol = FX.addDateColumn("Date", "date");
        TableColumn<RemittanceDetail, BigDecimal> unpaidCol = FX.addPriceColumn("Unpaid", "amountValue");
        TableColumn<RemittanceDetail, BigDecimal> paymentCol = FX.addPriceColumn("Payment", "paymentValue");
        TableColumn<RemittanceDetail, BigDecimal> balanceCol = FX.addPriceColumn("Balance", "balanceValue");
        table.getColumns().addAll(invoiceIdCol, partnerCol, dateCol, unpaidCol, paymentCol, balanceCol);
    }

    @Override
    protected void addTableItems() {
        super.addTableItems();
        remainingPayment.set(((RemittanceDialog) inputDialog).getRemainingPayment());
    }

    @Override
    public void setTableContextMenu() {
        System.err.println("remainingPayment = " + remainingPayment.get());
        table.contextMenuProperty().bind(
                Bindings.when(remainingPayment.isNotEqualTo(BigDecimal.ZERO)).then(createContextMenu())
                        .otherwise((ContextMenu) null));
    }

}
