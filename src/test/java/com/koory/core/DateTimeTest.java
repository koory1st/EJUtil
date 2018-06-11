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

package com.koory.core;

import com.koory1st.core.DateTime;

import java.text.ParseException;
import java.util.Date;

public class DateTimeTest {

    public static void main(String[] args) throws Exception {
        System.out.println("DateTimeTest start ====");

        DateTime now;
        Date nowDate;
        Long timestamp;
        String outputString;
        Date outputDate;
        DateTime outputDateTime;

        now = new DateTime();
        System.out.println("测试 DateTime dateTime = new DateTime()   " + now.toString());

        nowDate = new Date();
        now = new DateTime(nowDate);
        System.out.println("测试 DateTime dateTime = new DateTime(Date date)   " + now.toString());

        nowDate = new Date();
        timestamp = nowDate.getTime();
        now = new DateTime(timestamp);
        System.out.println("测试 DateTime dateTime = new DateTime(long timestamp)    " + now.toString());

        try {
            now = new DateTime("2010/05/05", "yyyy/MM/dd");
            System.out.println("测试 DateTime dateTime = new DateTime(String str, String pattern)    " + now.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            now = new DateTime("2010/05/05");
            System.out.println("测试 DateTime dateTime = new DateTime(String str)     " + now.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            now = new DateTime("2010-05-05 13:26:41");
            System.out.println("测试 DateTime dateTime = new DateTime(String str)     " + now.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        now = new DateTime();
        outputString = now.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("测试 dateTime.toString(pattern)   " + outputString);

        now = new DateTime();
        outputString = now.toString();
        System.out.println("测试 dateTime.toString()   " + outputString);

        now = new DateTime();
        nowDate = now.toDate();
        outputString = DateTime.getString(nowDate, "yyyy-MM-dd");
        System.out.println("测试 dateTime.toDate()   " + outputString);

        now = new DateTime();
        timestamp = now.toTimestamp();
        System.out.println("测试 dateTime.toDate()   " + timestamp);

        nowDate = now.toDate();
        outputString = DateTime.getString(nowDate, "yyyy-MM-dd");
        System.out.println("测试 DateTime.getString(Date date, String pattern)   " + outputString);

        nowDate = now.toDate();
        outputString = DateTime.getString(nowDate);
        System.out.println("测试 DateTime.getString(Date date)    " + outputString);

        nowDate = DateTime.getDate("2018-08-08 14:52:33", "yyyy-MM-dd");
        outputString = DateTime.getString(nowDate);
        System.out.println("测试 DateTime.getDate(String str, String pattern)    " + outputString);

//
//        outputString = DateTime.getString("yyyy-MM-dd");
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_YYYY);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_MM);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_DD);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_YYYYMM);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_YYYYMMDD);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString(DateTime.PATTERN_1_YYYYMMMDDHHMMSS);
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getString("");
//        System.out.println("  " + outputString);
//
//        outputString = DateTime.getNowString();
//        System.out.println("  " + outputString);
//
//        try {
//            outputDate = DateTime.getDate("2014-05-07", DateTime.PATTERN_1_YYYYMM);
//            System.out.println("outputDate=" + outputDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            String aa = new DateTime().toString();
//            System.out.println("aa=" + aa.toString());
//            outputDateTime = DateTime.getDateTime("2014-05-07", DateTime.PATTERN_1_YYYYMM);
//            System.out.println("outputDate=" + outputDateTime.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        System.out.println("DateTimeTest end ====");
    }
}
