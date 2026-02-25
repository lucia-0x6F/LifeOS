package persistence;

import model.Goal;
import model.LongTerm;
import model.ShortTerm;
import model.Task;
import model.exception.NameErrorException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads longTerm and shortTerm from JSON data stored in file
//TODO: Citation
public class JsonReader {
    private String source;
    private List<Goal> goals;
    private List<Task> tasks;

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

    // EFFECTS: parses Task from JSON object and returns it
    private Task parseTask(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int energyLevel = jsonObject.getInt("energyLevel");
        String linkedGoal = jsonObject.getString("linkedGoal");
        int times = jsonObject.getInt("times");
        String deadline = jsonObject.getString("deadline");
        Boolean completeStatus = jsonObject.getBoolean("completeStatus");
        Task task = new Task(name);
        task.setName(name);
        task.setLinkedGoal(null);
        task.setEnergyLevel(energyLevel);
        task.setTimes(times);
        task.setDeadline(deadline);

        if (completeStatus) {
            task.markAsCompleted();
        } else {
            task.markAsUncompleted();
        }

        if (linkedGoal != null) {
            Goal goal = findGoalByName(linkedGoal, goals);
            task.setLinkedGoal(goal);
        }

        return task;
        
        }

    // EFFECTS: returns the goal if the name is in goals, otherwise returns null 
    private Goal findGoalByName(String name, List<Goal> goals) {
        for (Goal g : goals) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
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

    private Goal parseGoal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean completeStatus = jsonObject.getBoolean("completeStatus");
        Goal goal = new Goal(name);

        if (completeStatus) {
            goal.markAsCompleted();
        } else {
            goal.markAsUncompleted();
        }

        JSONArray jsonArray = jsonObject.getJSONArray("linkedTasks");
            for (int i = 0; i < jsonArray.length(); i++) {
                String taskName = jsonArray.getString(i);
                goal.setLinkedTask(findTaskByName(taskName, tasks));
        }

        return goal;

    }


    // EFFECTS: returns the goal if the name is in goals, otherwise returns null 
    private Task findTaskByName(String name, List<Task> tasks) {
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
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

