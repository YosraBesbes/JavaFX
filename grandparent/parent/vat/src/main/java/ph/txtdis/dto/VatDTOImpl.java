package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.Vat;
import ph.txtdis.service.VatService;
import ph.txtdis.util.Util;

@Component
public class VatDTOImpl implements VatDTO {

    @Autowired
    private VatService service;

    @Autowired
    private Vat vat;

    private List<Vat> vatList;

    private LocalDate startDate, endDate;

    @Override
    public void back() {
        if (getStartDate().isEqual(getOldest()))
            setDateIfUnequal(getLatest());
        else
            vat.setDate(getPrevious());
    }

    @Override
    public void next() {
        if (getStartDate().isEqual(getLatest()))
            setDateIfUnequal(getOldest());
        else
            vat.setDate(getNext());
    }

    private LocalDate getOldest() {
        return service.getOldestMonth();
    }

    private LocalDate getLatest() {
        return service.getLatestMonth();
    }

    private LocalDate getPrevious() {
        return getStartDate().minusMonths(1L);
    }

    private LocalDate getNext() {
        return getStartDate().plusMonths(1L);
    }

    private void setDateIfUnequal(LocalDate date) {
        if (!getStartDate().isEqual(date))
            vat.setDate(date);
    }

    @Override
    public LocalDate getDate() {
        return vat == null ? null : vat.getDate();
    }

    @Override
    public void setDate(LocalDate date) {
    }

    @Override
    public LocalDate getStartDate() {
        return startDate == null ? Util.startOfMonth(getDate()) : startDate;
    }

    @Override
    public void setStartDate(LocalDate date) {
        startDate = date;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate == null ? Util.endOfMonth(getDate()) : endDate;
    }

    @Override
    public void setEndDate(LocalDate date) {
        endDate = date;
    }

    @Override
    public ObservableList<Vat> getVatList(LocalDate startDate, LocalDate endDate) {
        vatList = service.getVatList(startDate, endDate);
        return FXCollections.observableList(vatList);
    }

    @Override
    public BigDecimal getTotalVat() {
        BigDecimal total = BigDecimal.ZERO;
        for (Vat vat : vatList)
            total = total.add(vat.getVatValue());
        return total;
    }
}
