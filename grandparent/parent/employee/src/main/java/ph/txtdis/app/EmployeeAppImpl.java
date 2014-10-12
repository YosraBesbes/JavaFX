package ph.txtdis.app;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import ph.txtdis.App;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.button.CancelButton;
import ph.txtdis.fx.button.SearchByTextButton;
import ph.txtdis.fx.dialog.FoundEmployeeDialog;
import ph.txtdis.fx.tab.CurrentJobTab;
import ph.txtdis.fx.tab.DisciplineTab;
import ph.txtdis.fx.tab.EducationTab;
import ph.txtdis.fx.tab.FamilyTab;
import ph.txtdis.fx.tab.GovtIdTab;
import ph.txtdis.fx.tab.LeaveLoanTab;
import ph.txtdis.fx.tab.PastWorkTab;
import ph.txtdis.fx.tab.PersonalTab;
import ph.txtdis.fx.tab.Tabbed;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Employee;

public class EmployeeAppImpl extends AbstractIdApp<Employee> implements Searched {

    private EmployeeDTO employee;
    private List<Tab> tabs = new ArrayList<>();
    private PersonalTab personalTab;
    private Tabbed[] tabsWithTables;

    public EmployeeAppImpl() {
        super("Employee", "");
    }

    @Override
    protected void setDTO() {
        dto = employee = App.getContext().getBean(EmployeeDTO.class);
    }

    @Override
    public void refresh() {
        for (Tabbed t : tabsWithTables)
            t.refresh();
        super.refresh();
    }

    @Override
    public void setFocus() {
        Tab tab = personalTab.getTab();
        TabPane tabPane = tab.getTabPane();
        tabPane.getSelectionModel().select(0);
        personalTab.getSurnameField().requestFocus();
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("cancel", new CancelButton<Employee>(this, dto).getButton());
        buttons.put("search", new SearchByTextButton<Employee>(this, dto).getButton());
    }

    @Override
    protected Node[] addVBoxNodes() {
        createTabledTabs();
        addTabs();
        return new Node[] { createTabPane() };
    }

    @Override
    public String setSearchedCriteria() {
        return "given or last name —\nnot both —";
    }

    @Override
    public void listFoundEntities() {
        new FoundEmployeeDialog(this, (EmployeeDTO) dto);
    }

    @Override
    protected void setBindings() {
        for (Tab tab : tabs)
            tab.disableProperty().bind(isSurnameOrNameEmpty());
        buttons.get("cancel").disableProperty().bind(FX.isEmpty(personalTab.getIdField()));
        buttons.get("save").disableProperty().bind(isSurnameOrNameEmpty());
    }

    private void createTabledTabs() {
        personalTab = new PersonalTab(this, employee);
        tabsWithTables = new Tabbed[] { personalTab, new GovtIdTab(this, employee), new FamilyTab(this, employee),
                new EducationTab(this, employee), new PastWorkTab(this, employee), new CurrentJobTab(this, employee),
                new LeaveLoanTab(this, employee), new DisciplineTab(this, employee) };
    }

    private void addTabs() {
        for (Tabbed t : tabsWithTables)
            tabs.add(t.getTab());
    }

    private BooleanBinding isSurnameOrNameEmpty() {
        TextField surname = personalTab.getSurnameField();
        TextField name = personalTab.getNameField();
        return FX.isEmpty(surname).or(FX.isEmpty(name));
    }

    private TabPane createTabPane() {
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
        employee.save();
    }
}
