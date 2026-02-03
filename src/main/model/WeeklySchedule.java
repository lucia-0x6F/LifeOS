package model;

import java.util.ArrayList;

public class WeeklySchedule {
    private ArrayList<TimeBlock> timeBlocks;

    //EFFECTS: Constructs a TimeBlock object
    public WeeklySchedule() {
        timeBlocks = new ArrayList<TimeBlock>();
    }

    //MODIFIES: this
    //EFFECTS: adds the timeBlock to timeBlocks of the WeeklySchedule
    public void addTimeBlock(TimeBlock timeBlock) {
        //Stub
    }

    //EFFECTS: returns timeBlocks of WeeklySchedule
    public ArrayList<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    
}
