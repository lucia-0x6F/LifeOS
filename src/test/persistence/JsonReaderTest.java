package persistence;

import model.Goal;
import model.Task;
import model.LongTerm;
import model.ShortTerm;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFileLongTerm() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LongTerm longTerm = reader.readLongTerm();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLongTerm() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLongTerm.json");
        try {
            LongTerm longTerm = reader.readLongTerm();
            assertEquals("My long term", longTerm.getName());
            assertEquals(0, longTerm.getGoals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLongTerm() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLongTerm.json");
        try {
            LongTerm longTerm = reader.readLongTerm();
            assertEquals("My long term", longTerm.getName());
            List<Goal> goals = longTerm.getGoals();
            assertEquals(2, goals.size());

            checkGoal("goal1", goals.get(0));
            checkGoal("goal2", goals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonExistentFileShortTerm() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShortTerm shortTerm = reader.readShortTerm();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShortTerm() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShortTerm.json");
        try {
            ShortTerm shortTerm = reader.readShortTerm();
            assertEquals("My short term", shortTerm.getName());
            assertEquals(0, shortTerm.getTasks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShortTerm() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShortTerm.json");
        try {
            ShortTerm shortTerm = reader.readShortTerm();
            assertEquals("My short term", shortTerm.getName());
            List<Task> tasks = shortTerm.getTasks();
            assertEquals(5, tasks.size());
            checkTask("task1", tasks.get(0));
            checkTask("task2", tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSetLinks() {
        JsonReader readerLong = new JsonReader("./data/testReaderGeneralLongTerm.json");
        JsonReader readerShort = new JsonReader("./data/testReaderGeneralShortTerm.json");

        try {
            LongTerm longTerm = readerLong.readLongTerm();
            ShortTerm shortTerm = readerShort.readShortTerm();

            assertEquals(2, readerLong.getGoals().size());
            assertEquals("goal1", readerLong.getGoals().get(0).getName());
            assertEquals("goal2", readerLong.getGoals().get(1).getName());


            readerShort.setLinks(longTerm.getGoals());

            Goal goal1 = longTerm.getGoals().get(0);
            Task task1 = shortTerm.getTasks().get(0);
            Task task2 = shortTerm.getTasks().get(1);
            Task task3 = shortTerm.getTasks().get(2);
            Task task4 = shortTerm.getTasks().get(3);
            Task task5 = shortTerm.getTasks().get(4);

            assertTrue(goal1.getLinkedTasks().contains(task1));
            assertTrue(goal1.getLinkedTasks().contains(task3));
            assertNull(task2.getLinkedGoal());
            assertNull(task4.getLinkedGoal());
            assertNull(task5.getLinkedGoal());


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testSetLinksNull() {
        JsonReader readerShort = new JsonReader("./data/testReaderGeneralShortTerm.json");

        try {
            ShortTerm shortTerm = readerShort.readShortTerm();

            Task task1 = shortTerm.getTasks().get(0);

            ArrayList<Goal> empty = new ArrayList<>();
            readerShort.setLinks(empty);
            assertNull(task1.getLinkedGoal());

            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
