package persistence;

import model.Goal;
import model.LongTerm;
import model.ShortTerm;
import model.Task;
import model.exception.NameErrorException;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

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
            LongTerm wr = new LongTerm("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLongTerm.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLongTerm.json");
            wr = reader.readLongTerm();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.getGoals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLongTerm() {
        try {
            LongTerm longTerm = new LongTerm("My work room");
            try {
                longTerm.addGoal("goal1");
            } catch (NameErrorException e) {
                //pass
            }
            JsonWriter writer = new JsonWriter("data/testWriterGeneralLongTerm.json");
            writer.open();
            writer.write(longTerm);
            writer.close();

            JsonReader reader = new JsonReader("data/testWriterGeneralLongTerm.json");
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
            JsonWriter writer = new JsonWriter("data/testWriterEmptyShortTerm.json");
            writer.open();
            writer.write(shortTerm);
            writer.close();

            JsonReader reader = new JsonReader("data/testWriterEmptyShortTerm.json");
            shortTerm = reader.readShortTerm();
            assertEquals("My work room", shortTerm.getName());
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
            } catch (NameErrorException e) {
                //pass
            }
            JsonWriter writer = new JsonWriter("data/testWriterGeneralShortTerm.json");
            writer.open();
            writer.write(shortTerm);
            writer.close();

            JsonReader reader = new JsonReader("data/testWriterGeneralShortTerm.json");
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
}