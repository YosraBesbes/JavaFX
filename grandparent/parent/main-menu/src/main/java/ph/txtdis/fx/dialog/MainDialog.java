package ph.txtdis.fx.dialog;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.springframework.context.ConfigurableApplicationContext;

import ph.txtdis.fx.button.AgingButton;
import ph.txtdis.fx.button.BackupButton;
import ph.txtdis.fx.button.BookingButton;
import ph.txtdis.fx.button.ChannelButton;
import ph.txtdis.fx.button.CustomerButton;
import ph.txtdis.fx.button.DayEndReportButton;
import ph.txtdis.fx.button.FamilyButton;
import ph.txtdis.fx.button.GroupButton;
import ph.txtdis.fx.button.HRButton;
import ph.txtdis.fx.button.InventoryButton;
import ph.txtdis.fx.button.InvoiceBookletButton;
import ph.txtdis.fx.button.InvoicingButton;
import ph.txtdis.fx.button.ItemButton;
import ph.txtdis.fx.button.ItemTreeButton;
import ph.txtdis.fx.button.PickingButton;
import ph.txtdis.fx.button.PurchasingButton;
import ph.txtdis.fx.button.ReceivingButton;
import ph.txtdis.fx.button.RemittanceButton;
import ph.txtdis.fx.button.ReturnButton;
import ph.txtdis.fx.button.RouteButton;
import ph.txtdis.fx.button.SalesButton;
import ph.txtdis.fx.button.StockTakeButton;
import ph.txtdis.fx.button.StockTakeReconciliationButton;
import ph.txtdis.fx.button.StyleButton;
import ph.txtdis.fx.button.TruckButton;
import ph.txtdis.fx.button.UserButton;
import ph.txtdis.fx.button.VatButton;
import ph.txtdis.fx.button.WarehouseButton;
import ph.txtdis.fx.util.FX;
import ph.txtdis.fx.util.StyleSheet;
import ph.txtdis.model.Style;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.StyleService;
import ph.txtdis.util.Login;

public class MainDialog extends Stage {

    private StyleService styleService;
    private StyleSheet styleSheet = new StyleSheet();

