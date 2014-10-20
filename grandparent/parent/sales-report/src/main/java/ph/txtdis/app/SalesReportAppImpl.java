package ph.txtdis.app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.SalesReportDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.DateRangeButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.tab.MultiTabled;
import ph.txtdis.fx.tab.PerChannelTab;
import ph.txtdis.fx.tab.PerItemTab;
import ph.txtdis.fx.tab.PerTownTab;
import ph.txtdis.fx.tab.ProductivityTab;
import ph.txtdis.fx.tab.SummaryTab;
import ph.txtdis.fx.tab.Tabbed;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.util.Util;

public class SalesReportAppImpl extends AbstractApp<VolumeSummary, Integer> implements Excel {

    private List<Tab> tabs;
    private Map<String, Tabbed> tabMap;
    private TabPane tabPane;
    private SalesReportDTO dto;

    public SalesReportAppImpl() {
        super("Sales Report", "");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(SalesReportDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
        buttons.put("back", new BackButton(this, dto).getButton());
        buttons.put("open", new DateRangeButton(this, dto).getButton());
        buttons.put("next", new NextButton(this, dto).getButton());
        buttons.put("excel", new ExcelButton(this).getButton());
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
        tabMap.put("productive", new ProductivityTab(this, dto));
        tabMap.put("summary", new SummaryTab(this, dto));
        tabMap.put("channel", new PerChannelTab(this, dto));
        tabMap.put("town", new PerTownTab(this, dto));
        tabMap.put("item", new PerItemTab(this, dto));
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
    public void refresh() {
        super.refresh();
    }

    @Override
    public void setFocus() {
    }

    @Override
    public void save() {
    }

    @Override
    protected void setBindings() {
    }

    @Override
    protected void addFooter(VBox box) {
    }

    @Override
    public void saveAsExcel() {
        List<List<TableView<?>>> tables = createTables();
        new ExcelWriter(tables, createExcelFilename(), createExcelSheetNames());
    }

    private List<List<TableView<?>>> createTables() {
        List<List<TableView<?>>> tables = new ArrayList<>();
        tables.add(getTables("productive"));
        tables.add(getTables("summary"));
        tables.add(getTables("channel"));
        tables.add(getTables("town"));
        tables.add(getTables("item"));
        return tables;
    }

    private String createExcelFilename() {
        return Util.dateToFileName(dto.getStartDate()) + " - " + Util.dateToFileName(dto.getEndDate()) + "." + module;
    }

    private String[] createExcelSheetNames() {
        List<String> names = new ArrayList<>();
        names.add("Productivity");
        names.add("Total Sales to Trade");
        names.add("Volume per Channel");
        names.add("Quantity per Town");
        names.add("Amount per Item");
        return names.toArray(new String[names.size()]);
    }

    private List<TableView<?>> getTables(String name) {
        return ((MultiTabled) tabMap.get(name)).getTables();
    }
}
