package model;

import java.util.ArrayList;

public class LongTerm {
    private ArrayList <Goal> goals;
    

    //EFFECTS: construct a LongTermModule object
    public LongTerm() {
        goals = new ArrayList<Goal>();

    }

    //MODIFIES: this
    //EFFECTS: add the goal to the LongTermModule's goals if the goal is not in the list
    public void addGoal(Goal goal){
        if (!goals.contains(goal)) {
            goals.add(goal);
        }
    }

    //REQUIRES: goals cannot be empty
    //MODIFIES: this
    //EFFECTS: removes the goal from the LongTermModule's goals if the goal is in the list
    public void removeGoal(Goal goal){
         if (goals.contains(goal)) {
            goals.remove(goal);
        }
    }


    //EFFECTS: returns the LongTermModule's goals
    public ArrayList<Goal> getGoals() {
        return goals;
    } 


}
