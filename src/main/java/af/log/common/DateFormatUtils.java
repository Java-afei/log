package af.log.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 飞
 */
public class DateFormatUtils {

    private static final String patten="yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前格式化的时间
     * @return
     */
    public static String getFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    /**
     * 获取指定毫秒值的格式化时间
     * @param time
     * @return
     */
    public static String getFormat(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patten);
        String format = simpleDateFormat.format(time);
        return format;
    }

    /**
     * 获取今天起始的格式化时间，已弃用
     * @return
     */
    @Deprecated
    public static String getDayStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long time = calendar.getTime().getTime();
        return getFormat(time);
    }

    /**
     * 获取今天结束的格式化时间，已弃用
     * @return
     */
    @Deprecated
    public static String getDayEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        long time = calendar.getTime().getTime();
        return getFormat(time);
    }

    /**
     * 获取今天的开始时间==> 2023/4/6 00:00:00
     * @return
     */
    public static String getDayStartTime(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime time = LocalDateTime.of(localDate, LocalTime.MIN);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(patten);
        String format = time.format(pattern);
        return format;
    }

    /**
     * 获取今天起始的毫秒值
     * @return
     */
    public static long getDayStartTimeMillis(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime time = LocalDateTime.of(localDate, LocalTime.MIN);
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 获取今天的结束时间==> 2023/4/6 23:59:59
     * @return
     */
    public static String getDayEndTime(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime time = LocalDateTime.of(localDate, LocalTime.MAX);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(patten);
        String format = time.format(pattern);
        return format;
    }

    /**
     * 获取今天结束的毫秒值
     * @return
     */
    public static long getDayEndTimeMillis(){
        LocalDate localDate = LocalDate.now();
        LocalDateTime time = LocalDateTime.of(localDate, LocalTime.MAX);
        return time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


}
