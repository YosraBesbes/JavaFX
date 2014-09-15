package ph.txtdis.app;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ph.txtdis.App;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.dialog.FoundItemDialog;
import ph.txtdis.fx.tab.ItemTab;
import ph.txtdis.fx.tab.PricingTab;
import ph.txtdis.fx.tab.Tabled;
import ph.txtdis.fx.tab.VolumeDiscountTab;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.Item;
import ph.txtdis.type.ItemType;

public class ItemAppImpl extends AbstractApp<Item> implements Searched {

    private List<Tab> tabs = new ArrayList<>();
    private ItemTab itemTab;
    private PricingTab pricingTab;
    private VolumeDiscountTab discountTab;
    private Tabled[] tabsWithTables;

    public ItemAppImpl() {
        super("Item", "Item");
    }

    @Override
    protected void setDTO() {
        dto = App.getContext().getBean(ItemDTO.class);
    }

    @Override
    public void setFocus() {
        itemTab.getTab().getTabPane().getSelectionModel().select(0);
    }

    @Override
    protected Node[] addVBoxNodes() {
        tabledTabs();
        tabs();
        return new Node[] { tabPane() };
    }

    @Override
    protected void setDisableBindings() {
        pricingTab
                .getTab()
                .disableProperty()
                .bind(noSelectedType().or(isMonetary()).or(isFree()).or(isPurchasedButNoQtyPerUom())
                        .or(isAssembledButNoBOM()));
        discountTab.getTab().disableProperty().bind(FX.isEmpty(pricingTab.getTable()));
        buttons.get("delete").setDisable(true);
        buttons.get("save")
                .disableProperty()
                .bind(FX.isEmpty(itemTab.getIdField()).not().or(noSelectedType())
                        .or(noPricing().and(isNotMonetaryAndFree())));
    }

    private BooleanBinding noSelectedType() {
        return FX.isEmpty(getTypeCombo());
    }

    private BooleanBinding isMonetary() {
        return FX.is(getTypeCombo(), ItemType.MONETARY);
    }

    private BooleanBinding isFree() {
        return FX.is(getTypeCombo(), ItemType.FREE);
    }

    private BooleanBinding isPurchasedButNoQtyPerUom() {
        return FX.is(getTypeCombo(), ItemType.PURCHASED).and(FX.isEmpty(itemTab.getQtyPerUomTable()));
    }

    private BooleanBinding isAssembledButNoBOM() {
        return FX.isEmpty(itemTab.getBomTable()).and(
                FX.is(getTypeCombo(), ItemType.BUNDLED).or(FX.is(getTypeCombo(), ItemType.MADE))
                        .or(FX.is(getTypeCombo(), ItemType.REPACKED)));
    }

    private BooleanBinding noPricing() {
        return FX.isEmpty(pricingTab.getTable());
    }

    private BooleanBinding isNotMonetaryAndFree() {
        return FX.is(getTypeCombo(), ItemType.PURCHASED).or(
                FX.is(getTypeCombo(), ItemType.BUNDLED).or(
                        FX.is(getTypeCombo(), ItemType.REPACKED).or(FX.is(getTypeCombo(), ItemType.MADE))));
    }

    private ComboBox<ItemType> getTypeCombo() {
        return itemTab.getTypeCombo();
    }

    private void tabledTabs() {
        ItemDTO dto = (ItemDTO) this.dto;
        itemTab = new ItemTab(this, dto);
        pricingTab = new PricingTab(this, dto);
        discountTab = new VolumeDiscountTab(this, dto);
        tabsWithTables = new Tabled[] { itemTab, pricingTab, discountTab };
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
    public void save() throws InvalidException {
        for (Tabled t : tabsWithTables)
            t.save();
        super.save();
    }

    @Override
    public void refresh() {
        for (Tabled t : tabsWithTables)
            t.refresh();
        super.refresh();
    }

    @Override
    public String setSearchedCriteria() {
        return "description";
    }

    @Override
    public void listFoundEntities() {
        new FoundItemDialog(this, (ItemDTO) dto);
    }
}
