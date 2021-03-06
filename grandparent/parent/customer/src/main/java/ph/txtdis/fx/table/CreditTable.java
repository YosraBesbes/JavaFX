package ph.txtdis.fx.table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.dialog.CreditDialog;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Users;

public class CreditTable extends AbstractInputTable<CreditDetail, CustomerDTO> {

    public CreditTable(Stage stage, CustomerDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<CreditDetail, Integer> termCol = FX.addIntegerColumn("Term", "term");
        TableColumn<CreditDetail, Integer> gracePeriodCol = FX.addIntegerColumn("Grace Period", "gracePeriod");
        TableColumn<CreditDetail, BigDecimal> limitCol = FX.addPriceColumn("Limit", "limit");
        TableColumn<CreditDetail, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
        TableColumn<CreditDetail, Users> approvedByCol = FX.createColumn("Approved By", "createdBy", 120);
        TableColumn<CreditDetail, ZonedDateTime> approvedDateCol = new TimestampDisplayColumn<>(stage, "Approved On",
                "createdDate");
        table.getColumns().addAll(termCol, gracePeriodCol, limitCol, startCol, approvedByCol, approvedDateCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new CreditDialog(stage, dto);
    }
}
