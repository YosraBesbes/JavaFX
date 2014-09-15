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
import ph.txtdis.fx.dialog.FoundEmployeeDialog;
import ph.txtdis.fx.tab.CurrentJobTab;
import ph.txtdis.fx.tab.DisciplineTab;
import ph.txtdis.fx.tab.EducationTab;
import ph.txtdis.fx.tab.FamilyTab;
import ph.txtdis.fx.tab.GovtIdTab;
import ph.txtdis.fx.tab.LeaveLoanTab;
import ph.txtdis.fx.tab.PastWorkTab;
import ph.txtdis.fx.tab.PersonalTab;
import ph.txtdis.fx.tab.Tabled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Employee;

public class EmployeeAppImpl extends AbstractApp<Employee> implements Searched {

    private List<Tab> tabs = new ArrayList<>();
    private PersonalTab personalTab;
    private Tabled[] tabsWithTables;

    public EmployeeAppImpl() {
        super("Employee", "Employee");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(EmployeeDTO.class);
    }

    @Override
    public void refresh() {
        for (Tabled t : tabsWithTables)
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
    protected Node[] addVBoxNodes() {
        tabledTabs();
        tabs();
        return new Node[] { tabPane() };
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
    protected void setDisableBindings() {
        for (Tab tab : tabs)
            tab.disableProperty().bind(isSurnameOrNameEmpty());
        buttons.get("delete").disableProperty().bind(personalTab.getIdField().textProperty().isEmpty());
        buttons.get("save").disableProperty().bind(isSurnameOrNameEmpty());
    }

    private void tabledTabs() {
        EmployeeDTO dto = (EmployeeDTO) this.dto;
        personalTab = new PersonalTab(this, dto);
        tabsWithTables = new Tabled[] { personalTab, new GovtIdTab(this, dto), new FamilyTab(this, dto),
                new EducationTab(this, dto), new PastWorkTab(this, dto), new CurrentJobTab(this, dto),
                new LeaveLoanTab(this, dto), new DisciplineTab(this, dto) };
    }

    private void tabs() {
        for (Tabled t : tabsWithTables)
            tabs.add(t.getTab());
    }

    private BooleanBinding isSurnameOrNameEmpty() {
        TextField surname = personalTab.getSurnameField();
        TextField name = personalTab.getNameField();
        return FX.isEmpty(surname).or(FX.isEmpty(name));
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
    public void save() throws InvalidException {
        for (Tabled t : tabsWithTables)
            t.save();
        super.save();
    }
}
