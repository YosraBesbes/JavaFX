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

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.Type;

import ph.txtdis.util.DIS;

@Entity
@Subselect("select ch.id, i.order_date, ch.id, r.name, sum(d.qty * qpu.qty / cs.qty) qty from invoicing i "
        + "join customer cm on i.partner_id = cm.id join channel ch on cm.channel_id = ch.id "
        + "join invoicing_detail d on i.id = d.invoicing_id join route r on i.route_id = r.id "
        + "join qty_per_uom qpu on d.item_id = qpu.item_id and qpu.uom = d.uom "
        + "join qty_per_uom cs on d.item_id = cs.item_id and qpu.uom = 1 group by ch.id, i.order_date, r.name ")
@Synchronize({ "invoicing", "invoicing_detail", "item", "channel", "route", "qty_per_uom" })
public class InvoicedVolumePerChannelByRouteView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate orderDate;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Channel channel;

    private String name;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal qty;

    protected InvoicedVolumePerChannelByRouteView() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getRoute() {
        return name;
    }

    public BigDecimal getQty() {
        return qty;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((channel == null) ? 0 : channel.hashCode());
        result = prime * result + id;
        result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoicedVolumePerChannelByRouteView other = (InvoicedVolumePerChannelByRouteView) obj;
        if (channel == null) {
            if (other.channel != null)
                return false;
        } else if (!channel.equals(other.channel))
            return false;
        if (id != other.id)
            return false;
        if (orderDate == null) {
            if (other.orderDate != null)
                return false;
        } else if (!orderDate.equals(other.orderDate))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return DIS.formatQuantity(qty);
    }
}
