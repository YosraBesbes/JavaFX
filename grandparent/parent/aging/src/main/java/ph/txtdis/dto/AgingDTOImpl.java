package ph.txtdis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Aging;
import ph.txtdis.service.AgingService;

@Component
public class AgingDTOImpl implements AgingDTO {

    @Autowired
    private AgingService service;

    private List<Aging> agingList;

    @Override
    public ObservableList<Aging> getAgingList() {
        agingList = new ArrayList<>();
        for (Aging aging : service.getAgingList())
            agingList.add(aging);
        return FXCollections.observableList(agingList);
    }

    @Override
    public BigDecimal[] getTotals() {
        BigDecimal[] totals = new BigDecimal[6];
        for (int i = 0; i < totals.length; i++)
            totals[i] = BigDecimal.ZERO;
        for (Aging aging : agingList) {
            totals[0] = totals[0].add(aging.getTotalValue());
            totals[1] = totals[1].add(aging.getCurrentValue());
            totals[2] = totals[2].add(aging.getOneToSevenValue());
            totals[3] = totals[3].add(aging.getEightToFifteenValue());
            totals[4] = totals[4].add(aging.getSixteenToThirtyValue());
            totals[5] = totals[5].add(aging.getGreaterThanThirtyValue());
        }
        return totals;
    }
}
