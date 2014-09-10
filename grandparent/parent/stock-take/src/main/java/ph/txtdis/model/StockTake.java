package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class StockTake extends AbstractAudited {

    private static final long serialVersionUID = 7630222117410458884L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Warehouse warehouse;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser taker;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private SystemUser checker;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate stockTakeDate;

    @OneToMany(mappedBy = "stockTake", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockTakeDetail> details;

    public StockTake() {
    }

    public StockTake(Warehouse warehouse, SystemUser taker, SystemUser checker, LocalDate stockTakeDate) {
        this.warehouse = warehouse;
        this.taker = taker;
        this.checker = checker;
        this.stockTakeDate = stockTakeDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public SystemUser getTaker() {
        return taker;
    }

    public void setTaker(SystemUser taker) {
        this.taker = taker;
    }

    public SystemUser getChecker() {
        return checker;
    }

    public void setChecker(SystemUser checker) {
        this.checker = checker;
    }

    public LocalDate getStockTakeDate() {
        return stockTakeDate;
    }

    public void setStockTakeDate(LocalDate stockTakeDate) {
        this.stockTakeDate = stockTakeDate;
    }

    public List<StockTakeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<StockTakeDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "@" + warehouse + " on " + stockTakeDate;
    }
}
