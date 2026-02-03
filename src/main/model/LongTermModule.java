package model;

import java.util.ArrayList;

public class LongTermModule {
    private ArrayList<Goal> goals;
    

    //EFFECTS: construct a LongTermModule object
    public LongTermModule() {

    }

    //MODIFIES: this
    //EFFECTS: add the goal to the LongTermModule's goals
    public void addGoal(Goal goal){
        //Stub
    }

    //REQUIRES: goals cannot be empty
    //MODIFIES: this
    //EFFECTS: removes the goal from the LongTermModule's goals
    public void removeGoal(Goal goal){
        //Stub
    }


    //EFFECTS: returns the LongTermModule's goals
    public ArrayList<Goal> getGoals() {
        return goals;
    } 


}
