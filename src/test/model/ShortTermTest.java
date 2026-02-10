package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortTermTest{
    private ShortTerm testShortTerm;
    private Task task1;
    private Task task2;
    
    @BeforeEach
    void runBefore() {
        testShortTerm= new ShortTerm();
        task1 = new Task("CPSC110");
        task2 = new Task("CPSC121");
    }
    @Test
    void testConstructor() {
        assertTrue(testShortTerm.getTasks().isEmpty());
    }

    @Test
    void testAddTaskSingle() {
        testShortTerm.addTask(task1);
        assertEquals(1, testShortTerm.getTasks().size());
        assertEquals("CPSC110", testShortTerm.getTasks().get(0).getName());
    }

    @Test
    void testAddTaskMultiple() {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask(task1);
        testShortTerm.addTask(task2);
        assertEquals(2, testShortTerm.getTasks().size());
        assertEquals("CPSC110", testShortTerm.getTasks().get(0).getName());
        assertEquals("CPSC121", testShortTerm.getTasks().get(1).getName());
    }

    @Test
    void testRemoveTaskSingle() {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask(task1);
        assertEquals(1, testShortTerm.getTasks().size());
        testShortTerm.removeTask(task1);
        assertTrue(testShortTerm.getTasks().isEmpty());

    }

      @Test
    void testRemoveTaskMultiple() {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask(task1);
        testShortTerm.addTask(task2);
        testShortTerm.removeTask(task1);
        assertEquals(1, testShortTerm.getTasks().size());
        assertEquals("CPSC121", testShortTerm.getTasks().get(0).getName());
    }


}