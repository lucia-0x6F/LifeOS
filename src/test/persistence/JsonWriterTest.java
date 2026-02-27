package persistence;

import model.Goal;
import model.LongTerm;
import model.ShortTerm;
import model.Task;
import model.exception.NameErrorException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLongTerm() {
        try {
            LongTerm longTerm = new LongTerm("My long term");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLongTerm.json");
            writer.open();
            writer.write(longTerm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLongTerm.json");
            longTerm = reader.readLongTerm();
            assertEquals("My long term", longTerm.getName());
            assertEquals(0, longTerm.getGoals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLongTerm() {
        try {
            LongTerm longTerm = new LongTerm("My long term");
            try {
                longTerm.addGoal("goal1");
                longTerm.addGoal("goal2");
            } catch (NameErrorException e) {
                //pass
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLongTerm.json");
            writer.open();
            writer.write(longTerm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLongTerm.json");
            longTerm = reader.readLongTerm();
            assertEquals("My long term", longTerm.getName());
            List<Goal> goals = longTerm.getGoals();
            assertEquals(2, goals.size());
            checkGoal("goal1", goals.get(0));
            checkGoal("goal2", goals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyShortTerm() {
        try {
            ShortTerm shortTerm = new ShortTerm("My short term");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShortTerm.json");
            writer.open();
            writer.write(shortTerm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShortTerm.json");
            shortTerm = reader.readShortTerm();
            assertEquals("My short term", shortTerm.getName());
            assertEquals(0, shortTerm.getTasks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShortTerm() {
        try {
            ShortTerm shortTerm = new ShortTerm("My short term");
            try {
                shortTerm.addTask("task1");
                shortTerm.addTask("task2");
            } catch (NameErrorException e) {
                //pass
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShortTerm.json");
            writer.open();
            writer.write(shortTerm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShortTerm.json");
            shortTerm = reader.readShortTerm();
            assertEquals("My short term", shortTerm.getName());
            List<Task> tasks = shortTerm.getTasks();
            assertEquals(2, tasks.size());
            checkTask("task1", tasks.get(0));
            checkTask("task2", tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSetLinks() {
        try {
            Goal goal = new Goal("goal1");
            Task task = new Task("task1");
            goal.setLinkedTask(task);
            task.setLinkedGoal(goal);

            JSONObject goal1 = goal.toJson();
            JSONObject task1 = task.toJson();
            JSONArray jsonArray = goal1.getJSONArray("linkedTasks");
            
            assertEquals("task1", jsonArray.getString(0));
            assertEquals("goal1", task1.getString("linkedGoal"));
            assertEquals(1, jsonArray.length());

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}