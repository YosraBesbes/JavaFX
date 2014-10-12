package ph.txtdis.fx.tab;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.GovtIdDialog;
import ph.txtdis.fx.table.AbstractInputTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.GovtId;
import ph.txtdis.type.GovtIdType;

public class GovtIdTab extends AbstractTab<EmployeeDTO> {
    private TableView<GovtId> table;

    public GovtIdTab(Stage stage, EmployeeDTO dto) {
        super("Gov't ID", "govtId", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, EmployeeDTO dto) {

        table = new AbstractInputTable<GovtId, EmployeeDTO>(stage, dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {
                TableColumn<GovtId, Byte[]> imageCol = FX.addImageColumn(stage, "Image", "image");
                TableColumn<GovtId, GovtIdType> typeCol = FX.addComboColumn("Type", "type", GovtIdType.values());
                TableColumn<GovtId, LocalDate> issuanceCol = FX.addDateColumn("Issuance", "issuance");
                TableColumn<GovtId, LocalDate> expiryCol = FX.addDateColumn("Expiry", "expiry");
                TableColumn<GovtId, String> detailCol = FX.addStringColumn("Detail", "detail", 130);
                table.getColumns().addAll(imageCol, typeCol, issuanceCol, expiryCol, detailCol);
            }

            @Override
            protected void createInputDialog() {
                inputDialog = new GovtIdDialog(stage, dto);
            }

        }.getTable();

        return new Node[] { table };
    }

    @Override
    public void save() {
        dto.setGovtIds(table.getItems());
    }

    @Override
    public void refresh() {
        table.setItems(dto.getGovtIds());
    }
}
