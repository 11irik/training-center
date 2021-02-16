package com.kirill.trainingCenter.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateHelper {
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;
    private static final int WEEK_WORKING_DAYS = 5;

    public static LocalDateTime getEndDate(LocalDateTime startDate, Integer durationInHours, Integer workingTimeFrom, Integer workingTimeTo) {
        int courseStartHours = startDate.getHour();
        int workingHours = workingTimeTo - workingTimeFrom;

        int hoursLeft = durationInHours;
        int nonWorkingDays = 0;

        LocalDateTime endDate = startDate;

        //first week
        //first day
        int firstDayHours = workingHours;
        if (isDayOff(startDate)) {
            endDate = endDate.plusDays(8 - startDate.getDayOfWeek().getValue()); //todo comment
        } else {
            if (courseStartHours > workingTimeFrom && courseStartHours < workingTimeTo) {
                firstDayHours = workingTimeTo - courseStartHours;
            } else {
                if (courseStartHours > workingTimeTo) {
                    endDate = endDate.plusDays(1); //skip this day
                }
            }
        }
        hoursLeft -= firstDayHours;

        if (hoursLeft <= 0) {
            hoursLeft = durationInHours;
            if (courseStartHours < workingTimeTo && courseStartHours > workingTimeFrom) {
                hoursLeft += courseStartHours - workingTimeFrom;
            }
        } else {
            endDate = endDate.plusDays(1); //first day count
            int fullDays = hoursLeft / workingHours;
            hoursLeft %= workingHours;
            int workingWeeks = fullDays / WEEK_WORKING_DAYS;
            int workingDaysFirstWeek = fullDays % WEEK_WORKING_DAYS;

            if (endDate.getDayOfWeek().getValue() + workingDaysFirstWeek >= 7 || endDate.getDayOfWeek().getValue() == SATURDAY) { //todo comment
                nonWorkingDays += 2;
            }

            nonWorkingDays += workingWeeks * 2; //weekends count
            endDate = endDate.plusDays(nonWorkingDays + fullDays);
        }

        endDate = endDate.with(LocalTime.of(workingTimeFrom + hoursLeft, 0));
        return endDate;
    }
    
    public static boolean isDayOff(LocalDateTime localDateTime) {
        if (localDateTime.getDayOfWeek().getValue() == SATURDAY || localDateTime.getDayOfWeek().getValue() == SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
}
