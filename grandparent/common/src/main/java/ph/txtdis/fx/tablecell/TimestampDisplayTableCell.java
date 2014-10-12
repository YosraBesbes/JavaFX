package ph.txtdis.fx.tablecell;

import java.time.ZonedDateTime;

import javafx.geometry.Pos;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.util.Util;

public class TimestampDisplayTableCell<E> extends DisplayTableCell<E, ZonedDateTime> implements TextStyled {

    public TimestampDisplayTableCell() {
        super(Pos.CENTER);
    }

    @Override
    protected String setString(ZonedDateTime zdt) {
        return Util.formatZonedDateTime(zdt);
    }
}
