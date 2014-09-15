package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.RemittanceDTO;
import ph.txtdis.fx.dialog.RemittanceDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.RemittanceDetail;
import ph.txtdis.type.RemittanceReferenceType;

public class RemittanceDetailTable extends AbstractInputTable<RemittanceDetail, RemittanceDTO> {

    public RemittanceDetailTable(Stage stage, RemittanceDTO dto) {
        super(stage, dto);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new RemittanceDialog(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {
        TableColumn<RemittanceDetail, RemittanceReferenceType> referenceTypeCol = FX.addComboColumn("Type",
                "reference", RemittanceReferenceType.values());
        TableColumn<RemittanceDetail, Integer> referenceIdCol = FX.addIntegerColumn("ID No.", "referenceId");
        TableColumn<RemittanceDetail, String> partnerNameCol = FX.addStringColumn("Customer Name", "partnerName", 180);
        TableColumn<RemittanceDetail, LocalDate> dateCol = FX.addDateColumn("Date", "orderDate");
        TableColumn<RemittanceDetail, BigDecimal> amountCol = FX.addPriceColumn("Amount", "amount");
        TableColumn<RemittanceDetail, BigDecimal> paymentCol = FX.addPriceColumn("Payment", "payment");
        TableColumn<RemittanceDetail, BigDecimal> balanceCol = FX.addPriceColumn("Balance", "balance");
        table.getColumns().addAll(referenceTypeCol, referenceIdCol, partnerNameCol, dateCol, amountCol, paymentCol,
                balanceCol);
    }
}
