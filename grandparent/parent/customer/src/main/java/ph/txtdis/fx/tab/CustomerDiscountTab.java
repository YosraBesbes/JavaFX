package ph.txtdis.fx.tab;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemFamilyDTO;
import ph.txtdis.fx.dialog.CustomerDiscountDialog;
import ph.txtdis.fx.table.AbstractInputTable;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.model.ItemFamily;
import ph.txtdis.model.SystemUser;

public class CustomerDiscountTab extends AbstractTab<CustomerDTO> {
    private TableView<CustomerDiscount> table;

    public CustomerDiscountTab(Stage stage, CustomerDTO dto) {
        super("Customer Discount", "discount", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, CustomerDTO dto) {

        table = new AbstractInputTable<CustomerDiscount, CustomerDTO>(stage, dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {

                ItemFamilyDTO family = App.getContext().getBean(ItemFamilyDTO.class);

                TableColumn<CustomerDiscount, Integer> typeCol = FX.addIntegerColumn("Level", "level");
                TableColumn<CustomerDiscount, BigDecimal> discountCol = FX.add4PlaceColumn("Discount", "discount");
                TableColumn<CustomerDiscount, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
                TableColumn<CustomerDiscount, ItemFamily> familyLimitCol = FX.addComboColumn("Limited To",
                        "familyLimit", family.list());
                TableColumn<CustomerDiscount, SystemUser> givenByCol = FX.createColumn("Given By", "createdBy", 120);
                TableColumn<CustomerDiscount, ZonedDateTime> givenDateCol = new TimestampDisplayColumn<>(stage,
                        "Given On", "timeStamp");
                table.getColumns().addAll(typeCol, discountCol, startCol, familyLimitCol, givenByCol, givenDateCol);
            }

            @Override
            protected void createInputDialog() {
                inputDialog = new CustomerDiscountDialog(stage, dto);
            }

        }.getTable();

        return new Node[] { table };
    }

    @Override
    public void save() {
        dto.setDiscounts(table.getItems());
    }

    @Override
    public void refresh() {
        table.setItems(dto.getDiscounts());
    }
}
