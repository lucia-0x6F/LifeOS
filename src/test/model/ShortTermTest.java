package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortTermTest{
    private ShortTerm testShortTerm;
    
    @BeforeEach
    void runBefore() {
        testShortTerm= new ShortTerm();
    }
    @Test
    void testConstructor() {
        assertTrue(testShortTerm.getTasks().isEmpty());
    }

    @Test
    void testAddTaskSingle() throws NameErrorException {
        testShortTerm.addTask("task1");
        assertEquals(1, testShortTerm.getTasks().size());
        assertEquals("task1", testShortTerm.getTasks().get(0).getName());
    }

    @Test
    void testAddTaskMultiple() throws NameErrorException {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask("task1");
        testShortTerm.addTask("task2");
        assertEquals(2, testShortTerm.getTasks().size());
        assertEquals("task1", testShortTerm.getTasks().get(0).getName());
        assertEquals("task2", testShortTerm.getTasks().get(1).getName());
    }

    @Test
    void testRemoveTaskSingle() throws NameErrorException {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask("task1");
        assertEquals(1, testShortTerm.getTasks().size());
        testShortTerm.removeTask("task1");
        assertTrue(testShortTerm.getTasks().isEmpty());

    }

      @Test
    void testRemoveTaskMultiple() throws NameErrorException {
        assertTrue(testShortTerm.getTasks().isEmpty());
        testShortTerm.addTask("task1");
        testShortTerm.addTask("task2");
        testShortTerm.removeTask("task1");
        assertEquals(1, testShortTerm.getTasks().size());
        assertEquals("task2", testShortTerm.getTasks().get(0).getName());
    }


}