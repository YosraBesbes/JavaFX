package ph.txtdis.model;

import java.math.BigDecimal;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockTakeReconciliationFilteredDetail {

    private ReadOnlyObjectWrapper<Item> item;

    private ReadOnlyObjectWrapper<Quality> quality;

    private ReadOnlyObjectWrapper<BigDecimal> systemQty, countQty;

    private ObjectBinding<BigDecimal> finalQty, varianceQty;

    private SimpleObjectProperty<BigDecimal> adjustmentQty;

    private SimpleStringProperty justification;

    public StockTakeReconciliationFilteredDetail(Item item, Quality quality, BigDecimal system,
            BigDecimal stockTakeQty, BigDecimal countAdjustQty, String justification) {
        this.item = new ReadOnlyObjectWrapper<>(item);
        this.quality = new ReadOnlyObjectWrapper<>(quality);
        this.systemQty = new ReadOnlyObjectWrapper<>(system);
        this.justification = new SimpleStringProperty(justification);
        countQty = new ReadOnlyObjectWrapper<>(stockTakeQty);
        adjustmentQty = new SimpleObjectProperty<>(countAdjustQty);
        finalQty = new ObjectBinding<BigDecimal>() {
            {
                bind(countQty, adjustmentQty);
            }

            @Override
            protected BigDecimal computeValue() {
                if (countQty.get() == null || adjustmentQty.get() == null)
                    return null;
                return countQty.get().add(adjustmentQty.get());
            }
        };

        varianceQty = new ObjectBinding<BigDecimal>() {
            {
                bind(systemQty, finalQty);
            }

            @Override
            protected BigDecimal computeValue() {
                if (systemQty.get() == null || finalQty.get() == null)
                    return null;
                return finalQty.get().subtract(systemQty.get());
            }
        };
    }

    public Integer getItemId() {
        return getItem() == null ? null : getItem().getId();
    }

    public Item getItem() {
        return item.get();
    }

    public Quality getQualityType() {
        return quality.get();
    }

    public BigDecimal getSystemQty() {
        return systemQty.get();
    }

    public BigDecimal getCountQty() {
        return countQty.get();
    }

    public BigDecimal getVarianceQty() {
        return varianceQty.get();
    }

    public BigDecimal getAdjustmentQty() {
        return adjustmentQty.get();
    }

    public void setAdjustmentQty(BigDecimal adjustmentQty) {
        this.adjustmentQty.set(adjustmentQty);
    }

    public BigDecimal getFinalQty() {
        return finalQty.get();
    }

    public String getJustification() {
        return justification.get();
    }

    public void setJustification(String justification) {
        this.justification.set(justification);
    }
}
