package model;

import java.util.ArrayList;

public class ShortTerm {
    private ArrayList<Task> tasks;

    //EFFECTS: construct a ShortTermModule object
    public ShortTerm() {
        tasks = new ArrayList<Task>();
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks if the Task is not in the list
    public void addTask(Task task){
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    //REQUIRES: tasks cannot be empty
    //MODIFIES: this
    //EFFECTS: removes task from the ShortTermModule's tasks if the Task is in the list
    public void removeTask(Task task){
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }


    //EFFECTS: returns the ShortTermModule's tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    } 

    
}
