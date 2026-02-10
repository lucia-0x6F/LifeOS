package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    private Goal testGoal;
    private Task task1;
    private Task task2;

    @BeforeEach
    void runBefore() {
        testGoal = new Goal("testGoal");
        task1 = new Task("CPSC110");
        task2 = new Task("CPSC121");
    }

    @Test
    void setName(){
        assertEquals("testGoal", testGoal.getName());
        testGoal.setName("newName");
        assertEquals("newName", testGoal.getName());

    }

    @Test
    void testAddLinkedTasks(){
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.addLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());
        assertEquals(1, testGoal.getLinkedTasks().get(0));
        testGoal.addLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());

    }   

    @Test
    void testAddLinkedListNotAlreadyMultiple(){
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.addLinkedTask(task1);
        testGoal.addLinkedTask(task2);
        assertEquals(2, testGoal.getLinkedTasks().size());
        assertEquals(task1, testGoal.getLinkedTasks().get(0));
        assertEquals(task2, testGoal.getLinkedTasks().get(1));

    }   

    @Test
    void testRemoveLinkedList(){
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.addLinkedTask(task1);
        testGoal.addLinkedTask(task2);
        assertEquals(2, testGoal.getLinkedTasks().size());
        testGoal.removeLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());
        assertEquals(task2, testGoal.getLinkedTasks().get(0));
    }


    @Test
    void testMarkCompleteStatus() {
        assertFalse(testGoal.getCompleteStatus());
        testGoal.MarkAsCompleted();
        assertTrue(testGoal.getCompleteStatus());
        testGoal.MarkAsUncompleted();
        assertFalse(testGoal.getCompleteStatus());
    }


    
}

