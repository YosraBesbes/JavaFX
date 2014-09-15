package ph.txtdis.service;

import java.time.LocalDate;

public interface SpunByDate {

    LocalDate getOldestDate();

    LocalDate getLatestDate();
}
