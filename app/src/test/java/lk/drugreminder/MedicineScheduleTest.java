package lk.drugreminder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedicineScheduleTest {

    @Test
    public void checkMinutesCalculation() {
        int nextTimeM = 30, intervalM = 40;
        nextTimeM += intervalM;
        if (nextTimeM >= 60) {
            nextTimeM -= 60;
        }
        assertEquals(10, nextTimeM);
    }

    @Test
    public void checkHoursCalculation() {
        int nextTimeH = 21, intervalH = 10;
        nextTimeH += intervalH;
        if (nextTimeH >= 24) {
            nextTimeH -= 24;
        }
        assertEquals(7, nextTimeH);
    }

    @Test
    public void calculateNextTime() {
        int nextTimeH = 21, nextTimeM = 30, intervalH = 10, intervalM = 30;
        int days = 0;
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

        assertEquals(8, nextTimeH);
        assertEquals(0, nextTimeM);
        assertEquals(1, days);
    }

}
