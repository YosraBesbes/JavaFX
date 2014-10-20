package ph.txtdis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import ph.txtdis.model.Vat;

public interface VatDTO extends DateRanged, Spun {

    LocalDate getDate();

    void setDate(LocalDate date);

    ObservableList<Vat> getVatList(LocalDate startDate, LocalDate endDate);

    BigDecimal getTotalVat();
}
