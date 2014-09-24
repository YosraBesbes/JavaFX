package ph.txtdis.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.txtdis.model.StockTake;
import ph.txtdis.model.StockTakeDetail;
import ph.txtdis.repository.StockTakeRepository;
import ph.txtdis.util.TransactionStamp;
import ph.txtdis.util.Util;

@Service
@Transactional()
public class StockTakeServiceImpl extends AbstractService<StockTake, Integer> implements StockTakeService {

    @Autowired
    private StockTakeRepository repository;

    protected StockTakeServiceImpl() {
    }

    @Override
    public Integer getMinId() {
        return repository.getMinId();
    }

    @Override
    public Integer getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public LocalDate getLatestDate() {
        return repository.getLatestDate();
    }

    @Override
    public List<StockTakeDetail> getDetails(int id) {
        return repository.getDetails(id);
    }

    @Override
    public String getStockTakeAfter(LocalDate date) {
        TransactionStamp stamp = repository.getCutoffStampOfStockTakeAfter(date);
        return stamp == null ? null : Util.formatDate(stamp.getid()) + " stock take, whose cutoff was set by\n"
                + stamp;
    }

    @Override
    public String getOneTransactionAfter(LocalDate date) {
        Integer id = repository.getOnePickingIdAfter(date);
        return id != null ? pickListNo(id) : receivingNo(date);
    }

    private String pickListNo(Integer id) {
        return "P/List No. " + id;
    }

    private String receivingNo(LocalDate date) {
        Integer id = repository.getOneReceivingIdAfter(date);
        return id == null ? null : ("R/R No. " + id);
    }

    @Override
    public String getClosureStamp(LocalDate date) {
        TransactionStamp stamp = repository.getClosureStamp(date);
        return stamp == null ? null : Util.formatDate(date) + " stock take has been closed by\n" + stamp;
    }

    @Override
    public String getOnGoingStockTake(LocalDate date) {
        LocalDate openDate = repository.getOpenStockTakeBefore(date);
        return openDate == null ? null : "A prior stock take,\ndated " + Util.formatDate(openDate)
                + ",\nis still open ";
    }

    @Override
    public LocalDate getLatestCutoffDate() {
        ZonedDateTime zdt = repository.getLatestCutoffDate();
        return zdt == null ? null : zdt.toLocalDate();
    }
}
