package Global;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormat {
    public static final String PATTERN="yyyy/MM/dd";
    private static  SimpleDateFormat format = new SimpleDateFormat(PATTERN);

    /**
     * Retourne une date sous forme de string
     * @param date
     * @return
     */
    public static String toString(Date date){
        return format.format(date);
    }
}
