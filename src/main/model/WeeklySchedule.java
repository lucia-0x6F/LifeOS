package model;
import java.util.ArrayList;


public class WeeklySchedule {
    private ArrayList<TimeBlock> timeBlocks;

    //EFFECTS: Constructs a WeeklySchedule object
    public WeeklySchedule() {
        timeBlocks = new ArrayList<TimeBlock>();
    }

    //MODIFIES: this
    //EFFECTS: adds the timeBlock to timeBlocks of the WeeklySchedule
    public void addTimeBlock(TimeBlock timeBlock) {
        timeBlocks.add(timeBlock);
    }

    public ArrayList<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }

    public ArrayList<Task> getTasksAtEnergyLevel(TimeBlock timeBlock, ShortTerm shortTerm) {
        ArrayList<Task> tasksAtLevel = new ArrayList<>();
        Boolean found = false;
        for (Task t: shortTerm.getTasks()) {
            if (t.getEnergyLevel() == timeBlock.getEnergyLevel()) {
                tasksAtLevel.add(t);
                found = true;
            }
        }
        if (found) {
            return tasksAtLevel;
        } else {
            return null;
        }
    }



}