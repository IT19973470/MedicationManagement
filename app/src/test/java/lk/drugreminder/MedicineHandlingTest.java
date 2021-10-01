package lk.drugreminder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MedicineHandlingTest {

    @Test
    public void totalPillsForCards() {
        int pillsInCard = 5, cards = 3;
        assertEquals(15, pillsInCard * cards);
    }

    @Test
    public void totalPillsForBoxes() {
        int pillsInCard = 5, cards = 3, boxes = 2;
        assertEquals(30, pillsInCard * cards * boxes);
    }

    @Test
    public void addMorePills() {
        int currentPills = 30, pillsToAdd = 20;
        int totalPills = currentPills + pillsToAdd;
        assertEquals(50, totalPills);
    }

    @Test
    public void deductPills() {
        int currentPills = 30, pillsToDeduct = 10;
        int totalPills = currentPills - pillsToDeduct;
        assertEquals(20, totalPills);
    }

}
