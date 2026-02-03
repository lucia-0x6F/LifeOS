package model;

import java.util.ArrayList;

public class Task {
    private int energyLevel;
    private String name;
    private String linkedGoal;
    private int times;
    private String deadline;
    private boolean completed;
    
     //EFFECTS: constructs an uncompleted Task object
    public Task() {
        Task task = new Task();
        energyLevel = 0;
        name = "";
        linkedGoal = "";
        times = 0;
        deadline = "0220";
        completed = false;

    }

    //MODIFIES: this
    //EFFECTS: sets the Task's name 
    public void setName(){
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the Task's energyLevel 
    public void setEnergyLevel(){
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets linkedGoal to the Task
    public void setLinkedGoal(){
        //Stub
    }

    //EFFECTS: returns the Task's name
    public String getName() {
        return name;
    }

     //EFFECTS: returns the Task's energyLevel
    public int getEnergyLevel() {
        return energyLevel;
    }

    //EFFECTS: returns the Task's linkedGoal
    public String getLinkedGoal() {
        return linkedGoal;
    }


}
