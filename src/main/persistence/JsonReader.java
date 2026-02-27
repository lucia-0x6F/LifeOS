package persistence;

import model.Goal;
import model.LongTerm;
import model.ShortTerm;
import model.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads longTerm and shortTerm from JSON data stored in file
public class JsonReader {
    private String source;
    private List<Goal> goals;
    private List<Task> tasks;
    private Map<Task, String> pendingGoalNames;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        goals = new ArrayList<>();
        tasks = new ArrayList<>();
        pendingGoalNames = new HashMap<Task, String>();
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

    // MODIFIES: this
    // EFFECTS: parses Task from JSON object and returns it
    private Task parseTask(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int energyLevel = jsonObject.getInt("energyLevel");
        int times = jsonObject.getInt("times");
        String deadline = jsonObject.getString("deadline");
        Boolean completeStatus = jsonObject.getBoolean("completeStatus");
        Task task = new Task(name);
        task.setEnergyLevel(energyLevel);
        task.setTimes(times);
        task.setDeadline(deadline);
    
        if (completeStatus) {
            task.setAsCompleted();
        } else {
            task.setAsUncompleted();
        }

        if (jsonObject.has("linkedGoal") && !jsonObject.isNull("linkedGoal")) {
            String goalNames = null;
            goalNames = jsonObject.getString("linkedGoal");
            pendingGoalNames.put(task, goalNames);
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

    // MODIFIES: longTerm, this
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
        Goal goal = parseGoal(jsonObject);
        longTerm.addGoal(goal);
        goals.add(goal);
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

    // EFFECTS: parses goal from JSON object and returns it
    private Goal parseGoal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean completeStatus = jsonObject.getBoolean("completeStatus");
        Goal goal = new Goal(name);

        if (completeStatus) {
            goal.setAsCompleted();
        } else {
            goal.setAsUncompleted();
        }

        return goal;
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

    // MODIFIES: shortTerm, this
    // EFFECTS: parses task from JSON object and adds it to shortTerm
    private void addTask(ShortTerm shortTerm, JSONObject jsonObject) {
        Task task = parseTask(jsonObject);
        shortTerm.addTask(task);
        tasks.add(task);
    }
    
    // MODIFIES: goals
    // EFFECTS: if a task's pendingGoalName can be found in the goal list,
    //          set the goal as linkedGoal for the task,
    //          and set this task as the linkedTask for the goal
    public void setLinks(List<Goal> goals) {
        for (Task task : pendingGoalNames.keySet()) {
            String goalName = pendingGoalNames.get(task);
            Goal goal = findGoalByName(goalName, goals);
            if (goal != null) {
                task.setLinkedGoal(goal);
                goal.setLinkedTask(task);
            }   
        }
    }

    public List<Goal> getGoals() {
        return goals;
    }
}

