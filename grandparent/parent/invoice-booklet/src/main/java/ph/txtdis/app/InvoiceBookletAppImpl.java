package ph.txtdis.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.InvoiceBookletDTO;
import ph.txtdis.fx.dialog.InvoiceBookletDialog;
import ph.txtdis.fx.table.AbstractTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;

public class InvoiceBookletAppImpl extends AbstractApp<InvoiceBooklet> {

    private TableView<InvoiceBooklet> table;

    public InvoiceBookletAppImpl() {
        super("Sales Invoice Booklet Issuance", null);
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
        table = new AbstractTable<InvoiceBooklet, InvoiceBookletDTO>(this, (InvoiceBookletDTO) dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {
                TableColumn<InvoiceBooklet, Integer> idStartCol = FX.addIntegerColumn("Starting No.", "startId");
                TableColumn<InvoiceBooklet, Integer> idEndCol = FX.addIntegerColumn("Ending No.", "endId");
                TableColumn<InvoiceBooklet, SystemUser> issuedToCol = FX.addComboColumn("Issued To", "issuedTo",
                        dto.listUsers());
                TableColumn<InvoiceBooklet, Timestamp> issuedByCol = FX.createColumn("Issued By", "createdBy", 120);
                TableColumn<InvoiceBooklet, Timestamp> issuedOnCol = FX.createColumn("Issued On", "timeStamp", 180);
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
        }.getTable();
        
        table.setItems(((InvoiceBookletDTO) dto).list());

        HBox box = new HBox(table);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        return new Node[] { box };
    }

    @Override
    protected String titleName() {
        return module;
    }

    @Override
    protected String getTitleName() {
        return App.title();
    }

    @Override
    public void setFocus() {
        table.requestFocus();
    }

    @Override
    public void save() {
    }

    @Override
    protected void setDisableBindings() {
    }

    @Override
    protected void addFooter(VBox box) {
    }
}
