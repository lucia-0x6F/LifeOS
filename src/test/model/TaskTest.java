package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task testTask;
    private Goal goal1;
    private Goal goal2;

    @BeforeEach
    void runBefore() {
        testTask = new Task("testTask");
        goal1 = new Goal("goal1");
        goal2 = new Goal("goal2");

    }

    @Test
    void setName(){
        assertEquals("testTask", testTask.getName());
        testTask.setName("newName");
        assertEquals("newName", testTask.getName());

    }

    @Test
    void testAddLinkedTasks(){
        assertNull(testTask.getLinkedGoal());
        testTask.setLinkedGoal(goal1);
        assertEquals(goal1, testTask.getLinkedGoal());
        testTask.setLinkedGoal(goal2);
        assertEquals(goal2, testTask.getLinkedGoal());

    }   


    @Test
    void testMarkCompleteStatus() {
        assertFalse(testTask.getCompleteStatus());
        testTask.markAsCompleted();
        assertTrue(testTask.getCompleteStatus());
        testTask.markAsUncompleted();
        assertFalse(testTask.getCompleteStatus());
    }

    @Test
    void testSetEnergyLevel() {
        assertEquals(0, testTask.getEnergyLevel());
        testTask.setEnergyLevel(1);
        assertEquals(1, testTask.getEnergyLevel());
    }

    @Test
    void testSetDeadline() {
        assertEquals(0220, testTask.getDeadline());
        testTask.setDeadline(0111);
        assertEquals(0111, testTask.getDeadline());
        testTask.setDeadline(0220);
        assertEquals(0220, testTask.getDeadline());
    }


    @Test
    void testSetTimes() {
        assertEquals(0, testTask.getTimes());
        testTask.setTimes(2);
        assertEquals(2, testTask.getTimes());
         testTask.setTimes(1);
        assertEquals(1, testTask.getTimes());
    }
    

}
