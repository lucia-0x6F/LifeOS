package model;

import java.util.ArrayList;

public class ShortTerm {
    private ArrayList<Task> tasks;

    //EFFECTS: construct a ShortTermModule object
    public ShortTerm() {
        tasks = new ArrayList<Task>();
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks if the Task's name is not in the list
    public void addTask(Task task){
        //Stub
    }

    //REQUIRES: tasks cannot be empty
    //MODIFIES: this
    //EFFECTS: removes task from the ShortTermModule's tasks if the Task's name is in the list
    public void removeTask(Task task){
        //Stub
    }


    //EFFECTS: returns the ShortTermModule's tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    } 

    
}
