package ph.txtdis.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import ph.txtdis.App;
import ph.txtdis.dto.Spun;
import ph.txtdis.dto.SummaryDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.MailButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.button.OpenByDateButton;
import ph.txtdis.fx.button.SaveButton;
import ph.txtdis.fx.dialog.LatestTruckRelatedTransactionClosureOptionDialog;
import ph.txtdis.fx.tab.ConsolidationTab;
import ph.txtdis.fx.tab.LoadSettlementTab;
import ph.txtdis.fx.tab.Mapped;
import ph.txtdis.fx.tab.MultiTabled;
import ph.txtdis.fx.tab.RemittanceSettlementTab;
import ph.txtdis.fx.tab.SettlementTab;
import ph.txtdis.fx.tab.SummaryTab;
import ph.txtdis.fx.tab.Tabbed;
import ph.txtdis.fx.tab.TabledPerTruck;
import ph.txtdis.mail.ApprovedByMail;
import ph.txtdis.mail.CannotCheckMailException;
import ph.txtdis.mail.Mail;
import ph.txtdis.mail.MailNotSentException;
import ph.txtdis.model.DailySummary;
import ph.txtdis.type.UserType;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class DayEndAppImpl extends AbstractApp<DailySummary, LocalDate> implements ApprovedByMail, Excel, SettlementTab {

    private List<Tab> tabs;
    private Map<String, Tabbed> tabMap;
    private SimpleObjectProperty<UserType> userType;
    private SummaryDTO summary;
    private TabPane tabPane;

    public DayEndAppImpl() {
        super("Day-End Report", "");
    }

    @Override
    public void start() {
        super.start();
        checkForTransactionClosure();
        setMaximized(true);
    }

    private void checkForTransactionClosure() {
        if (isAnyOpen().get())
            askForTransactionClosure();
    }

    private void askForTransactionClosure() {
        if (new LatestTruckRelatedTransactionClosureOptionDialog(this, dto.getId()).isAffirmative())
            refresh();
        else
            close();
    }

    @Override
    protected void setDTO() {
        dto = summary = App.getContext().getBean(SummaryDTO.class);
    }

    @Override
    public void refresh() {
        tabMap.forEach((string, tabbed) -> tabbed.refresh());
        super.refresh();
    }

    @Override
    public void setFocus() {
        tabPane.getSelectionModel().select(getEnabledTab());
    }

    private Tab getEnabledTab() {
        Tab tab = tabMap.get("main").getTab();
        return tab.isDisabled() ? tabMap.get("load").getTab() : tab;
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("back", new BackButton(this, (Spun) dto).getButton());
        buttons.put("open", new OpenByDateButton<DailySummary>(this, dto).getButton());
        buttons.put("next", new NextButton(this, (Spun) dto).getButton());
        buttons.put("save", new SaveButton<DailySummary, LocalDate>(this, dto).getButton());
        buttons.put("excel", new ExcelButton(this).getButton());
        buttons.put("mail", new MailButton<DailySummary, LocalDate>(this, dto).getButton());
    }

    @Override
    protected Node[] addVBoxNodes() {
        setTabMap();
        setTabs();
        setTabPane();
        return new Node[] { tabPane };
    }

    private void setTabMap() {
        tabMap = new LinkedHashMap<>();
        tabMap.put("main", new ConsolidationTab(this, summary));
        tabMap.put("summary", new SummaryTab(this, summary));
        tabMap.put("load", new LoadSettlementTab(this, summary));
        tabMap.put("remittance", new RemittanceSettlementTab(this, summary));
    }

    private void setTabs() {
        tabs = new ArrayList<>();
        tabMap.forEach((string, tabbed) -> tabs.add(tabbed.getTab()));
    }

    private void setTabPane() {
        tabPane = new TabPane();
        tabPane.setStyle("-fx-tab-min-width: 80;");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(tabs);
    }

    @Override
    protected void setBindings() {
        userType = new SimpleObjectProperty<>(Login.user().getType());
        buttons.get("save").disableProperty().bind(isAllReconciled().or(isAnyOpen()));
        buttons.get("excel").disableProperty().bind(isAllReconciled().not());
        buttons.get("mail").disableProperty()
                .bind(isAllReconciled().not().or(isConfirmed()).or(userType.isEqualTo(UserType.MANAGER)));
        tabMap.get("main").getTab().disableProperty().bind(isAllReconciled().not());
        tabMap.get("summary").getTab().disableProperty().bind(isAllReconciled().not());
    }

    private BooleanBinding isConfirmed() {
        return ((ConsolidationTab) tabMap.get("main")).isConfirmed();
    }

    @Override
    public BooleanBinding isAllReconciled() {
        return isAllReconciled("load").and(isAllReconciled("remittance"));
    }

    private BooleanBinding isAllReconciled(String tabId) {
        return ((SettlementTab) tabMap.get(tabId)).isAllReconciled();
    }

    @Override
    public BooleanBinding isAnyOpen() {
        return isAnyOpen("load").or(isAnyOpen("remittance"));
    }

    private BooleanBinding isAnyOpen(String tabId) {
        return ((SettlementTab) tabMap.get(tabId)).isAnyOpen();
    }

    @Override
    public boolean isDataReadyToBeSaved() {
        return false;
    }

    @Override
    public void save() throws InvalidException {
        tabMap.get("load").save();
        tabMap.get("remittance").save();
        tabMap.get("main").save();
    }

    @Override
    public void saveAsExcel() {
        List<List<TableView<?>>> tables = new ArrayList<>();
        tables.add(getConsolidatedTables());
        addSummaryTables(tables);
        tables.add(getLoadTables());
        tables.add(getRemittanceTables());
        new ExcelWriter(tables, createExcelFilename(), createExcelSheetNames());
    }

    private String createExcelFilename() {
        return Util.formatDate(dto.getId()).replace("/", "-") + " " + module;
    }

    private String[] createExcelSheetNames() {
        List<String> names = new ArrayList<>();
        names.add("Consolidated");
        addSummaryNames(names);
        names.add("Load Reconciliation");
        names.add("Remittance Settlement");
        return names.toArray(new String[names.size()]);
    }

    private void addSummaryNames(List<String> names) {
        for (Tabbed tabbed : getSummaryMap().values())
            names.add(tabbed.getTab().getId() + " Summary");
    }

    private List<TableView<?>> getConsolidatedTables() {
        return ((MultiTabled) tabMap.get("main")).getTables();
    }

    private void addSummaryTables(List<List<TableView<?>>> tables) {
        for (Tabbed tabbed : getSummaryMap().values())
            tables.add(((MultiTabled) tabbed).getTables());
    }

    private Map<String, Tabbed> getSummaryMap() {
        return getSummaryMappedTab().getTabMap();
    }

    private Mapped getSummaryMappedTab() {
        return (Mapped) tabMap.get("summary");
    }

    private List<TableView<?>> getLoadTables() {
        List<TableView<?>> tables = new ArrayList<>();
        for (Tabbed tabbed : getLoadCollection())
            tables.add(((TabledPerTruck) tabbed).getTable());
        return tables;
    }

    private Collection<Tabbed> getLoadCollection() {
        return getLoadMap().values();
    }

    private Map<String, Tabbed> getLoadMap() {
        return getLoadMappedTab().getTabMap();
    }

    private Mapped getLoadMappedTab() {
        return (Mapped) tabMap.get("load");
    }

    private List<TableView<?>> getRemittanceTables() {
        List<TableView<?>> tables = new ArrayList<>();
        for (Tabbed tabbed : getRemittanceMap().values())
            tables.add(((TabledPerTruck) tabbed).getTable());
        return tables;
    }

    private Map<String, Tabbed> getRemittanceMap() {
        return getRemittanceMappedTab().getTabMap();
    }

    private Mapped getRemittanceMappedTab() {
        return (Mapped) tabMap.get("remittance");
    }

    @Override
    public Mail checkMail() throws CannotCheckMailException {
        return ((ApprovedByMail) tabMap.get("main")).checkMail();
    }

    @Override
    public void sendMail() throws MailNotSentException {
    }

    @Override
    public void handleCheckingResult(Mail mail) {
    }
}
