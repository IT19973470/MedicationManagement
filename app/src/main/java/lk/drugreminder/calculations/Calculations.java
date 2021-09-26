package lk.drugreminder.calculations;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Calculations {

    //calculate the next schedule time
    public static int[] calcNextDueTime(int timeH, int timeM, int hours, int minutes) {
        int[] time = new int[2];
        timeM += minutes;
        if (timeM >= 60) {
            timeH++;
            timeM -= 60;
        }

        timeH += hours;

        if (timeH >= 24) {
            timeH -= 24;
        }

        time[0] = timeH;
        time[1] = timeM;

        return time;
    }

    //calculate remaining pills
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
            String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
            String[] formattedDateTimes = formattedDateTime.split(" ");
            return formattedDateTimes[0] + " at " + formattedDateTimes[1] + " " + formattedDateTimes[2];
        } catch (ArithmeticException e) {

        }
        return "N/A";
    }

}
