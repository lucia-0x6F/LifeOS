package model;

public class Task implements WorkUnit {
    private int energyLevel;
    private String name;
    private Goal linkedGoal;
    private int times;
    private int deadline;
    private boolean completeStatus;
    
    //EFFECTS: constructs an uncompleted Task object
    public Task(String name) {
        this.name = name;
        energyLevel = 0;
        linkedGoal = null;
        times = 0;
        deadline = 0220;
        completeStatus = false;

    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void setLinkedGoal(Goal goal) {
        this.linkedGoal = goal;
    }

    public String getName() {
        return name;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public Goal getLinkedGoal() {
        return linkedGoal;
    }

    //MODIFIES: this
    //EFFECTS: sets the completeStatus of the Task to be completed
    public void markAsCompleted() {
        completeStatus = true;
    }

    //MODIFIES: this
     //EFFECTS: sets the completeStatus of the Task to be uncompleted
    public void markAsUncompleted() {
        completeStatus = false;
    }

    public boolean getCompleteStatus() {
        return completeStatus;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setTimes(int times) {
        this.times = times;
    }


    public int getTimes() {
        return times;
    }



}
