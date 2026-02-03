package model;

import java.util.ArrayList;

public class ShortTermModule {
    private ArrayList<Task> tasks;

    //EFFECTS: construct a ShortTermModule object
    public ShortTermModule() {
        tasks = new ArrayList<Task>();
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks
    public void addTask(Task task){
        //Stub
    }

    //REQUIRES: tasks cannot be empty
    //MODIFIES: this
    //EFFECTS: removes task from the ShortTermModule's tasks
    public void removeTask(Task task){
        //Stub
    }


    //EFFECTS: returns the ShortTermModule's tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    } 
}
