package persistence;

import model.Goal;
import model.Task;
import model.LongTerm;
import model.ShortTerm;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
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
            assertEquals(2, tasks.size());
            checkTask("task1", tasks.get(0));
            checkTask("task2", tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}