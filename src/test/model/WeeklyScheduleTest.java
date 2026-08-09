package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.exception.NameErrorException;

@ExcludeFromJacocoGeneratedReport
public class WeeklyScheduleTest {
    private WeeklySchedule testWeeklySchedule;
    private TimeBlock testTimeBlock;
    private ShortTerm testShortTerm;
    
    @BeforeEach
    public void setup() {
        testWeeklySchedule = new WeeklySchedule();
        testTimeBlock = new TimeBlock();
        testShortTerm = new ShortTerm("My short term");
    }

    @Test
    public void testAddTimeBlock() {
        assertTrue(testWeeklySchedule.getTimeBlocks().isEmpty());
        testWeeklySchedule.addTimeBlock(testTimeBlock);
        assertEquals(1, testWeeklySchedule.getTimeBlocks().size());
        assertTrue(testWeeklySchedule.getTimeBlocks().contains(testTimeBlock));
    }

    @Test
    public void testGetTasksAtEnergyLevel() throws NameErrorException {
        testShortTerm.addTask("task1");
        testShortTerm.addTask("task2");
        testShortTerm.getTasks().get(0).setEnergyLevel(1);
        testShortTerm.getTasks().get(1).setEnergyLevel(3);
        testTimeBlock.setEnergyLevel(1);
        assertEquals("task1", testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm).get(0).getName());
        assertEquals(1, testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm).size());
        testTimeBlock.setEnergyLevel(2);
        assertNull(testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm));
    }

   
}
