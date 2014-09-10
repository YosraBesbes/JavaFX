package ph.txtdis.fx.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.springframework.context.ConfigurableApplicationContext;

import ph.txtdis.fx.button.AccountButton;
import ph.txtdis.fx.button.AgingButton;
import ph.txtdis.fx.button.BackupButton;
import ph.txtdis.fx.button.BookingButton;
import ph.txtdis.fx.button.ChannelButton;
import ph.txtdis.fx.button.CmDmButton;
import ph.txtdis.fx.button.CustomerButton;
import ph.txtdis.fx.button.DeliveryButton;
import ph.txtdis.fx.button.EwtButton;
import ph.txtdis.fx.button.FamilyButton;
import ph.txtdis.fx.button.GroupButton;
import ph.txtdis.fx.button.HRButton;
import ph.txtdis.fx.button.IncentiveButton;
import ph.txtdis.fx.button.InventoryButton;
import ph.txtdis.fx.button.InvoiceBookletButton;
import ph.txtdis.fx.button.InvoicingButton;
import ph.txtdis.fx.button.ItemButton;
import ph.txtdis.fx.button.ItemTreeButton;
import ph.txtdis.fx.button.PickingButton;
import ph.txtdis.fx.button.PurchasingButton;
import ph.txtdis.fx.button.ReceivingButton;
import ph.txtdis.fx.button.RemittanceButton;
import ph.txtdis.fx.button.RestoreButton;
import ph.txtdis.fx.button.ReturnButton;
import ph.txtdis.fx.button.RouteButton;
import ph.txtdis.fx.button.RouteLoadReconciliationButton;
import ph.txtdis.fx.button.RouteRemittanceReconciliationButton;
import ph.txtdis.fx.button.SalesButton;
import ph.txtdis.fx.button.StockTakeButton;
import ph.txtdis.fx.button.StockTakeReconciliationButton;
import ph.txtdis.fx.button.StockTakeSummaryButton;
import ph.txtdis.fx.button.StyleButton;
import ph.txtdis.fx.button.UserButton;
import ph.txtdis.fx.button.ValeButton;
import ph.txtdis.fx.button.VatButton;
import ph.txtdis.fx.button.WarehouseButton;
import ph.txtdis.fx.util.FX;
import ph.txtdis.fx.util.StyleSheet;
import ph.txtdis.model.Style;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.StyleService;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class MainDialog extends Stage {
    
    private StyleService styleService;
    private StyleSheet styleSheet = new StyleSheet();

    public MainDialog(ConfigurableApplicationContext context) {
        
        styleService = context.getBean(StyleService.class);
        setDefaultStyle(Login.user());
        
        Button accountButton = new AccountButton(this).getButton();
        Button agingButton = new AgingButton(this).getButton();
        Button backupButton = new BackupButton(this).getButton();
        Button bookingButton = new BookingButton(this).getButton();
        Button channelButton = new ChannelButton(this).getButton();
        Button cmdmButton = new CmDmButton(this).getButton();
        Button customerButton = new CustomerButton(this).getButton();
        Button deliveryButton = new DeliveryButton(this).getButton();
        Button ewtButton = new EwtButton(this).getButton();
        Button familyButton = new FamilyButton(this).getButton();
        Button groupButton = new GroupButton(this).getButton();
        Button itemButton = new ItemButton(this).getButton();
        Button pickingButton = new PickingButton(this).getButton();
        Button purchasingButton = new PurchasingButton(this).getButton();
        Button receivingButton = new ReceivingButton(this).getButton();
        Button returnButton = new ReturnButton(this).getButton();
        Button routeLoadButton = new RouteLoadReconciliationButton(this).getButton();
        Button routeSalesButton = new RouteRemittanceReconciliationButton(this).getButton();
        Button routeButton = new RouteButton(this).getButton();
        Button hrButton = new HRButton(this).getButton();
        Button incentiveButton = new IncentiveButton(this).getButton();
        Button inventoryButton = new InventoryButton(this).getButton();
        Button invoiceBookletButton = new InvoiceBookletButton(this).getButton();
        Button invoicingButton = new InvoicingButton(this).getButton();
        Button remittanceButton = new RemittanceButton(this).getButton();
        Button restoreButton = new RestoreButton(this).getButton();
        Button salesButton = new SalesButton(this).getButton();
        Button stockTakeButton = new StockTakeButton(this).getButton();
        Button stockTakeSummaryButton = new StockTakeSummaryButton(this).getButton();
        Button stockTakeVarianceButton = new StockTakeReconciliationButton(this).getButton();
        Button styleButton = new StyleButton(this, styleSheet, styleService).getButton();
        Button treeButton = new ItemTreeButton(this).getButton();
        Button userButton = new UserButton(this).getButton();
        Button vatButton = new VatButton(this).getButton();
        Button valeButton = new ValeButton(this).getButton();
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
        gridPane.add(deliveryButton, 5, 0);
        gridPane.add(routeLoadButton, 6, 0);
        gridPane.add(routeSalesButton, 7, 0);
        gridPane.add(valeButton, 8, 0);
                
        gridPane.add(familyButton, 0, 1);
        gridPane.add(itemButton, 1, 1);
        gridPane.add(treeButton, 2, 1);
        gridPane.add(warehouseButton, 3, 1);
        gridPane.add(inventoryButton, 4, 1);
        gridPane.add(stockTakeButton, 5, 1);
        gridPane.add(stockTakeSummaryButton, 6, 1);
        gridPane.add(stockTakeVarianceButton, 7, 1);
        gridPane.add(incentiveButton, 8, 1);
        
        gridPane.add(routeButton, 0, 2);
        gridPane.add(accountButton, 1, 2);
        gridPane.add(channelButton, 2, 2);
        gridPane.add(customerButton, 3, 2);
        gridPane.add(invoiceBookletButton, 4, 2);
        gridPane.add(invoicingButton, 5, 2);
        gridPane.add(remittanceButton, 6, 2);
        gridPane.add(agingButton, 7, 2);
        gridPane.add(salesButton, 8, 2);

        gridPane.add(groupButton, 0, 3);
        gridPane.add(userButton, 1, 3);
        gridPane.add(hrButton, 2, 3);
        gridPane.add(styleButton, 3, 3);
        gridPane.add(backupButton, 4, 3);
        gridPane.add(restoreButton, 5, 3);
        gridPane.add(ewtButton, 6, 3);
        gridPane.add(vatButton, 7, 3);
        gridPane.add(cmdmButton, 8, 3);

        HBox dialogBox = new HBox(gridPane);
        dialogBox.setPadding(new Insets(10));
        dialogBox.setStyle("-fx-border-color: -fx-base; -fx-background-color: #aaaaff; -fx-accent: white; ");
        dialogBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogBox);
        FX.decorateWindow(this);
        setScene(dialogScene);

        getScene().getStylesheets().addAll("/css/base.css");
        setTitle("Welcome to txtDIS!");
    }

    private void setDefaultStyle(SystemUser user) {
        Style style = styleService.get(user);
        if (style == null) {
            String base = Util.colorToRGBA(Color.NAVY);
            String accent = Util.colorToRGBA(Color.BLUE);
            style = new Style(user, base, null, accent, null);
        }
        styleSheet.update(style);
    }
}
