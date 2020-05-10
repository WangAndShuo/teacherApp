package com.classproject.teacherapp.util;


import java.text.ParseException;
import java.text.ParsePosition;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description: 时间工具类
 * <p>
 * create by: deng_sg@suixingpay.com
 * create time: 2020-02-18 15:02
 * <p>
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
public class DateUtil {
    /**
     * 年
     */
    public static final String DATE_YYYY = "yyyy";
    /**
     * 月
     */
    public static final String DATE_MM = "MM";
    /**
     * 日
     */
    public static final String DATE_DD = "dd";
    /**
     * 年-月-日
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 月-日
     */
    public static final String MM_DD = "MM-dd";
    /**
     * 年月日时分秒
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * 年-月-日 时:分:秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String ABBR_SHORT_DATE_FORMAT_STR = "yyyyMMdd";

    /**
     * 按照参数格式化日期
     *
     * @Author: li_tu@suixingpay.com
     * @Date: 2019-07-13 16:41
     * @Version: 1.0
     */
    public static String formatDateByParam(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 使用参数Format格式化Date成字符串
     *
     * @return String
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @author: litu[li_tu@suixingpay.com]
     * @date: 2018年11月5日 下午2:38:56
     * @version: V1.0
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat dateTime = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return dateTime.format(new Date());
    }

    /**
     * @author: litu[li_tu@suixingpay.com]
     * @date: 2018年11月5日 下午2:38:56
     * @version: V1.0
     */
    public static Long getTimeStamp(String timeStamp) {
        return new Date(new Long(timeStamp)).getTime();
    }



    /**
     * 增加、减少指定月数
     *
     * @param date
     * @param month 要增加的月数（减少则为 负数）
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * 月首
     *
     * @param date
     * @return
     */
    public static Date monthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String formatDefaultTime(Date date) {
        return new SimpleDateFormat(YYYYMMDDHHMMSS).format(date);
    }

    /**
     * 校验字符串是否符合格式 zhou_gc
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean checkValidDate(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
            return false;
        }
        dateStr = StringUtils.deleteWhitespace(dateStr);
        pattern = StringUtils.deleteWhitespace(pattern);
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);//严格进行参数匹配，不进行自动日期转换
            format.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 交易时间
     * @param str
     * @param dateFormat
     * @return
     */
    public static boolean isValidDateTime(String str,String dateFormat) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01

            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /*
     *   检查日期相差是否为12个月，区间范围为[....) zhou_gc
     * */

    public static boolean checkDateInOneYear(String beginMonth, String endMonth, String pattern) {

        if (StringUtils.isBlank(beginMonth) || StringUtils.isBlank(endMonth) || StringUtils.isBlank(pattern)) {
            return false;
        }
        beginMonth = StringUtils.deleteWhitespace(beginMonth);
        endMonth = StringUtils.deleteWhitespace(endMonth);
        pattern = StringUtils.deleteWhitespace(pattern);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            Date beginT = sdf.parse(beginMonth);
            Date endT = sdf.parse(endMonth);
            if (beginT.equals(endT)) {
                return true;
            }
            if (!beginT.before(endT)) {
                return false;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginT);
            calendar.add(Calendar.YEAR, 1);
            Date tmp = calendar.getTime();
            if (endT.before(tmp)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 获取两个日期间的月数（待改进）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int amountMonths(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        boolean rev = false; // 翻转（是否 startDate > endDate）
        Date d1 = startDate;
        Date d2 = endDate;
        if (startDate.getTime() > endDate.getTime()) {
            rev = true;
            d1 = endDate;
            d2 = startDate;
        }
        c1.setTime(d1);
        c2.setTime(d2);
        // count of year
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int cy = year2 - year1;
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int ret;
        if (cy > 0) {
            ret = (cy - 1) * 12;
            ret += month2;
            ret += (12 - month1);
        } else {
            ret = month2 - month1;
        }
        return rev ? (-1 * ret) : ret;
    }


    /**
     * 从字符串解析为日期型
     *
     * @param s
     * @param fmt
     * @return
     */
    public static Date parse(String s, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 转换到字符串
     *
     * @param date
     * @param fmt 日期格式化字符串
     * @return
     */
    public static String toString(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }


}



