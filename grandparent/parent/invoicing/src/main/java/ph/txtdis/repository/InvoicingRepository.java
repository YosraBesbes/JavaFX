package ph.txtdis.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ph.txtdis.model.Aging;
import ph.txtdis.model.Booking;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.InvoicingDetail;
import ph.txtdis.model.ProductivityPerFamilyByRoute;
import ph.txtdis.model.Truck;
import ph.txtdis.model.Vat;
import ph.txtdis.model.VolumeByRoute;
import ph.txtdis.model.VolumePerChannelByRoute;
import ph.txtdis.model.VolumePerTownByRoute;
import ph.txtdis.model.VolumeSummary;

public interface InvoicingRepository extends CrudRepository<Invoicing, Integer> {

    @Query("select min(i.id) from Invoicing i")
    int getMinId();

    @Query("select max(i.id) from Invoicing i")
    int getMaxId();

    @Query("select i.details from Invoicing i where i.id = ?1")
    List<InvoicingDetail> getDetails(int id);

    @Query("select max(i.id) from Invoicing i where i.id between ?1 and ?2")
    Integer getBookletLastId(int startId, int endId);

    @Query("select i.id from Invoicing i where i.booking = ?1")
    Integer getIdFromSalesOrder(Booking booking);

    @Query("select i from Invoicing i, Picking p join p.details d "
            + "where i.booking = d.booking and i.orderDate = ?1 and p.truck = ?2 ")
    List<Invoicing> getInvoices(LocalDate date, Truck truck);

    @Query("select min(i.orderDate) from Invoicing i")
    LocalDate getOldestMonth();

    @Query("select max(i.orderDate) from Invoicing i")
    LocalDate getLatestMonth();

    @Query("select new ph.txtdis.model.Vat(i) from Invoicing i where i.orderDate between ?1 and ?2 ")
    List<Vat> getVatList(LocalDate startDate, LocalDate endDate);

    @Query("select new ph.txtdis.model.Aging(c, "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c group by i.partner), "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c and i.day < 1 group by i.partner), "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c "
            + "    and i.day between 1 and 7 group by i.partner), "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c "
            + "    and i.day between 8 and 15 group by i.partner), "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c "
            + "    and i.day between 16 and 30 group by i.partner), "
            + "(select sum(i.balance) from InvoiceView i where i.partner = c and i.day > 30 group by i.partner)) "
            + "   from Customer c where c in (select i.partner from InvoiceView i)")
    List<Aging> getAgingList();

    @Query("    select new ph.txtdis.model.ProductivityPerFamilyByRoute(f, "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'PMS1' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'PMS2' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'PMS3' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S41' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S42' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S43' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S44' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S45' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S46' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S47' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S48' and i.family = f), "
            + "(select count(distinct s.partner) from Invoicing s join s.details d join d.item i join s.route r "
            + "  where s.orderDate between ?1 and ?2 and r.name = 'S49' and i.family = f)) "
            + "   from ItemFamily f order by f.id ")
    List<ProductivityPerFamilyByRoute> getProductivity(LocalDate startDate, LocalDate endDate);

    @Query("select new ph.txtdis.model.VolumeByRoute(s.id, "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS1'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS2'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS3'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S41'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S42'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S43'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S44'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S45'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S46'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S47'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S48'), "
            + "(select sum(s.qty) from InvoicedVolumeByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S49')) from InvoicedVolumeByRouteView s where s.id = 1 ")
    List<VolumeByRoute> getSummary(LocalDate startDate, LocalDate endDate);

    @Query("  select new ph.txtdis.model.VolumePerChannelByRoute(c, "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS1' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS2' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS3' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S41' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S42' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S43' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S44' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S45' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S46' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S47' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S48' and s.channel = c), "
            + "(select sum(s.qty) from InvoicedVolumePerChannelByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S49' and s.channel = c)) from Channel c order by c.id ")
    List<VolumePerChannelByRoute> getPerChannel(LocalDate startDate, LocalDate endDate);

    @Query("  select new ph.txtdis.model.VolumePerTownByRoute(l, "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS1' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS2' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'PMS3' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S41' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S42' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S43' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S44' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S45' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S46' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S47' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S48' and s.city = l), "
            + "(select sum(s.qty) from InvoicedVolumePerTownByRouteView s where s.orderDate between ?1 and ?2 "
            + "    and s.name = 'S49' and s.city = l)) "
            + "   from Location l where l.name in ('QUEZON CITY', 'CALOOCAN', 'MALABON', 'NAVOTAS', 'VALENZUELA CITY') "
            + "  order by l.id ")
    List<VolumePerTownByRoute> getPerTown(LocalDate startDate, LocalDate endDate);

    @Query("  select new ph.txtdis.model.VolumeSummary(s.item, sum(s.qty)) from InvoicedVolumeView s "
            + "where s.orderDate between ?1 and ?2 group by s.item ")
    List<VolumeSummary> getPerItem(LocalDate startDate, LocalDate endDate);

    List<Invoicing> findByOrderDateOrderByIdAsc(LocalDate date);
}
