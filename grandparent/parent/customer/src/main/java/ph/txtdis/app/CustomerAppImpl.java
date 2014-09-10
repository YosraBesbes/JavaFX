package ph.txtdis.app;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.dialog.FoundCustomerDialog;
import ph.txtdis.fx.tab.CreditTab;
import ph.txtdis.fx.tab.CustomerDiscountTab;
import ph.txtdis.fx.tab.CustomerTab;
import ph.txtdis.fx.tab.Tabled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Customer;
import ph.txtdis.type.CustomerType;

public class CustomerAppImpl extends AbstractApp<Customer> implements Searched {

    private List<Tab> tabs = new ArrayList<>();
    private CustomerTab customerTab;
    private CreditTab creditTab;
    private CustomerDiscountTab discountTab;
    private Tabled[] tabsWithTables;

    public CustomerAppImpl() {
        super("Customer", "Customer");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void refresh() {
        for (Tabled t : tabsWithTables)
            t.refresh();
        super.refresh();
    }

    @Override
    public void setFocus() {
        customerTab.getTab().getTabPane().getSelectionModel().select(0);
        customerTab.getNameField().requestFocus();
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
    public void listFind() {
        new FoundCustomerDialog(this, (CustomerDTO) dto);
    }

    @Override
    protected void setDisableBindings() {
        creditTab.getTab().disableProperty().bind(FX.isNot(getTypeCombo(), CustomerType.OUTLET));
        discountTab.getTab().disableProperty().bind(creditTab.getTab().disabledProperty());
        buttons.get("delete").setDisable(true);
        buttons.get("save")
                .disableProperty()
                .bind(FX.isEmpty(getTypeCombo()).or(
                        FX.is(getTypeCombo(), CustomerType.OUTLET).and(FX.isEmpty(customerTab.getRoutingTable()))));
    }

    private ComboBox<CustomerType> getTypeCombo() {
        return customerTab.getTypeCombo();
    }

    private void tabledTabs() {
        CustomerDTO dto = (CustomerDTO) this.dto;
        customerTab = new CustomerTab(this, dto);
        creditTab = new CreditTab(this, dto);
        discountTab = new CustomerDiscountTab(this, dto);
        tabsWithTables = new Tabled[] { customerTab, creditTab, discountTab };
    }

    private void tabs() {
        for (Tabled t : tabsWithTables)
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
    protected String getTitleName() {
        return App.title();
    }

    @Override
    public void save() {
        for (Tabled t : tabsWithTables)
            t.save();
        super.save();
    }
}
