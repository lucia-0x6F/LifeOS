package model;

import java.util.ArrayList;

public class Task {
    private String energyLevel;
    private String name;
    private String linkedGoal;
    private int times;
    private String deadline;
    private boolean completed;
    
     //EFFECTS: constructs an uncompleted Task object
    public Task() {
        Task task = new Task();
        energyLevel = "";
        name = "";
        linkedGoal = "";
        times = 0;
        deadline = "0220";
        completed = false;

    }

    //MODIFIES: this
    //EFFECTS: sets the Task's name 
    public void setName(){
        //stub
    }

    //MODIFIES: this
    //EFFECTS: sets linkedGoal to the Task
    public void setLinkedGoal(){
        //stub
    }

    //EFFECTS: returns the Task's name
    public String getName() {
        return "";
    }

    //EFFECTS: returns the Task's linkedGoal
    public String getLinkedGoal() {
        return "";
    }


}
