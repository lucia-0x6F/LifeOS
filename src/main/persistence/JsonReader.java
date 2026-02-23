package persistence;

import model.LongTerm;
import model.ShortTerm;
import model.Goal;
import model.Task;
import model.TimeBlock;
import model.WeeklySchedule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads longTerm and shortTerm from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads longTerm from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LongTerm readLongTerm() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFileLongTerm(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses longTerm from JSON object and returns it
    private LongTerm parseLongTerm(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: longTerm
    // EFFECTS: parses goals from JSON object and adds them to longTerm
    private void addGoals(LongTerm longTerm, JSONObject jsonObject) {
        //Stub
    }

    // MODIFIES: longTerm
    // EFFECTS: parses goal from JSON object and adds it to longTerm
    private void addGoal(LongTerm longTerm, JSONObject jsonObject) {
        //Stub
    }

    // EFFECTS: reads shortTerm from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShortTerm readShortTerm() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFileShortTerm(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses shortTerm from JSON object and returns it
    private ShortTerm parseShortTerm(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: shortTerm
    // EFFECTS: parses tasks from JSON object and adds them to shortTerm
    private void addTasks(ShortTerm shortTerm, JSONObject jsonObject) {
        //Stub
    }

    // MODIFIES: shortTerm
    // EFFECTS: parses task from JSON object and adds it to shortTerm
    private void addTask(ShortTerm shortTerm, JSONObject jsonObject) {
        //Stub
    }
}

