package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExcludeFromJacocoGeneratedReport
public class GoalTest {
    private Goal testGoal;
    private Task task1;
    private Task task2;

    @BeforeEach
    void runBefore() {
        testGoal = new Goal("testGoal");
        task1 = new Task("task1");
        task2 = new Task("task2");
    }

    @Test
    void setName() {
        assertEquals("testGoal", testGoal.getName());
        testGoal.setName("newName");
        assertEquals("newName", testGoal.getName());

    }

    @Test
    void testAddLinkedTasks() {
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.setLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());
        assertEquals(task1, testGoal.getLinkedTasks().get(0));
        testGoal.setLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());

    }   

    @Test
    void testAddLinkedListNotAlreadyMultiple() {
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.setLinkedTask(task1);
        testGoal.setLinkedTask(task2);
        assertEquals(2, testGoal.getLinkedTasks().size());
        assertEquals(task1, testGoal.getLinkedTasks().get(0));
        assertEquals(task2, testGoal.getLinkedTasks().get(1));

    }   

    @Test
    void testRemoveLinkedList() {
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.setLinkedTask(task1);
        testGoal.setLinkedTask(task2);
        assertTrue(testGoal.getLinkedTasks().contains(task1));
        assertTrue(testGoal.getLinkedTasks().contains(task2));
        testGoal.removeLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTasks().size());
        assertEquals(task2, testGoal.getLinkedTasks().get(0));
        Task task3 = new Task("Task3");
        testGoal.removeLinkedTask(task3);
        assertEquals(1, testGoal.getLinkedTasks().size());
        assertEquals(task2, testGoal.getLinkedTasks().get(0));
    }

    @Test
    void testMarkCompleteStatus() {
        assertFalse(testGoal.getCompleteStatus());
        testGoal.markAsCompleted();
        assertTrue(testGoal.getCompleteStatus());
        testGoal.markAsUncompleted();
        assertFalse(testGoal.getCompleteStatus());
    }

    @Test
    void testGetLinkedTaskNames() {
        assertTrue(testGoal.getLinkedTasks().isEmpty());
        testGoal.setLinkedTask(task1);
        assertEquals(1, testGoal.getLinkedTaskNames().size());
        assertEquals("task1", testGoal.getLinkedTaskNames().get(0));
        testGoal.setLinkedTask(task2);
        assertEquals(2, testGoal.getLinkedTaskNames().size());
        assertEquals("task2", testGoal.getLinkedTaskNames().get(1));
    }


    
}

