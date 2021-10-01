package lk.drugreminder;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DiseaseManagementTest {

    @Test
    public void testSchedulesForEachTake() {
        int pillsCount = 8, dose = 3;
        try {
            pillsCount = pillsCount / dose;
            assertEquals(2, pillsCount);
        } catch (ArithmeticException e) {
            assertEquals(8, pillsCount);
        }
    }

    @Test
    public void testPrintingTime() {
        //Today is 2021-10-01
        LocalDateTime localDateTime = LocalDate.now().atTime(20, 35);
        assertEquals("2021-10-01 08:35 PM", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
    }

    @Test
    public void testAddDaysToGivenDateTime() {
        //Today is 2021-10-01
        LocalDateTime localDateTime = LocalDate.now().atTime(20, 35);
        localDateTime = localDateTime.plusDays(1);
        assertEquals("2021-10-02 08:35 PM", localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
    }

    @Test
    public void testPrintingFormattedDateAndTime() {
        String dateTimePrev = "2021-10-02 08:35 PM";
        String[] strings = dateTimePrev.split(" ");
        String dateTime = strings[0] + " at " + strings[1] + " " + strings[2];
        assertEquals("2021-10-02 at 08:35 PM", dateTime);
    }
}