    public MainDialog(ConfigurableApplicationContext context) {

        styleService = context.getBean(StyleService.class);
        setDefaultStyle(Login.user());

        Button agingButton = new AgingButton(this).getButton();
        Button backupButton = new BackupButton(this).getButton();
        Button bookingButton = new BookingButton(this).getButton();
        Button channelButton = new ChannelButton(this).getButton();
        Button customerButton = new CustomerButton(this).getButton();
        Button dayEndButton = new DayEndReportButton(this).getButton();
        Button familyButton = new FamilyButton(this).getButton();
        Button groupButton = new GroupButton(this).getButton();
        Button itemButton = new ItemButton(this).getButton();
        Button pickingButton = new PickingButton(this).getButton();
        Button purchasingButton = new PurchasingButton(this).getButton();
        Button receivingButton = new ReceivingButton(this).getButton();
        Button returnButton = new ReturnButton(this).getButton();
        Button routeButton = new RouteButton(this).getButton();
        Button hrButton = new HRButton(this).getButton();
        Button inventoryButton = new InventoryButton(this).getButton();
        Button invoiceBookletButton = new InvoiceBookletButton(this).getButton();
        Button invoicingButton = new InvoicingButton(this).getButton();
        Button remittanceButton = new RemittanceButton(this).getButton();
        Button salesButton = new SalesButton(this).getButton();
        Button stockTakeButton = new StockTakeButton(this).getButton();
        Button stockTakeReconciliationButton = new StockTakeReconciliationButton(this).getButton();
        Button styleButton = new StyleButton(this, styleSheet, styleService).getButton();
        Button treeButton = new ItemTreeButton(this).getButton();
        Button truckButton = new TruckButton(this).getButton();
        Button userButton = new UserButton(context).getButton();
        Button vatButton = new VatButton(this).getButton();
        Button warehouseButton = new WarehouseButton(this).getButton();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(purchasingButton, 0, 0);
        gridPane.add(receivingButton, 1, 0);
        gridPane.add(bookingButton, 2, 0);
        gridPane.add(returnButton, 3, 0);
        gridPane.add(pickingButton, 4, 0);
        gridPane.add(dayEndButton, 5, 0);
        gridPane.add(salesButton, 6, 0);

        gridPane.add(getLabel("Purchasing"), 0, 1);
        gridPane.add(getLabel("Receiving"), 1, 1);
        gridPane.add(getLabel("Booking"), 2, 1);
        gridPane.add(getLabel("RMA"), 3, 1);
        gridPane.add(getLabel("Picking"), 4, 1);
        gridPane.add(getLabel("Day-End"), 5, 1);
        gridPane.add(getLabel("Month-End"), 6, 1);

        gridPane.add(itemButton, 0, 2);
        gridPane.add(familyButton, 1, 2);
        gridPane.add(treeButton, 2, 2);
        gridPane.add(warehouseButton, 3, 2);
        gridPane.add(inventoryButton, 4, 2);
        gridPane.add(stockTakeButton, 5, 2);
        gridPane.add(stockTakeReconciliationButton, 6, 2);

        gridPane.add(getLabel("Item"), 0, 3);
        gridPane.add(getLabel("Family"), 1, 3);
        gridPane.add(getLabel("Tree"), 2, 3);
        gridPane.add(getLabel("Warehouse"), 3, 3);
        gridPane.add(getLabel("Inventory"), 4, 3);
        gridPane.add(getLabel("Stock Take"), 5, 3);
        gridPane.add(getLabel("Reconciliation"), 6, 3);

        gridPane.add(truckButton, 0, 4);
        gridPane.add(routeButton, 1, 4);
        gridPane.add(channelButton, 2, 4);
        gridPane.add(customerButton, 3, 4);
        gridPane.add(invoiceBookletButton, 4, 4);
        gridPane.add(invoicingButton, 5, 4);
        gridPane.add(remittanceButton, 6, 4);

        gridPane.add(getLabel("Truck"), 0, 5);
        gridPane.add(getLabel("Route"), 1, 5);
        gridPane.add(getLabel("Channel"), 2, 5);
        gridPane.add(getLabel("Customer"), 3, 5);
        gridPane.add(getLabel("Invoice"), 4, 5);
        gridPane.add(getLabel("Invoicing"), 5, 5);
        gridPane.add(getLabel("Remittance"), 6, 5);

        gridPane.add(userButton, 0, 6);
        gridPane.add(groupButton, 1, 6);
        gridPane.add(hrButton, 2, 6);
        gridPane.add(styleButton, 3, 6);
        gridPane.add(backupButton, 4, 6);
        gridPane.add(agingButton, 5, 6);
        gridPane.add(vatButton, 6, 6);

        gridPane.add(getLabel("User"), 0, 7);
        gridPane.add(getLabel("Group"), 1, 7);
        gridPane.add(getLabel("HR"), 2, 7);
        gridPane.add(getLabel("Style"), 3, 7);
        gridPane.add(getLabel("Backup"), 4, 7);
        gridPane.add(getLabel("Aging"), 5, 7);
        gridPane.add(getLabel("VAT"), 6, 7);

        HBox dialogBox = new HBox(gridPane);
        dialogBox.setPadding(new Insets(10));
        dialogBox.setStyle("-fx-border-color: -fx-base; -fx-background-color: #aaaaff; -fx-accent: white; ");
        dialogBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogBox);
        FX.putIconAndTitle(this);
        setScene(dialogScene);

        getScene().getStylesheets().addAll("/css/base.css");
        setTitle("Welcome to txtDIS!");
    }

    private Label getLabel(String name) {
        Label label = new Label(name);
        GridPane.setHalignment(label, HPos.CENTER);
        return label;
    }

    private void setDefaultStyle(SystemUser user) {
        Style style = styleService.get(user);
        if (style != null)
            styleSheet.update(style);
    }
}
