package ph.txtdis.app;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.AgingDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.display.CurrencyDisplay;
import ph.txtdis.fx.table.AgingTable;
import ph.txtdis.model.Aging;
import ph.txtdis.model.Vat;

public class AgingAppImpl extends AbstractApp<Vat, Integer> implements Excel {

    private CurrencyDisplay totalDisplay, currentDisplay, oneToSevenDisplay, eightToFifteenDisplay,
            sixteenToThirtyDisplay, greaterThanThirtyDisplay;
    private TableView<Aging> table;
    private AgingDTO dto;

    public AgingAppImpl() {
        super("Aging Account Receivables", "");
    }

    @Override
    protected void setDTO() {
        dto = App.context().getBean(AgingDTO.class);
    }

    @Override
    protected void setButtons() {
        buttons = new LinkedHashMap<>();
        buttons.put("excel", new ExcelButton(this).getButton());
    }

    @Override
    protected Node[] addVBoxNodes() {
        return new Node[] { new VBox(setVatTableHBox()) };
    }

    public HBox setVatTableHBox() {
        HBox hBox = new HBox(setVatTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setVatTableVBox() {
        return new VBox(setVatTable(), setTotalVatHBox());
    }

    private TableView<Aging> setVatTable() {
        table = new AgingTable(this).getTable();
        setTableData();
        return table;
    }

    private void setTableData() {
        table.setItems(dto.getAgingList());
        table.setId(module);
        table.setUserData(dto.getTotals());
    }

    private HBox setTotalVatHBox() {
        HBox hBox = new HBox(new Label("Total"), setTotalDisplayHBox());
        setHBoxProperties(hBox);
        return hBox;
    }

    private HBox setTotalDisplayHBox() {
        BigDecimal[] totals = (BigDecimal[]) table.getUserData();
        totalDisplay = new CurrencyDisplay(totals[0]);
        currentDisplay = new CurrencyDisplay(totals[1]);
        oneToSevenDisplay = new CurrencyDisplay(totals[2]);
        eightToFifteenDisplay = new CurrencyDisplay(totals[3]);
        sixteenToThirtyDisplay = new CurrencyDisplay(totals[4]);
        greaterThanThirtyDisplay = new CurrencyDisplay(totals[5]);
        return new HBox(totalDisplay, currentDisplay, oneToSevenDisplay, eightToFifteenDisplay, sixteenToThirtyDisplay,
                greaterThanThirtyDisplay);
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public void refresh() {
        setTableData();
        totalDisplay.setValue((BigDecimal) table.getUserData());
        super.refresh();
    }

    @Override
    public void setFocus() {
        table.requestFocus();
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
        new ExcelWriter(Arrays.asList(Arrays.asList(table)), module, table.getId().replace("/", "-"));
    }
}
