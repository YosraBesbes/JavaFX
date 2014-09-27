package ph.txtdis.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;

import ph.txtdis.exception.TxtdisException;

public class DIS {

    public final static String PACKAGE = "ph.txtdis.windows.";

    // REPORT OPTIONS
    public final static int ROUTE = 0;
    public final static int STT = 0;

    // NUMBER FORMAT
    public final static DecimalFormat NO_COMMA_DECIMAL = new DecimalFormat("0.00;(0.00)");
    public final static DecimalFormat TWO_PLACE_DECIMAL = new DecimalFormat("#,##0.00;(#,##0.00)");
    public final static DecimalFormat FOUR_PLACE_DECIMAL = new DecimalFormat("#,##0.0000;(#,##0.0000)");

    public final static DecimalFormat INTEGER = new DecimalFormat("#,##0;(#,##0)");
    public final static DecimalFormat NO_COMMA_INTEGER = new DecimalFormat("0;(0)");
    public final static SimpleDateFormat STANDARD_DATE = new SimpleDateFormat("MM/dd/yyyy");
    public final static SimpleDateFormat POSTGRES_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat LONG_DATE = new SimpleDateFormat("MMM dd, yyyy");
    public final static SimpleDateFormat TIME = new SimpleDateFormat("HH:mm");

    // CONSTANTS
    public final static BigDecimal HUNDRED = new BigDecimal(100);

    public static Calendar cal = Calendar.getInstance();

    public final static Time ZERO_TIME = parseTime("00:00");
    public final static Date FAR_FUTURE = parseDate("9999-12-31");
    public final static Date FAR_PAST = parseDate("0001-01-01");

    // DATE INPUT OPTION
    public final static int DATEFROM = 1;
    public final static int DATEFROMTO = 2;
    public final static int DATETO = 3;

