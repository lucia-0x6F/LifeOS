package model;

import java.util.ArrayList;

/**
 * Sets the timePeriod, dayOfWeek, energyLevel for the timeblock, add tasks into the timeBlock
 */

public class TimeBlock {
    private int timePeriod;
    private ArrayList<Task> tasks;
    private int energyLevel;
    private int dayOfWeek;

    //EFFECTS: Constructs a TimeBlock object
    public TimeBlock() {
        timePeriod = 0;
        tasks = new ArrayList<Task>();
        energyLevel = 0;
        dayOfWeek = 1;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    //MODIFIES: this
    //EFFECTS: add the task to tasks of the timeBlock
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public int getTimePeriod() {
        return timePeriod;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    
}

