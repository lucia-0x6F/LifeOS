package model;

public class Task {
    private int energyLevel;
    private String name;
    private String linkedGoal;
    private int times;
    private String deadline;
    private boolean completeStatus;
    
    //EFFECTS: constructs an uncompleted Task object
    public Task(String name) {
        this.name = name;
        energyLevel = 0;
        linkedGoal = "";
        times = 0;
        deadline = "0220";
        completeStatus = false;

    }

    //MODIFIES: this
    //EFFECTS: sets the name as the Task's name 
    public void setName(String name) {
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the Task's energyLevel 
    public void setEnergyLevel() {
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the goal as linkedGoal to the Task 
    public void setLinkedGoal(Goal goal) {
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

    //MODIFIES: this
    //EFFECTS: sets the completeStatus of the Task to be completed
    public void MarkAsCompleted() {
        //Stub
    }

    //MODIFIES: this
     //EFFECTS: sets the completeStatus of the Task to be uncompleted
    public void MarkAsUncompleted() {
        //Stub
    }

    //EFFECTS: sets the completeStatus of the Task to be uncompleted
    public boolean getCompleteStatus() {
        return completeStatus;
    }

    //MODIFIES: this
    //EFFECTS: sets the String as the deadLine of the Task
    public void setDeadline(String deadline) {
        //Stub
    }

    //EFFECTS: returns the deadLine of the Task
    public String getDeadline() {
        return deadline;
    }

    //MODIFIES: this
    //EFFECTS: sets the times as times of the Task
    public void setTimes(int Times) {
        //Stub
    }


    //MODIFIES: this
    //EFFECTS: returns the times of the Task
    public int getTimes() {
        return times;
    }



}
