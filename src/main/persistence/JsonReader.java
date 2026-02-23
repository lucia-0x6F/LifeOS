package persistence;

import model.LongTerm;
import model.ShortTerm;
import model.Goal;
import model.Task;
import model.TimeBlock;
import model.WeeklySchedule;
import model.exception.NameErrorException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads longTerm and shortTerm from JSON data stored in file
//TODO: Citation
//TODO: add lines related to the change of parameters for longTerm and shortTerm
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads longTerm from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LongTerm readLongTerm() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLongTerm(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        System.out.println("TRY READ: " + source);
        System.out.println("ABS PATH: " + Paths.get(source).toAbsolutePath());
        System.out.println("EXISTS? " + Files.exists(Paths.get(source)));
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses longTerm from JSON object and returns it
    private LongTerm parseLongTerm(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LongTerm longTerm = new LongTerm(name);
        addGoals(longTerm, jsonObject);
        return longTerm;
    }

    // MODIFIES: longTerm
    // EFFECTS: parses goals from JSON object and adds them to longTerm
    private void addGoals(LongTerm longTerm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("goals");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(longTerm, nextGoal);
        }
    }

    // MODIFIES: longTerm
    // EFFECTS: parses goal from JSON object and adds it to longTerm
    private void addGoal(LongTerm longTerm, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        try {
            longTerm.addGoal(name);
        } catch (NameErrorException e) {
            //pass
        }
    }

    // EFFECTS: reads shortTerm from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShortTerm readShortTerm() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShortTerm(jsonObject);
    }

    // EFFECTS: parses shortTerm from JSON object and returns it
    private ShortTerm parseShortTerm(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ShortTerm shortTerm = new ShortTerm(name);
        addTasks(shortTerm, jsonObject);
        return shortTerm;
    }

    // MODIFIES: shortTerm
    // EFFECTS: parses tasks from JSON object and adds them to shortTerm
    private void addTasks(ShortTerm shortTerm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(shortTerm, nextTask);
        }
    }

    // MODIFIES: shortTerm
    // EFFECTS: parses task from JSON object and adds it to shortTerm
    private void addTask(ShortTerm shortTerm, JSONObject jsonObject) {
         String name = jsonObject.getString("name");
        try {
            shortTerm.addTask(name);
        } catch (NameErrorException e) {
            //pass
        }
    }
}

