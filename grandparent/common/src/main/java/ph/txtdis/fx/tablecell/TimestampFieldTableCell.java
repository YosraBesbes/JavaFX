package ph.txtdis.fx.tablecell;

import java.time.ZonedDateTime;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.util.Util;

public class TimestampFieldTableCell<S> extends TextFieldTableCell<S, ZonedDateTime> implements TextStyled {

    public TimestampFieldTableCell() {
        super(new StringConverter<ZonedDateTime>() {

            @Override
            public String toString(ZonedDateTime zdt) {
                return Util.formatZonedDateTime(zdt);
            }

            @Override
            public ZonedDateTime fromString(String text) {
                return Util.parseZonedDateTime(text);
            }
        });
    }

    @Override
    public void updateItem(ZonedDateTime zdt, boolean empty) {
        super.updateItem(zdt, empty);
        if (zdt != null)
            Util.formatZonedDateTime(zdt);
    }
}
