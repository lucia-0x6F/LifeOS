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

    //MODIFIES: this
    //EFFECTS: sets the timePeriod for the timeBlock
    public void setTimePeriod(int timePeriod) {
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the task for the TimeBlock
    public void addTask(Task task) {
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the energyLevel for the TimeBlock
    public void setEnergyLevel(int energyLevel) {
        //Stub
    }

    //MODIFIES: this
    //EFFECTS: sets the dayOfWeek for the TimeBlock
    public void setDayOfWeek(int dayOfWeek) {
        //Stub
    }


    //EFFECTS: returns the timePeriod of the timeBlock
    public int getTimePeriod() {
        return timePeriod;
    }

    //EFFECTS: returns the task of the TimeBlock
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //EFFECTS: returns the energyLevel of the TimeBlock
    public int getEnergyLevel() {
        return energyLevel;
    }

    //MODIFIES: this
    //EFFECTS: gets the dayOfWeek for the TimeBlock
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    
}

