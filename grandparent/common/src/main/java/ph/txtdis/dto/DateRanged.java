package ph.txtdis.dto;

import java.time.LocalDate;

public interface DateRanged {

    LocalDate getStartDate();

    void setStartDate(LocalDate date);

    LocalDate getEndDate();

    void setEndDate(LocalDate date);
}
