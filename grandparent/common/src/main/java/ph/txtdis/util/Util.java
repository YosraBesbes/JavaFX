package ph.txtdis.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.paint.Color;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;

public class Util {

    public static Date localToSqlDate(LocalDate localDate) {
        return localDate == null ? null : new Date(java.util.Date.from(
                localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
    }

    public static Byte[] inputStreamToBytes(InputStream inputStream) {
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return ArrayUtils.toObject(bytes);
        } catch (IOException e) {
            return null;
        }
    }

    public static String colorToRGBA(Color color) {
        return String.format("rgba(%d, %d, %d, %f)", (int) Math.round(color.getRed() * 255),
                (int) Math.round(color.getGreen() * 255), (int) Math.round(color.getBlue() * 255), color.getOpacity());
    }

    public static String getModule(Object object) {
        return object.getClass().getSimpleName().replace("App", "").replace("Impl", "");
    }

    public static <E> String getEntityIdAndName(Apped app, Audited<E> dto) {
        return getModule(app) + " No. " + dto.getId() + ": \n" + dto.get();
    }

    public static String formatDate(LocalDate date) {
        return date == null ? "" : date.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }

    public static String formatZonedDateTime(ZonedDateTime zdt) {
        return zdt == null ? "" : zdt.format(DateTimeFormatter.ofPattern("M/d/yyyy h:mma"));
    }
}
