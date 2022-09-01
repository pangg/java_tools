package com.xxx.example.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class DateFormatDemo {
    /**
     * 使用SimpleDateFormat(“HH-mm-ss a”)构造函数和SimpleDateFormat类的sdf.format(date)方法格式化时间。
     */
    @Test
    public void dateTimeAmPm() {
        Date date = new Date();
        String strDateFormat = "HH:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
    }

    /**
     * 使用Calender类的Calender.getInstance()方法和Formatter类的fmt.format()方法来显示(MMM)格式的月份名称
     */
    @Test
    public void dateTimeMonth() {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        fmt.format("%tB %tb %tm", cal, cal, cal);
        System.out.println(fmt);

        System.out.println("-----------------");

        SimpleDateFormat f = new SimpleDateFormat("MMM");
        SimpleDateFormat f1 = new SimpleDateFormat("dd");
        SimpleDateFormat f2 = new SimpleDateFormat("a");
        int h;
        if (Calendar.getInstance().get(Calendar.HOUR) == 0) {
            h = 12;
        } else {
            h = Calendar.getInstance().get(Calendar.HOUR);
        }
        String filename = "Current Date is :" + f.format(new Date()) + f1.format(new Date()) + "日" +
                + h + "时" + f2.format(new Date());
        System.out.println(filename);
    }

    /**
     * 使用Calender类的Calender.getInstance()来显示某个时刻的小时和分钟
     */
    @Test
    public void dateTimeHrMin() {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        fmt.format("%tl:%tM", cal, cal);
        System.out.println(fmt);

        System.out.println("--------------------");

        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
    }

    /**
     * 使用Formatter类的fmt.format()方法和Calendar类的Calendar.getInstance()方法来显示当前日期和时间
     */
    @Test
    public void dateTimeDatetime() {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        fmt.format("%tc", cal);
        System.out.println(fmt);

        // 中国时间格式
        Date d = new Date();
        SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        System.out.println(simpDate.format(d));

        Date d1 = new Date();
        System.out.println(d1.toString());
    }

    /**
     * 使用SimpleDateFormat类的sdf.format(date)方法将时间格式化为24小时格式(00:00-24:00)
     */
    @Test
    public void formatTimeIn24Hour() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h");
        System.out.println("hour in h format : " + sdf.format(date));

        Date d2 = new Date();
        SimpleDateFormat simpDate2 = new SimpleDateFormat("kk:mm:ss");
        System.out.println(simpDate2.format(d2));

        Date d3 = new Date();
        SimpleDateFormat simpDate3 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        System.out.println(simpDate3.format(d3));
    }

    /**
     * 使用SimpleDateFormat('MMMM')构造函数和SimpleDateFormat类的sdf.format(date)方法来格式化月份
     */
    @Test
    public void formattingMonths() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        System.out
                .println("Current Month in MMMM format : " + sdf.format(date));

        // 示例-2
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM");
        String s = formatter.format(new Date());
        System.out.println(s);

    }

    /**
     * 使用SimpleDateFormat类的SimpleDateFormat('ss')构造函数和sdf.format(date)方法格式化秒数
     */
    @Test
    public void formattingSeconds() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        System.out.println("seconds is ss format : " + sdf.format(date));
    }

    /**
     * 使用DateFormatSymbols().DateFormatSymbols类的getShortMonths()方法，本示例显示了几个月的简写名称
     */
    @Test
    public void displayingMonthsShortFormat() {
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        for (int i = 0; i < shortMonths.length - 1; i++) {
            String shortMonth = shortMonths[i];
            System.out.println("shortMonth = " + shortMonth);
        }

        System.out.println("---------------");

        String str1 = "yyyy-MM-dd";
        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(str1, Locale.FRENCH);
        System.out.println(sdf.format(d));
        sdf = new SimpleDateFormat(str1, Locale.ENGLISH);
        System.out.println(sdf.format(d));
    }

    /**
     * 使用DateFormatSymbols().DateFormatSymbols类的getWeekdays()方法来显示工作日(周一至周五)的简称
     */
    @Test
    public void displayingWeekdays() {
        String[] weekdays = new DateFormatSymbols().getWeekdays();
        for (int i = 2; i < weekdays.length - 1; i++) {
            String weekday = weekdays[i];
            System.out.println("weekday = " + weekday);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", Locale.US);
        String str = dateFormat.format(date);
        System.out.println(str);
    }

    /**
     * 使用Calender类的add()方法添加时间到一个日期
     */
    @Test
    public void addTime2Date() {
        Date d1 = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(d1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr = format.format(d1);

        System.out.println("当前时间 : " + DateToStr);

        cl.setTime(d1);
        cl.add(Calendar.MONTH, 1);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr2 = format2.format(cl.getTime());
        System.out.println("加上一个月后的时间为： " + DateToStr2);

        cl.setTime(d1);
        cl.add(Calendar.HOUR, 70);
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr3 = format3.format(cl.getTime());
        System.out.println("加上70个小时后的时间： " + DateToStr3);

        cl.setTime(d1);
        cl.add(Calendar.YEAR, 3);
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr4 = format4.format(cl.getTime());
        System.out.println("加上3年后的时间： " + DateToStr4);
    }

    /**
     * 使用Locale类和DateFormat类来显示不同国家格式的日期
     */
    @Test
    public void displayTimeCountrysFormat() {
        Date d1 = new Date();
        System.out.println("today is : " + d1.toString());
        Locale locItalian = new Locale("it", "ch");
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locItalian);
        System.out
                .println("today is in Italian Language  in Switzerland Format : "
                        + df.format(d1));
    }

    /**
     * 显示不同语言的时间
     */
    @Test
    public void languageDate() {
        Date d1 = new Date();
        System.out.println("today is : " + d1.toString());
        Locale loc = new Locale("zh");
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, loc);
        System.out.println("today is : " + df.format(d1));
    }

    /**
     * 使用calender类的roll()方法滚动月(不改变年)或小时(不更改月或年)
     */
    @Test
    public void rollHoursMonths() {
        Date curDate = new Date();
        Calendar cl = Calendar.getInstance();

        cl.setTime(curDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr = format.format(cl.getTime());
        System.out.println("当前时间：" + DateToStr);

        // 2个月后
        cl.setTime(curDate);
        cl.roll(Calendar.MONTH, 2);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr2 = format2.format(cl.getTime());
        System.out.println("当前时间加上2个月后的时间：" + DateToStr2);

        // 10个小时后的时间
        cl.setTime(curDate);
        cl.roll(Calendar.HOUR, 10);
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr3 = format3.format(cl.getTime());
        System.out.println("当前时间加上10小时后的时间：" + DateToStr3);
    }

    @Test
    public void rollHoursMonths2() {
        Calendar cal = Calendar.getInstance();
        System.out.println("Time:" + cal.getTime());

        cal.roll(Calendar.YEAR, false);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr = format.format(cal.getTime());
        System.out.println("向上滚下1年：" + DateToStr);


        cal.roll(Calendar.HOUR, true);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String DateToStr2 = format2.format(cal.getTime());
        System.out.println("向上滚动1小时: " + DateToStr2);
    }

    /**
     * 显示年份和月份的第几周
     */
    @Test
    public void displayWeekNumber() {
        Date d1 = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(d1);

        System.out.println("today is " + cl.WEEK_OF_YEAR + " week of the year");
        System.out.println("today is a " + cl.DAY_OF_MONTH
                + "month of the year");
        System.out.println("today is a " + cl.WEEK_OF_MONTH
                + "week of the month");

        Calendar cal = Calendar.getInstance();
        System.out.println("Current week of month is : "
                + cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Current week of year is : "
                + cal.get(Calendar.WEEK_OF_YEAR));
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        System.out.println("date after one week : "
                + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE)
                + "-" + cal.get(Calendar.YEAR));
    }

    /**
     * 使用DateFormatSymbols().DateFormatSymbols类的getWeekdays()方法来显示时间的格式
     */
    @Test
    public void displayDateDifferentFormats() {
        Date dt = new Date(1662019091000L);
        DateFormat[] dtformat = new DateFormat[6];

        dtformat[0] = DateFormat.getInstance();
        dtformat[1] = DateFormat.getDateInstance();
        dtformat[2] = DateFormat.getDateInstance(DateFormat.MEDIUM);
        dtformat[3] = DateFormat.getDateInstance(DateFormat.FULL);
        dtformat[4] = DateFormat.getDateInstance(DateFormat.LONG);
        dtformat[5] = DateFormat.getDateInstance(DateFormat.SHORT);

        for (DateFormat dateform : dtformat)
            System.out.println(dateform.format(dt));
    }

    @Test
    public void displayDateDifferentFormats2() {
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        String DateToStr = format.format(curDate);
        System.out.println(DateToStr);

        format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        DateToStr = format.format(curDate);
        System.out.println(DateToStr);

        format = new SimpleDateFormat("dd MMMM yyyy zzzz", Locale.ENGLISH);
        DateToStr = format.format(curDate);
        System.out.println(DateToStr);

        format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        DateToStr = format.format(curDate);
        System.out.println(DateToStr);

        try {
            Date strToDate = format.parse(DateToStr);
            System.out.println(strToDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
