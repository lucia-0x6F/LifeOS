package model;

/**
 * Sets the name, energyLevel, times, deadline and complete status and linkedTask for the task
 */

public class Task implements WorkUnit {
    private int energyLevel;
    private String name;
    private Goal linkedGoal;
    private int times;
    private String deadline;
    private boolean completeStatus;
    
    //EFFECTS: constructs an uncompleted Task object with 0 energylevel, 
    // no linkedGoal, 0 times, "0101" deadline and the completeStatus is false
    public Task(String name) {
        this.name = name;
        energyLevel = 0;
        linkedGoal = null;
        times = 0;
        deadline = "0101";
        completeStatus = false;

    }

    @Override
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
    
    @Override
    public void markAsCompleted() {
        completeStatus = true;
    }

    @Override
    public void markAsUncompleted() {
        completeStatus = false;
    }

    public boolean getCompleteStatus() {
        return completeStatus;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setTimes(int times) {
        this.times = times;
    }


    public int getTimes() {
        return times;
    }

 



}
