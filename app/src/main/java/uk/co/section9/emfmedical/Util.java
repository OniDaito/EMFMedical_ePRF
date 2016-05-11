package uk.co.section9.emfmedical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by oni on 11/05/2016.
 */
public class Util {

    // this will need to go into a DB util style class
    public static String dateToDBString(Date d){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date dbStringToDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(1970,1,1);
    }

    public static void copyDate(Date from, Date to){
        to.setDate(from.getDate());
        to.setTime(from.getTime());
    }

}
