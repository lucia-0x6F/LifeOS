package model;

import java.util.ArrayList;

public class Goal {
    private String name;
    private ArrayList<Task> linkedTasks;
    private boolean completed;
 
    //EFFECTS: constructs an uncompleted Goal object
    public Goal() {
        Goal goal = new Goal();
        name = "";
        linkedTasks = new ArrayList<>();
        completed = false;

    }

    //MODIFIES: this
    //EFFECTS: sets the Goal's name 
    public void setName(){
        //stub
    }

    //MODIFIES: this
    //EFFECTS: add linkedTasks to the Goal's linkedTasks
    public void addLinkedTask(Task linkedTask){
        //stub
    }

    //REQUIRES: linkedTasks cannot empty
    //MODIFIES: this
    //EFFECTS: remove linkedTask from the Goal's linkedTasks
    public void removeLinkedTask(Task linkedTask){
        //stub
    }

    //EFFECTS: returns the Goal's name
    public String getName() {
        return "";
    }

    //EFFECTS: returns the Goal's linkedTasks
    public ArrayList<Task> getLinkedTasks() {
        return linkedTasks;
    }
}
