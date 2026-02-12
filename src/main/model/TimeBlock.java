package model;

import java.util.ArrayList;

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
    //EFFECTS: sets the task for the TimeBlock
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

