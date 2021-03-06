package ph.txtdis.fx.tab;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.App;
import ph.txtdis.dto.ChannelDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.fx.dialog.VolumeDiscountDialog;
import ph.txtdis.fx.table.AbstractInputTable;
import ph.txtdis.fx.tablecolumn.TimestampDisplayColumn;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Channel;
import ph.txtdis.model.Users;
import ph.txtdis.model.VolumeDiscount;
import ph.txtdis.type.UomType;
import ph.txtdis.type.VolumeDiscountType;

public class VolumeDiscountTab extends AbstractTab<ItemDTO> {
    private TableView<VolumeDiscount> table;

    public VolumeDiscountTab(Stage stage, ItemDTO dto) {
        super("Volume Discount", "discount", stage, dto);
    }

    @Override
    protected Node[] addNodes(Stage stage, ItemDTO dto) {

        table = new AbstractInputTable<VolumeDiscount, ItemDTO>(stage, dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {

                ChannelDTO channel = App.getContext().getBean(ChannelDTO.class);

                TableColumn<VolumeDiscount, VolumeDiscountType> typeCol = FX.addComboColumn("Type", "type",
                        VolumeDiscountType.values());
                TableColumn<VolumeDiscount, UomType> uomCol = FX.addComboColumn("UOM", "uom", UomType.values());
                TableColumn<VolumeDiscount, Integer> cutOffCol = FX.addIntegerColumn("Target", "cutOff");
                TableColumn<VolumeDiscount, BigDecimal> discountCol = FX.addPriceColumn("Discount", "discount");
                TableColumn<VolumeDiscount, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
                TableColumn<VolumeDiscount, Channel> channelLimitCol = FX.addComboColumn("Limited to", "channelLimit",
                        channel.list());
                TableColumn<VolumeDiscount, Users> givenByCol = FX.createColumn("Given By", "createdBy", 120);
                TableColumn<VolumeDiscount, ZonedDateTime> givenDateCol = new TimestampDisplayColumn<>(stage,
                        "Given On", "createdDate");
                table.getColumns().addAll(typeCol, uomCol, cutOffCol, discountCol, startCol, channelLimitCol,
                        givenByCol, givenDateCol);
            }

            @Override
            protected void createInputDialog() {
                inputDialog = new VolumeDiscountDialog(stage, dto);
            }

        }.getTable();

        return new Node[] { table };
    }

    @Override
    public void save() {
        dto.setVolumeDiscounts(table.getItems());
    }

    @Override
    public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(dto.getVolumeDiscounts());
    }
}
