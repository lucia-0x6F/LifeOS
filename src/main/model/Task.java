package model;

import org.json.JSONObject;

import persistence.Writable;

/**
 * Sets the name, energyLevel, times, deadline and complete status and linkedTask for the task
 */

public class Task implements WorkUnit, Writable {
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

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public Goal getLinkedGoal() {
        return linkedGoal;
    }
    
    // MODIFIES: this
    // EFFECTS: sets the task as completed
    @Override
    public void setAsCompleted() {
        completeStatus = true;
    }

    // MODIFIES: this
    // EFFECTS: sets the task as uncompleted
    @Override
    public void setAsUncompleted() {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("energyLevel", energyLevel);
        if (linkedGoal == null) {
            json.put("linkedGoal", JSONObject.NULL);
        } else {
            json.put("linkedGoal", linkedGoal.getName());
        }
        json.put("times", times);
        json.put("deadline", deadline);
        json.put("completeStatus", completeStatus);
        return json;
    }
}

 




