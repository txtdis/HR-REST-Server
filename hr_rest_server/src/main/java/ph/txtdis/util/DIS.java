package ph.txtdis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

import org.apache.commons.lang3.text.WordUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import javafx.scene.paint.Color;
import ph.txtdis.exception.InvalidException;

public class DIS {

    public static InputStream inputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toBytes(String name) {
        File file = new File(name);
        return toBytes(file);
    }

    public static Path toPath(String name) {
        File file = new File(name);
        return file.toPath();
    }

    public static byte[] toBytes(InputStream is) {
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // NUMBER FORMAT
    public final static DecimalFormat NO_COMMA_DECIMAL = new DecimalFormat(
            "0.00;(0.00)");
    public final static DecimalFormat TWO_PLACE_DECIMAL = new DecimalFormat(
            "#,##0.00;(#,##0.00)");
    public final static DecimalFormat FOUR_PLACE_DECIMAL = new DecimalFormat(
            "#,##0.0000;(#,##0.0000)");

    public final static DecimalFormat INTEGER = new DecimalFormat(
            "#,##0;(#,##0)");
    public final static DecimalFormat NO_COMMA_INTEGER = new DecimalFormat(
            "0;(0)");

    // CONSTANTS
    public final static BigDecimal HUNDRED = new BigDecimal(100);

    // DATE INPUT OPTION
    public final static int DATEFROM = 1;
    public final static int DATEFROMTO = 2;
    public final static int DATETO = 3;

    public static boolean isZero(BigDecimal bigDecimal) {
        return bigDecimal == null ? true
                : bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNegative(BigDecimal bigDecimal) {
        return bigDecimal == null ? false
                : bigDecimal.compareTo(BigDecimal.ZERO) < 0;
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

    public static BigDecimal toPercentRate(BigDecimal percent) {
        return DIS.divide(percent, DIS.HUNDRED);
    }

    public static BigDecimal parseBigDecimal(String text) {
        text = text == null ? ""
                : text.trim().replace(",", "").replace("(", "-").replace(")",
                        "").replace("₱", "");
        return (text.equals("-") || text.isEmpty()) ? BigDecimal.ZERO
                : new BigDecimal(text);
    }

    public static int parseInt(String text) {
        return parseBigDecimal(text).intValue();
    }

    public static Long parseLong(String text) {
        return parseBigDecimal(text).longValue();
    }

    public static double parseDouble(String text) {
        return parseBigDecimal(text).doubleValue();
    }

    public static PhoneNumber parsePhone(String text) {
        try {
            return phoneUtil().parse(text, "PH");
        } catch (NumberParseException e) {
            return null;
        }
    }

    public static String persistPhone(String number) {
        PhoneNumber phone = parsePhone(number);
        return phone == null ? ""
                : phoneUtil().format(phone, PhoneNumberFormat.E164);
    }

    public static String displayPhone(String number) {
        PhoneNumber phone = parsePhone(number);
        return phone == null ? ""
                : phoneUtil().format(phone, PhoneNumberFormat.NATIONAL);
    }

    private static PhoneNumberUtil phoneUtil() {
        return PhoneNumberUtil.getInstance();
    }

    public static String formatDecimal(BigDecimal number) {
        return isZero(number) ? "" : TWO_PLACE_DECIMAL.format(number);
    }

    public static String formatCurrency(BigDecimal number) {
        return "₱" + formatDecimal(number);
    }

    public static String formatInt(int number) {
        return number == 0 ? "" : INTEGER.format(number);
    }

    public static String formatId(Long number) {
        return number == null || number == 0 ? ""
                : NO_COMMA_INTEGER.format(number);
    }

    public static String formatLong(Long number) {
        return number == null || number == 0 ? ""
                : NO_COMMA_INTEGER.format(number);
    }

    public static String formatQuantity(BigDecimal number) {
        return number == null ? "" : INTEGER.format(number);
    }

    public static String formatPhone(PhoneNumber number) {
        return number == null ? ""
                : phoneUtil().formatNumberForMobileDialing(number, "PH", true);
    }

    public static String format4Place(BigDecimal number) {
        return isZero(number) ? "" : FOUR_PLACE_DECIMAL.format(number);
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

    public static <T> T instantiateClass(String name, Object[] parameters,
            Class<?>[] parameterTypes) {
        try {
            return instantiateClass(Class.forName(name), parameters,
                    parameterTypes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T instantiateClass(Object object, Object[] parameters,
            Class<?>[] parameterTypes) {
        return instantiateClass(object.getClass(), parameters, parameterTypes);
    }

    public static <T> T instantiateClass(Class<?> cls) {
        return instantiateClass(cls, null, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T instantiateClass(Class<?> cls, Object[] parameters,
            Class<?>[] parameterTypes) {
        try {
            Constructor<?> constructor = cls.getConstructor(parameterTypes);
            return (T) constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | SecurityException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T invokeMethod(Object object, String name) {
        return invokeMethod(object, name, null, null);
    }

    public static <T> T invokeOneParameterMethod(Object object, String name,
            Object parameter, Class<?> parameterType) {
        return invokeMethod(object, name, new Object[] { parameter },
                new Class<?>[] { parameterType });
    }

    public static <T> T invokeMethod(Object object, String name,
            Object[] parameters, Class<?>[] parameterTypes) {
        try {
            return invoke(object, name, parameters, parameterTypes);
        } catch (NoSuchMethodException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T invoke(Object object, String name, Object[] parameters,
            Class<?>[] parameterTypes) throws NoSuchMethodException,
                    IllegalAccessException, InvocationTargetException {
        Class<?> cls = object.getClass();
        Method method = cls.getMethod(name, parameterTypes);
        return (T) method.invoke(object, parameters);
    }

    public static boolean isEmpty(String string) {
        if (string == null || string.isEmpty())
            return true;
        return false;
    }

    public static boolean hasBeenAssigned(String duplicate, String assignee)
            throws InvalidException {
        if (assignee != null)
            throw new InvalidException(duplicate + "\nis assigned to\n"
                    + assignee);
        return false;
    }

    public static boolean isMobile(PhoneNumber phone) {
        if (phone == null)
            return false;
        return phoneUtil().getNumberType(phone) == PhoneNumberType.MOBILE;
    }

    public static String toString(Object object) {
        return object == null ? "" : object.toString();
    }

    public static String colorToRGBA(Color color) {
        return String.format("rgba(%d, %d, %d, %f)", (int) Math.round(color
                .getRed() * 255), (int) Math.round(color.getGreen() * 255),
                (int) Math.round(color.getBlue() * 255), color.getOpacity());
    }
}
