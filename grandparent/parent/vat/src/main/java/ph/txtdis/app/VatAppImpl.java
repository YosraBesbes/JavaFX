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
import ph.txtdis.dto.VatDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.DateRangeButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.display.CurrencyDisplay;
import ph.txtdis.fx.table.VatTable;
import ph.txtdis.model.Vat;
import ph.txtdis.util.Util;

public class VatAppImpl extends AbstractApp<Vat, Integer> implements Excel {

    private CurrencyDisplay totalVatDisplay;
    private Label label;
    private TableView<Vat> table;
    private VatDTO dto;

    public VatAppImpl() {
        super("Value-Added Tax", "");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(VatDTO.class);
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
        return new Node[] { new VBox(setVatTableHBox()) };
    }

    public HBox setVatTableHBox() {
        HBox hBox = new HBox(setVatTableVBox());
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private VBox setVatTableVBox() {
        setVatTable();
        return new VBox(setTableLabel(), table, setTotalVatHBox());
    }

    private TableView<Vat> setVatTable() {
        table = new VatTable(this).getTable();
        setTableData();
        return table;
    }

    private void setTableData() {
        table.setItems(dto.getVatList(dto.getStartDate(), dto.getEndDate()));
        table.setId(Util.formatDate(dto.getStartDate()) + " - " + Util.formatDate(dto.getEndDate()));
        table.setUserData(dto.getTotalVat());
    }

    private HBox setTotalVatHBox() {
        HBox hBox = new HBox(new Label("Total"), setTotalVatDisplay());
        setHBoxProperties(hBox);
        return hBox;
    }

    private CurrencyDisplay setTotalVatDisplay() {
        totalVatDisplay = new CurrencyDisplay((BigDecimal) table.getUserData());
        return totalVatDisplay;
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER_RIGHT);
    }

    private Label setTableLabel() {
        label = new Label(table.getId());
        label.setStyle("-fx-font-size: 16pt;");
        return label;
    }

    @Override
    public void refresh() {
        setTableData();
        label.setText(table.getId());
        totalVatDisplay.setValue((BigDecimal) table.getUserData());
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
