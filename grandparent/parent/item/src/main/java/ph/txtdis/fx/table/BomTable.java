package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.dialog.BomDialog;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Bom;
import ph.txtdis.type.UomType;

public class BomTable extends AbstractTable<Bom, ItemDTO> {

    public BomTable(Stage stage, ItemDTO dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<Bom, Integer> partCol = FX.addIntegerColumn("Part ID", "child");
        TableColumn<Bom, UomType> uomCol = FX.addComboColumn("UOM", "uom", UomType.values());
        TableColumn<Bom, BigDecimal> qtyCol = FX.add4PlaceColumn("Quantity", "qty");
        table.getColumns().addAll(partCol, uomCol, qtyCol);
        table.setMinHeight(35);
    }

    @Override
    protected void createInputDialog() {
        inputDialog = new BomDialog(stage, dto);
    }
}
