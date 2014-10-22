package ph.txtdis.app;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.button.CancelButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.SearchByTextButton;
import ph.txtdis.fx.dialog.FoundCustomerDialog;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.tab.CreditTab;
import ph.txtdis.fx.tab.CustomerDiscountTab;
import ph.txtdis.fx.tab.CustomerTab;
import ph.txtdis.fx.tab.Tabbed;
import ph.txtdis.fx.table.CustomerTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Customer;
import ph.txtdis.type.CustomerType;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class CustomerAppImpl extends AbstractIdApp<Customer> implements Disable, Excel, Searched {

    private List<Tab> tabs = new ArrayList<>();
    private CustomerDTO customer;
    private CustomerTab customerTab;
    private CreditTab creditTab;
    private CustomerDiscountTab discountTab;
    private Tabbed[] tabsWithTables;
    private TimestampDisplay disabledOnDisplay;
    private UserDisplay disabledByDisplay;

    public CustomerAppImpl() {
        super("Customer", "");
    }

    @Override
    protected void setDTO() {
        dto = customer = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void refresh() {
        for (Tabbed t : tabsWithTables)
            t.refresh();
        refreshDisableStamps();
        super.refresh();
    }

    @Override
    public void setFocus() {
        customerTab.getTab().getTabPane().getSelectionModel().select(0);
        customerTab.getNameField().requestFocus();
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("excel", new ExcelButton(this).getButton());
        buttons.put("cancel", new CancelButton<Customer>(this, dto).getButton());
        buttons.put("search", new SearchByTextButton<Customer>(this, dto).getButton());
    }

    @Override
    protected Node[] addVBoxNodes() {
        tabledTabs();
        tabs();
        return new Node[] { tabPane() };
    }

    @Override
    public String setSearchedCriteria() {
        return "name";
    }

    @Override
    public void listFoundEntities() {
        new FoundCustomerDialog(this, (CustomerDTO) dto);
    }

    @Override
    protected void setBindings() {
        creditTab.getTab().disableProperty().bind(FX.isNot(getTypeCombo(), CustomerType.OUTLET));
        discountTab.getTab().disableProperty().bind(creditTab.getTab().disabledProperty());
        buttons.get("cancel").disableProperty()
                .bind(FX.isEmpty(customerTab.getIdField()).or(FX.isEmpty(disabledByDisplay).not()));
        buttons.get("save")
                .disableProperty()
                .bind(FX.isEmpty(getTypeCombo()).or(FX.isEmpty(disabledByDisplay).not())
                        .or(FX.is(getTypeCombo(), CustomerType.OUTLET).and(FX.isEmpty(customerTab.getRoutingTable()))));
    }

    private ComboBox<CustomerType> getTypeCombo() {
        return customerTab.getTypeCombo();
    }

    private void tabledTabs() {
        customerTab = new CustomerTab(this, customer);
        creditTab = new CreditTab(this, customer);
        discountTab = new CustomerDiscountTab(this, customer);
        tabsWithTables = new Tabbed[] { customerTab, creditTab, discountTab };
    }

    private void tabs() {
        for (Tabbed t : tabsWithTables)
            tabs.add(t.getTab());
    }

    private TabPane tabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 80;");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(tabs);
        return tabPane;
    }

    @Override
    public void save() throws InvalidException {
        for (Tabbed t : tabsWithTables)
            t.save();
        customer.save();
    }

    @Override
    public void saveAsExcel() {
        TableView<Customer> customerTable = new CustomerTable(this).getTable();
        customerTable.setId(module + " List as of " + Util.formatDate(LocalDate.now()));
        customerTable.setItems(customer.list());
        new ExcelWriter(Arrays.asList(Arrays.asList(customerTable)), module, Util.dateToFileName(LocalDate.now()));
    }

    @Override
    protected void setUserBox() {
        super.setUserBox();
        userHBox.getChildren().addAll(addDisablerNodes());
    }

    private Node[] addDisablerNodes() {
        Label disabledByLabel = new Label("Disabled By");
        disabledByDisplay = new UserDisplay(customer.getDisabledBy());
        Label disabledOnLabel = new Label("On");
        disabledOnDisplay = new TimestampDisplay(customer.getDisabledOn());
        return new Node[] { disabledByLabel, disabledByDisplay, disabledOnLabel, disabledOnDisplay };
    }

    private void refreshDisableStamps() {
        disabledByDisplay.setUser(customer.getDisabledBy());
        disabledOnDisplay.setTimestamp(customer.getDisabledOn());
    }

    @Override
    public void disable() {
        customer.setDisabledBy(Login.user());
        customer.setDisabledOn(ZonedDateTime.now());
        customer.save();
        refresh();
    }
}
