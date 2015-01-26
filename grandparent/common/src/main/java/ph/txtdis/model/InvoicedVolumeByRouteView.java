package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.Type;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("select 1 id, i.order_date, r.name, sum(d.qty * qpu.qty / cs.qty) qty from invoicing i "
        + "join invoicing_detail d on i.id = d.invoicing_id join route r on i.route_id = r.id "
        + "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
        + "join qty_per_uom cs on d.item_id = cs.item_id and qpu.uom = 1 group by id, i.order_date, r.name ")
@Synchronize({ "invoicing", "invoicing_detail", "item", "route", "qty_per_uom" })
public class InvoicedVolumeByRouteView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    private String name;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;
}
