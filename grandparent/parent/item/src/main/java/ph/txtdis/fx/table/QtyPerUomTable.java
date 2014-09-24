package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.dialog.QtyPerUomDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.QtyPerUom;
import ph.txtdis.type.UomType;

public class QtyPerUomTable extends AbstractInputTable<QtyPerUom, ItemDTO> {

    public QtyPerUomTable(Stage stage, ItemDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<QtyPerUom, UomType> uomCol = FX.addComboColumn("UOM", "uom", UomType.values());
        TableColumn<QtyPerUom, BigDecimal> qtyCol = FX.add4PlaceColumn("Quantity", "qty");
        TableColumn<QtyPerUom, Boolean> isPurchasedCol = FX.addBooleanColumn("Purchased", "purchased");
        TableColumn<QtyPerUom, Boolean> isSoldCol = FX.addBooleanColumn("Sold", "sold");
        TableColumn<QtyPerUom, Boolean> isReportedCol = FX.addBooleanColumn("Reported", "reported");
        table.getColumns().addAll(uomCol, qtyCol, isPurchasedCol, isSoldCol, isReportedCol);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new QtyPerUomDialog(stage, dto);
    }
}
