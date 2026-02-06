package model;

import java.util.ArrayList;

public class Goal {
    private String name;
    private ArrayList<Task> linkedTasks;
    private boolean completed;
 
    //EFFECTS: constructs an uncompleted Goal object
    public Goal(String name) {
        this.name = name;
        linkedTasks = new ArrayList<>();
        completed = false;
    }

    //MODIFIES: this
    //EFFECTS: sets the Goal's name 
    public void setName(){
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: add linkedTasks to the Goal's linkedTasks
    public void addLinkedTask(Task linkedTask){
        //Stub
    }

    //REQUIRES: linkedTasks cannot empty
    //MODIFIES: this
    //EFFECTS: remove linkedTask from the Goal's linkedTasks
    public void removeLinkedTask(Task linkedTask){
        //Stub
    }

    //EFFECTS: returns the Goal's name
    public String getName() {
        return  name;
    }

    //EFFECTS: returns the Goal's linkedTasks
    public ArrayList<Task> getLinkedTasks() {
        return linkedTasks;
    }
}
