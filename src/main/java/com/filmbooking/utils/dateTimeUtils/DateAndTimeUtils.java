package com.filmbooking.utils.dateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateAndTimeUtils {

    public static String addTime(String hourAndMinute, int additionMinutes) {

        int hours = Integer.parseInt(hourAndMinute.split(":")[0]);
        int minutes = Integer.parseInt(hourAndMinute.split(":")[1]);

        int totalMinutes = hours * 60 + minutes + additionMinutes;

        String hoursString = "";
        String minuteString = "";

        if (totalMinutes / 60 < 10) hoursString = "0" + String.valueOf(totalMinutes / 60);
        else
            hoursString = String.valueOf(totalMinutes / 60);
        if (totalMinutes % 60 < 10) minuteString = "0" + String.valueOf(totalMinutes % 60);
        else
            minuteString = String.valueOf(totalMinutes % 60);

        return hoursString + ":" + minuteString;

    }

    public static boolean isDateTimeBefore(String fromDateTime, String toDateTime) {
        SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("hh:mm dd/mm/yyyy");


        try {
            Date fromDate = simpleDateTimeFormat.parse(fromDateTime);
            Date toDate = simpleDateTimeFormat.parse(toDateTime);

            return fromDate.before(toDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return a String of Date and Time with Format "hh:mm dd/mm/yyyy"
     */
    public static String getCurrentDateTime() {
        LocalDateTime current = LocalDateTime.now();
        String currentTime = current.getHour() + ":" + current.getMinute();
        String currentDate = current.getDayOfMonth() + "/" + current.getMonthValue() + "/" + current.getYear();


        return currentTime + " " + currentDate;
    }



    public static void main(String[] args) {
        System.out.println(DateAndTimeUtils.addTime("9:45", 15));
        System.out.println(DateAndTimeUtils.isDateTimeBefore("14:18 29/09/2023", "00:00 26/09/2023"));
        System.out.println(DateAndTimeUtils.getCurrentDateTime());
        System.out.println(DateAndTimeUtils.isDateTimeBefore("00:00 26/09/2023", DateAndTimeUtils.getCurrentDateTime()));;
    }

}
