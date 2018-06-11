/*
 * Copyright (c) 2018 koory1st
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.koory1st.core;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime implements Serializable {
    public final static String PATTERN_1_YYYY = "yyyy";
    public final static String PATTERN_1_MM = "MM";
    public final static String PATTERN_1_DD = "dd";
    public final static String PATTERN_1_YYYYMM = "yyyy-MM";
    public final static String PATTERN_1_YYYYMMDD = "yyyy-MM-dd";
    public final static String PATTERN_1_YYYYMMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public final static String PATTERN_2_YYYYMMDD = "yyyy/MM/dd";
    public final static String PATTERN_2_YYYYMMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    private Calendar c;

    //region 构造函数
    public DateTime() {
        Date date = new Date();
        c = Calendar.getInstance();
        c.setTime(date);
    }

    public DateTime(long milliseconds) {
        c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
    }

    public DateTime(Date date) {
        c = Calendar.getInstance();
        c.setTime(date);
    }

    public DateTime(String dateString, String pattern) throws ParseException {
        Date date = getDate(dateString, pattern);
        c = Calendar.getInstance();
        c.setTime(date);
    }

    public DateTime(String dateString) throws Exception {
        String pattern;

        Date date = null;

        String exceptionString = "无法转换此字符串(%s)";
        exceptionString = String.format(exceptionString, dateString);
        Exception exception = new Exception(exceptionString);

        c = Calendar.getInstance();

        try {
            pattern = PATTERN_1_YYYYMMMDDHHMMSS;
            date = getDate(dateString, pattern);
            c.setTime(date);
            return;
        } catch (ParseException ignored) {
        }

        try {
            pattern = PATTERN_2_YYYYMMMDDHHMMSS;
            date = getDate(dateString, pattern);
            c.setTime(date);
            return;
        } catch (ParseException ignored) {
        }

        try {
            pattern = PATTERN_1_YYYYMMDD;
            date = getDate(dateString, pattern);
            c.setTime(date);
            return;
        } catch (ParseException ignored) {
        }

        try {
            pattern = PATTERN_2_YYYYMMDD;
            date = getDate(dateString, pattern);
            c.setTime(date);
            return;
        } catch (ParseException ignored) {
        }

        throw exception;
    }
    //endregion

    //region 实例方法
    public Date toDate() {
        return c.getTime();
    }

    @Override
    public String toString() {
        Date date = c.getTime();

        String pattern;

        try {
            pattern = PATTERN_1_YYYYMMDD;
            return getString(date, pattern);
        } catch (Exception ignored) {
        }

        try {
            pattern = PATTERN_2_YYYYMMDD;
            return getString(date, pattern);
        } catch (Exception ignored) {
        }

        try {
            pattern = PATTERN_1_YYYYMMMDDHHMMSS;
            return getString(date, pattern);
        } catch (Exception ignored) {
        }

        try {
            pattern = PATTERN_2_YYYYMMMDDHHMMSS;
            return getString(date, pattern);
        } catch (Exception ignored) {
        }

        return "";
    }
    public String toString(String pattern) {
        Date date = c.getTime();
        try {
            return getString(date, pattern);
        } catch (Exception e) {
            return "";
        }
    }

    public Long toTimestamp() {
        return c.getTimeInMillis();
    }
    //endregion

    //region 静态方法
    public static String getNowString() throws Exception {
        String format = PATTERN_1_YYYYMMDD;
        Date date;
        date = new Date();
        return getString(date, format);
    }

    public static String getString(String pattern) throws Exception {
        Date date = new Date();
        return getString(date, pattern);
    }

    public static String getString(DateTime dateTime, String pattern) throws Exception {
        if (dateTime == null) {
            throw new Exception("无法转换null日期");
        }
        Date date = dateTime.toDate();
        return getString(date, pattern);
    }
    public static String getString(DateTime dateTime) throws Exception {
        if (dateTime == null) {
            throw new Exception("无法转换null日期");
        }
        return dateTime.toString();
    }

    public static String getString(Date date, String pattern) throws Exception {
        if(date == null){
            throw new Exception("无法转换null日期");
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getString(Date date) throws Exception {
        if (date == null) {
            throw new Exception("无法转换null日期");
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString();
    }

    public static DateTime getDateTime(String dateString, String pattern) throws java.text.ParseException {
        return new DateTime(getDate(dateString, pattern));
    }

    public static Date getDate(String datetime, String pattern) throws java.text.ParseException {
        SimpleDateFormat fmt = (SimpleDateFormat) DateFormat.getDateInstance();
        fmt.applyPattern(pattern);
        return fmt.parse(datetime);
    }
    //endregion
}
