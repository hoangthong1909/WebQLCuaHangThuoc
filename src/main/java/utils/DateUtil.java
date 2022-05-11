package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addDays(Date date, int days){
        //date.setTime(date.getTime()+days*24*60*60*1000);
        //setTime gán cho biến date 1 mốc thời gian được chuyển từ milisecon (long)
        //getTime chuyển mốc thời gian của biến date thành milisecon (long)
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,days);
        return cal.getTime();
    }
}
