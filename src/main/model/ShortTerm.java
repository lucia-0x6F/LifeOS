package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.exception.NameErrorException;
import persistence.Writable;

/**
 * Add or remove task, find a task that has a specific name, view the task list 
 */
 
public class ShortTerm implements Writable {
    private ArrayList<Task> tasks;
    private String name;

    //EFFECTS: construct a ShortTermModule object with no tasks 
    public ShortTerm(String name) {
        this.name = name;
        tasks = new ArrayList<Task>();
    }

    public String getName() {
        return name;
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks if the Task's name is not in the list, otherwise exception
    public void addTask(String name) throws NameErrorException {
        for (Task t: tasks) {
            if (t.getName().equals(name)) {
                throw new NameErrorException();
            } 
        }
        Task task = new Task(name);
        tasks.add(task);
    }

    
    public void addTask(Task task) {
        tasks.add(task);
    } 

    //REQUIRES: tasks cannot be empty
    //MODIFIES: this
    //EFFECTS: removes task from the ShortTermModule's tasks if the Task is in the list, otherwise exception
    public void removeTask(String name) throws NameErrorException {
        Task removeTarget = null;
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                removeTarget = t;
                break;
            }
        }
        if (removeTarget != null) {
            tasks.remove(removeTarget);
        } else {
            throw new NameErrorException();
        }
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    } 

    //EFFECTS: returns the task that has the same name as the name, otherwise throw an exception
    public Task findTask(String name) {
        for (Task t: tasks) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasks", tasksToJson());
        return json;
    }

    // EFFECTS: returns goals in this shortTern as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : tasks) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
