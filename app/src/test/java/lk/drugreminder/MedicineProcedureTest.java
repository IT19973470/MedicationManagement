package lk.drugreminder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MedicineProcedureTest {

    @Test
    public void calculateTookPills() {
        int totalPills = 5, tookPills = 0;
        for (int i = 0; i < totalPills; i++) {
            tookPills++;
        }
        assertEquals(5, tookPills);
    }

    @Test
    public void calculateTookAndMissedPills() {
        int totalPills = 5, tookPills = 0, missedPills = 0;
        boolean pillsTook = true;
        for (int i = 0; i < totalPills; i++) {
            if (pillsTook) {
                tookPills++;
            } else {
                missedPills++;
            }
        }
        assertEquals(5, tookPills);
        assertEquals(0, missedPills);
    }

}
