package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeeklyScheduleTest {
    private WeeklySchedule testWeeklySchedule;
    private TimeBlock testTimeBlock;
    private Task task1;
    private Task task2;
    private ShortTerm testShortTerm;
    
        
    
    @BeforeEach
    public void setup() {
        testWeeklySchedule = new WeeklySchedule();
        testTimeBlock = new TimeBlock();
        task1 = new Task("task1");
        task2 = new Task("task2");
        testShortTerm = new ShortTerm();
    }

    @Test
    public void testAddTimeBlock() {
        assertTrue(testWeeklySchedule.getTimeBlocks().isEmpty());
        testWeeklySchedule.addTimeBlock(testTimeBlock);
        assertEquals(1, testWeeklySchedule.getTimeBlocks().size());
        assertTrue(testWeeklySchedule.getTimeBlocks().contains(testTimeBlock));
    }

    @Test
    public void testGetTasksAtEnergyLevel() {
        task1.setEnergyLevel(1);
        task2.setEnergyLevel(3);
        testShortTerm.addTask(task1);
        testShortTerm.addTask(task2);
        testTimeBlock.setEnergyLevel(1);
        assertEquals(task1, testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm).get(0));
        assertEquals(task2, testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm).get(1));
        assertEquals(2, testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm).size());
        testTimeBlock.setEnergyLevel(2);
        assertNull(testWeeklySchedule.getTasksAtEnergyLevel(testTimeBlock, testShortTerm));
    }

   
}
