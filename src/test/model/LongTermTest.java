package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.exception.NameErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExcludeFromJacocoGeneratedReport
public class LongTermTest {
    private LongTerm testLongTerm;
    
    @BeforeEach
    void runBefore() {
        testLongTerm = new LongTerm("My long term");
    }

    @Test
    void testConstructor() {
        assertTrue(testLongTerm.getGoals().isEmpty());
    }

    @Test
    void testAddGoalSingle() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal("goal1");
        assertEquals(1, testLongTerm.getGoals().size());
        assertEquals("goal1", testLongTerm.getGoals().get(0).getName());
    }

    @Test
    void testAddGoalMultiple() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal("goal1");
        testLongTerm.addGoal("goal2");
        assertEquals(2, testLongTerm.getGoals().size());
        assertEquals("goal1", testLongTerm.getGoals().get(0).getName());
        assertEquals("goal2", testLongTerm.getGoals().get(1).getName());
    }

    @Test
    void testAddGoalNameDuplicate() {
        try {
            testLongTerm.addGoal("goal1");
            testLongTerm.addGoal("goal1");
            fail("Cause NameErrorException");
        } catch (NameErrorException e) {
            //stub
        }
    }

    @Test
    void testRemoveGoalsingle() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal("goal1");
        testLongTerm.addGoal("goal2");
        assertEquals(2, testLongTerm.getGoals().size());
        testLongTerm.removeGoal("goal1");
        assertEquals(1, testLongTerm.getGoals().size());

    }

    @Test
    void testRemoveGoalsMultiple() throws NameErrorException {
        assertTrue(testLongTerm.getGoals().isEmpty());
        testLongTerm.addGoal("goal1");
        testLongTerm.addGoal("goal2");
        testLongTerm.removeGoal("goal1");
        assertEquals(1, testLongTerm.getGoals().size());
        assertEquals("goal2", testLongTerm.getGoals().get(0).getName());
    }

    @Test
    void testRemoveGoalNameDuplicate() {
        try {
            testLongTerm.addGoal("goal1");
            testLongTerm.removeGoal("goal1");
            testLongTerm.removeGoal("goal1");
            fail("Cause NameErrorException");
        } catch (NameErrorException e) {
            //stub
        }
    }

    @Test
    void testRemoveGoalNameNotFound() {
        try {
            testLongTerm.addGoal("goal1");
            testLongTerm.removeGoal("goal2");
            fail("Cause NameErrorException");
        } catch (NameErrorException e) {
            //stub
        }
    }

    @Test
    void testFindGoal() {
        assertTrue(testLongTerm.getGoals().isEmpty());
        try {
            testLongTerm.addGoal("goal1");
            assertEquals("goal1",testLongTerm.findGoal("goal1").getName());
        } catch (NameErrorException e) {
            fail("There's no exception");
        }
        testLongTerm.findGoal("goal2");
    }


}