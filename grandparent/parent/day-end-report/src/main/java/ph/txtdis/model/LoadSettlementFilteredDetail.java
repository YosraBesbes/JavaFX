package ph.txtdis.model;

import java.math.BigDecimal;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoadSettlementFilteredDetail {

    private ReadOnlyObjectWrapper<Item> item;

    private ReadOnlyObjectWrapper<BigDecimal> pickedQty, soldQty, returnedQty;

    private ObjectBinding<BigDecimal> varianceQty;

    private SimpleObjectProperty<BigDecimal> adjustmentQty;

    private SimpleStringProperty actionTaken;

    public LoadSettlementFilteredDetail(Item item, BigDecimal picked, BigDecimal sold, BigDecimal returned,
            BigDecimal adjustment, String actionTaken) {
        this.item = new ReadOnlyObjectWrapper<>(item);
        this.actionTaken = new SimpleStringProperty(actionTaken);
        pickedQty = new ReadOnlyObjectWrapper<>(picked);
        soldQty = new ReadOnlyObjectWrapper<>(sold);
        returnedQty = new ReadOnlyObjectWrapper<>(returned);
        adjustmentQty = new SimpleObjectProperty<>(adjustment);
        varianceQty = new ObjectBinding<BigDecimal>() {
            {
                bind(pickedQty, returnedQty, soldQty, adjustmentQty);
            }

            @Override
            protected BigDecimal computeValue() {
                return soldQty.get().add(returnedQty.get()).subtract(pickedQty.get()).add(adjustmentQty.get());
            }
        };
    }

    public Integer getItemId() {
        return getItem() == null ? null : getItem().getId();
    }

    public Item getItem() {
        return item.get();
    }

    public BigDecimal getPickedQty() {
        return pickedQty.get();
    }

    public BigDecimal getSoldQty() {
        return soldQty.get();
    }

    public BigDecimal getReturnedQty() {
        return returnedQty.get();
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

    public String getActionTaken() {
        return actionTaken.get();
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken.set(actionTaken);
    }
}
