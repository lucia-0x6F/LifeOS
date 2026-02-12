package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongTermTest{
    private LongTerm testLongTerm;
    private String goal1;
    private String goal2;
    
    @BeforeEach
    void runBefore() {
        testLongTerm= new LongTerm();
        goal1 = new String("goal1");
        goal2 = new String("goal2");

    }

    @Test
    void testConstructor() {
        assertTrue(testLongTerm.getGoals().isEmpty());
    }

    @Test
    void testAddTaskSingle() throws NameErrorException {
        testLongTerm.addGoal(goal1);
        assertEquals(1, testLongTerm.getGoals().size());
        assertEquals("goal1", testLongTerm.getGoals().get(0).getName());
    }

    @Test
    void testAddTaskMultiple() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal(goal1);
        testLongTerm.addGoal(goal2);
        assertEquals(2, testLongTerm.getGoals().size());
        assertEquals("goal1", testLongTerm.getGoals().get(0).getName());
        assertEquals("goal2", testLongTerm.getGoals().get(1).getName());
    }

    @Test
    void testRemoveTaskSingle() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal(goal1);
        testLongTerm.addGoal(goal2);
        assertEquals(2, testLongTerm.getGoals().size());
        testLongTerm.removeGoal(goal1);
        assertEquals(1, testLongTerm.getGoals().size());

    }

      @Test
    void testRemoveTaskMultiple() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal(goal1);
        testLongTerm.addGoal(goal2);
        testLongTerm.removeGoal(goal1);
        assertEquals(1, testLongTerm.getGoals().size());
        assertEquals("goal2", testLongTerm.getGoals().get(0).getName());
    }

}