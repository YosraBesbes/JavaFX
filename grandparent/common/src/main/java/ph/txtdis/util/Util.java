package ph.txtdis.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.scene.paint.Color;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import ph.txtdis.app.Apped;
import ph.txtdis.dto.Audited;

public class Util {

    public static Date localToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
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

    public static String dateToFileName(LocalDate date) {
        return date == null ? "" : date.format(DateTimeFormatter.ofPattern("M.d.yyyy"));
    }

    public static String formatZonedDateTime(ZonedDateTime zdt) {
        return zdt == null ? "" : zdt.format(timestampFormat());
    }

    public static String zdtToFileName(ZonedDateTime zdt) {
        return zdt == null ? "" : zdt.format(timestampFileFormat());
    }

    private static DateTimeFormatter timestampFormat() {
        return DateTimeFormatter.ofPattern("M/d/yyyy h:mma");
    }

    private static DateTimeFormatter timestampFileFormat() {
        return DateTimeFormatter.ofPattern("M.d.yyyy-h.mm.a");
    }

    public static ZonedDateTime parseZonedDateTime(String zdt) {
        return zdt == null ? null : ZonedDateTime.parse(zdt, timestampFormat());
    }

    public static ZonedDateTime startOfDay(LocalDate date) {
        return date == null ? null : date.atStartOfDay(ZoneId.systemDefault());
    }

    public static ZonedDateTime endOfDay(LocalDate date) {
        return date == null ? null : date.plusDays(1L).atStartOfDay(ZoneId.systemDefault());
    }

    public static LocalDate startOfMonth(LocalDate date) {
        if (date == null)
            date = LocalDate.now();
        return LocalDate.of(date.getYear(), date.getMonthValue(), 1);
    }

    public static LocalDate endOfMonth(LocalDate date) {
        return startOfMonth(date).plusMonths(1L).minusDays(1L);
    }

    public static BigDecimal toBigDecimal(Double qty) {
        return qty == null ? null : new BigDecimal(qty);
    }
}