    public static boolean isZero(BigDecimal bigDecimal) {
        return bigDecimal == null ? true : bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNegative(BigDecimal bigDecimal) {
        return bigDecimal == null ? false : bigDecimal.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean isNegative(int integer) {
        return integer < 0;
    }

    public static boolean isNegative(String string) {
        return string.startsWith("(") && string.endsWith(")");
    }

    public static boolean isPositive(BigDecimal bigDecimal) {
        return bigDecimal.compareTo(BigDecimal.ZERO) > 0;
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor, 4, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getRate(BigDecimal percent) {
        return DIS.divide(percent, DIS.HUNDRED);
    }

    public static Date addDays(Date date, int amount) {
        return new Date(DateUtils.addDays(date, amount).getTime());
    }

    public static Date addMonths(Date date, int amount) {
        return new Date(DateUtils.addMonths(date, amount).getTime());
    }

    public static Date addYears(Date date, int amount) {
        return new Date(DateUtils.addYears(date, amount).getTime());
    }

    public static int getDayOfWeek(Date date) {
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getFirstOfMonth(Date date) {
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return new Date(cal.getTimeInMillis());
    }

    public static Date getLastOfMonth(Date date) {
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return new Date(cal.getTimeInMillis());
    }

    public static Date getLastOfMonthAdded(Date date, int amount) {
        date = getFirstOfMonth(date);
        date = addMonths(date, amount);
        return getLastOfMonth(date);
    }

    public static boolean isSunday(Date date) {
        return getDayOfWeek(date) == Calendar.SUNDAY ? true : false;
    }

    public static boolean isMonday(Date date) {
        return getDayOfWeek(date) == Calendar.MONDAY ? true : false;
    }

    public static boolean isSaturday(Date date) {
        return getDayOfWeek(date) == Calendar.SATURDAY ? true : false;
    }

    public static Date parseLongDate(String text) {
        try {
            return new Date(DateUtils.truncate(LONG_DATE.parse(text), Calendar.DATE).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String text) {
        try {
            return new Date(DateUtils.truncate(POSTGRES_DATE.parse(text), Calendar.DATE).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Time parseTime(String text) {
        try {
            return new Time(TIME.parse(text).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BigDecimal parseBigDecimal(String text) {
        text = text == null ? "" : text.trim().replace(",", "").replace("(", "-").replace(")", "").replace("₱", "");
        return (text.equals("-") || text.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(text);
    }

    public static int parseInt(String text) {
        return parseBigDecimal(text).intValue();
    }

    public static long parseLong(String text) {
        return parseBigDecimal(text).longValue();
    }

    public static double parseDouble(String text) {
        return parseBigDecimal(text).doubleValue();
    }

    public static String formatDecimal(BigDecimal number) {
        return isZero(number) ? "" : TWO_PLACE_DECIMAL.format(number);
    }

    public static String formatMonetary(BigDecimal number) {
        return "₱" + formatDecimal(number);
    }

    public static String formatInt(int number) {
        return number == 0 ? "" : INTEGER.format(number);
    }

    public static String formatId(int number) {
        return number == 0 ? "" : NO_COMMA_INTEGER.format(number);
    }

    public static String formatLong(long number) {
        return number == 0 ? "" : NO_COMMA_INTEGER.format(number);
    }

    public static String formatQuantity(BigDecimal number) {
        return number == null ? "" : INTEGER.format(number);
    }

    public static String formatPhone(long number) {
        return number == 0 ? "" : "0" + NO_COMMA_INTEGER.format(number);
    }

    public static String format4Place(BigDecimal number) {
        return isZero(number) ? "" : FOUR_PLACE_DECIMAL.format(number);
    }

    public static String formatDate(Date date) {
        return date == null ? "" : LONG_DATE.format(date);
    }

    public static String capitalize(String allCaps) {
        allCaps = WordUtils.capitalizeFully(allCaps, '_');
        return allCaps.replace("_", "");
    }

    public static String getReportClassNameFromOrder(Object object) {
        return object.getClass().getName().replace("Order", "Report");
    }

    public static String getOrderClassNameFromReport(Object object) {
        return object.getClass().getName().replace("Report", "Order");
    }

    public static <T> T instantiateClass(String name) {
        return instantiateClass(name, null, null);
    }

    public static <T> T instantiateClass(String name, Object[] parameters, Class<?>[] parameterTypes) {
        try {
            return instantiateClass(Class.forName(name), parameters, parameterTypes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T instantiateClass(Object object, Object[] parameters, Class<?>[] parameterTypes) {
        return instantiateClass(object.getClass(), parameters, parameterTypes);
    }

    public static <T> T instantiateClass(Class<?> cls) {
        return instantiateClass(cls, null, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T instantiateClass(Class<?> cls, Object[] parameters, Class<?>[] parameterTypes) {
        try {
            Constructor<?> constructor = cls.getConstructor(parameterTypes);
            return (T) constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T invokeMethod(Object object, String name) {
        return invokeMethod(object, name, null, null);
    }

    public static <T> T invokeOneParameterMethod(Object object, String name, Object parameter, Class<?> parameterType) {
        return invokeMethod(object, name, new Object[] { parameter }, new Class<?>[] { parameterType });
    }

    public static <T> T invokeMethod(Object object, String name, Object[] parameters, Class<?>[] parameterTypes) {
        try {
            return invoke(object, name, parameters, parameterTypes);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T invoke(Object object, String name, Object[] parameters, Class<?>[] parameterTypes)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = object.getClass();
        Method method = cls.getMethod(name, parameterTypes);
        return (T) method.invoke(object, parameters);
    }

    public static boolean isEmpty(String string) {
        if (string == null || string.isEmpty())
            return true;
        return false;
    }

    public static boolean hasBeenAssigned(String duplicate, String assignee) throws TxtdisException {
        if (assignee != null)
            throw new TxtdisException(duplicate + "\nis assigned to\n" + assignee);
        return false;
    }

    public static boolean isBefore(Date date, Date latest) {
        return DateUtils.truncatedCompareTo(date, latest, Calendar.DATE) < 0;
    }

    public static String toString(Object object) {
        return object == null ? "" : object.toString();
    }
}
