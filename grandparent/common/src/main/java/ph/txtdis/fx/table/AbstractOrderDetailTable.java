package ph.txtdis.fx.table;

import java.math.BigDecimal;

import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import ph.txtdis.fx.util.FX;
import ph.txtdis.type.UomType;

public abstract class AbstractOrderDetailTable<E, D> extends AbstractInputTable<E, D> {

    public AbstractOrderDetailTable(Stage stage, D dto) {
        super(stage, dto);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addTableColumns() {

        TableColumn<E, Integer> itemIdCol = FX.addIntegerColumn("Item ID", "itemId");
        TableColumn<E, String> itemNameCol = FX.addStringColumn("Name", "itemName", 180);
        itemNameCol.setEditable(false);
        
        TableColumn<E, UomType> uomCol = FX.addComboColumn("UOM", "uom", UomType.values(), 30);
        TableColumn<E, BigDecimal> qtyCol = FX.add4PlaceColumn("Quantity", "qty");
        table.getColumns().addAll(itemIdCol, itemNameCol, uomCol, qtyCol);
    }
}
