package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TimeBlockTest {
    private Task task1;
    private TimeBlock testTimeBlock;

    @BeforeEach
    public void runbefore() {
        testTimeBlock = new TimeBlock();
        task1 = new Task("task1");
    }

    @Test
    public void testSetTimePeriod() {
        assertEquals(0, testTimeBlock.getTimePeriod());
        testTimeBlock.setTimePeriod(3);
        assertEquals(3, testTimeBlock.getTimePeriod());
    }

    @Test
    public void testAddTask() {
        assertEquals(0, testTimeBlock.getTasks().size());
        testTimeBlock.addTask(task1);
        assertEquals(1, testTimeBlock.getTasks().size());
        assertEquals(task1, testTimeBlock.getTasks().get(0));
    }

    @Test
    public void testSetEnergyLevel() {
        assertEquals(0, testTimeBlock.getEnergyLevel());
        testTimeBlock.setEnergyLevel(2);
        assertEquals(2, testTimeBlock.getEnergyLevel());
    }

    @Test
    public void testSetDayOfWeek() {
        assertEquals(1, testTimeBlock.getDayOfWeek());
        testTimeBlock.setDayOfWeek(5);
        assertEquals(5, testTimeBlock.getDayOfWeek());
    }
}
