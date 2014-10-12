package ph.txtdis.fx.tablecell;

import java.time.LocalDate;

import javafx.geometry.Pos;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.util.Util;

public class DateDisplayTableCell<E> extends DisplayTableCell<E, LocalDate> implements TextStyled {

    public DateDisplayTableCell() {
        super(Pos.CENTER);
    }

    @Override
    protected String setString(LocalDate date) {
        return Util.formatDate(date);
    }
}
