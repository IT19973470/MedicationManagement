package lk.drugreminder.calculations;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Calculations {

    public static int[] calcNextDueTime(int timeH, int timeM, int hours, int minutes) {
        int[] time = new int[3];
        int day = 0;
        timeM += minutes;
        if (timeM >= 60) {
            timeH++;
            timeM -= 60;
        }

        timeH += hours;

        if (timeH >= 24) {
            day++;
            timeH -= 24;
        }

        time[0] = timeH;
        time[1] = timeM;
        time[2] = day;

        return time;
    }

    public static String pillsEndOn(int pillsCount, int dose, int nextTimeH, int nextTimeM, int intervalH, int intervalM) {
        try {
            pillsCount = pillsCount / dose;
            int days = 0;
            for (int i = 0; i < pillsCount; i++) {
                nextTimeM += intervalM;
                if (nextTimeM >= 60) {
                    nextTimeH++;
                    nextTimeM -= 60;
                }

                nextTimeH += intervalH;

                if (nextTimeH >= 24) {
                    days++;
                    nextTimeH -= 24;
                }
            }

            LocalDateTime localDateTime = LocalDate.now().atTime(nextTimeH, nextTimeM);
            localDateTime = localDateTime.plusDays(days);
            String[] strings = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")).split(" ");

            return strings[0] + " at " + strings[1] + " " + strings[2];
        } catch (ArithmeticException e) {

        }
        return "N/A";
    }

}
