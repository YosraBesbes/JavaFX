package ph.txtdis.app;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.InvoiceBookletDTO;
import ph.txtdis.fx.dialog.InvoiceBookletDialog;
import ph.txtdis.fx.table.AbstractInputTable;
import ph.txtdis.fx.tablecolumn.IdDisplayColumn;
import ph.txtdis.fx.tablecolumn.TextDisplayColumn;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.model.InvoiceBooklet;

public class InvoiceBookletAppImpl extends AbstractApp<InvoiceBooklet, Integer> {

    private TableView<InvoiceBooklet> table;

    public InvoiceBookletAppImpl() {
        super("Sales Invoice Booklet Issuance", "");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(InvoiceBookletDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
    }

    @Override
    protected Node[] addVBoxNodes() {
        table = new AbstractInputTable<InvoiceBooklet, InvoiceBookletDTO>(this, (InvoiceBookletDTO) dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {
                TableColumn<InvoiceBooklet, Integer> idStartCol = new IdDisplayColumn<>(stage, "Starting No.",
                        "startId");
                TableColumn<InvoiceBooklet, Integer> idEndCol = new IdDisplayColumn<>(stage, "Ending No.", "endId");
                TableColumn<InvoiceBooklet, String> issuedToCol = new TextDisplayColumn<>(stage, "Issued To",
                        "issuedTo", 120, Pos.CENTER_LEFT);
                TableColumn<InvoiceBooklet, String> issuedByCol = new TextDisplayColumn<>(stage, "Issued By",
                        "createdBy", 120, Pos.CENTER_LEFT);
                TableColumn<InvoiceBooklet, ZonedDateTime> issuedOnCol = new TimestampDisplayColumn<>(stage,
                        "Issued On", "createdDate");
                table.getColumns().addAll(idStartCol, idEndCol, issuedToCol, issuedByCol, issuedOnCol);
            }

            @Override
            protected void createInputDialog() {
                inputDialog = new InvoiceBookletDialog(stage, dto);
            }

            @Override
            protected List<InvoiceBooklet> getAddedItems() {
                List<InvoiceBooklet> booklets = new ArrayList<>();
                inputDialog.getAddedItems().forEach(booklet -> {
                    dto.set(booklet);
                    dto.save();
                    booklets.add(dto.get(dto.getId()));
                });
                return booklets;
            }

            @Override
            protected ContextMenu createPerTableRowMenu(TableView<InvoiceBooklet> table, TableRow<InvoiceBooklet> row) {
                return null;
            }

        }.getTable();

        table.setItems(((InvoiceBookletDTO) dto).list());

        HBox box = new HBox(table);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        return new Node[] { box };
    }

    @Override
    public void setFocus() {
        table.requestFocus();
    }

    @Override
    public void save() {
    }

    @Override
    protected void setBindings() {
    }

    @Override
    protected void addFooter(VBox box) {
    }
}
