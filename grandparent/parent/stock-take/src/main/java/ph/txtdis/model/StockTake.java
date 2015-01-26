package ph.txtdis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class StockTake extends AbstractAudited {

    private static final long serialVersionUID = 7630222117410458884L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Warehouse warehouse;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users taker;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users checker;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate stockTakeDate;

    @OneToMany(mappedBy = "stockTake", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockTakeDetail> details;

    public StockTake(Warehouse warehouse, Users taker, Users checker, LocalDate stockTakeDate) {
        this.warehouse = warehouse;
        this.taker = taker;
        this.checker = checker;
        this.stockTakeDate = stockTakeDate;
    }

    @Override
    public String toString() {
        return "@" + warehouse + " on " + stockTakeDate;
    }
}
