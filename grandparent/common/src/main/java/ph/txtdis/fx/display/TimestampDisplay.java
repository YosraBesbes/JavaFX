package ph.txtdis.fx.display;

import java.time.ZonedDateTime;

import javafx.scene.control.TextField;
import ph.txtdis.util.Util;

public class TimestampDisplay extends TextField {

    public TimestampDisplay(ZonedDateTime zdt) {
        setTimestamp(zdt);
        setMaxWidth(160);
        setEditable(false);
        focusTraversableProperty().set(false);
    }

    public void setTimestamp(ZonedDateTime zdt) {
        setText(zdt == null ? "" : Util.formatZonedDateTime(zdt));
    }
}
