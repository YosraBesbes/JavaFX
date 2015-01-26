package ph.txtdis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.Type;

import ph.txtdis.util.DIS;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Subselect("select l.id, i.order_date, cm.city_id, r.name, sum(d.qty * qpu.qty / cs.qty) qty from invoicing i "
        + "join customer cm on i.partner_id = cm.id join location l on cm.city_id = l.id "
        + "join invoicing_detail d on i.id = d.invoicing_id join route r on r.id = i.route_id "
        + "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
        + "join qty_per_uom cs on d.item_id = cs.item_id and qpu.uom = 1 "
        + "group by l.id, cm.city_id, i.order_date, r.name ")
@Synchronize({ "invoicing", "invoicing_detail", "item", "location", "route", "qty_per_uom" })
public class InvoicedVolumePerTownByRouteView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Location city;

    private String name;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    @Override
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
