package ph.txtdis.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Text {

    public static boolean isEmpty(String string) {
        if (string == null || string.isEmpty())
            return true;
        return false;
    }

    public static String toString(Object object) {
        return object == null ? "" : object.toString();
    }

    public static String capitalize(String uncapped) {
        uncapped = WordUtils.capitalizeFully(uncapped, '_');
        return uncapped.replace("_", "");
    }

    public static String singular(Object o) {
        return StringUtils.uncapitalize(getBaseName(o));
    }

    private static String getBaseName(Object o) {
        return removeService(getName(o));
    }

    private static String getName(Object o) {
        return o.getClass().getSimpleName();
    }

    private static String removeService(String name) {
        return StringUtils.removeEnd(name, "Service");
    }
}
