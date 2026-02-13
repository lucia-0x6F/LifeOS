package model;

import java.util.ArrayList;

public class LongTerm {
    private ArrayList<Goal> goals;
    

    //EFFECTS: construct a LongTermModule object
    public LongTerm() {
        goals = new ArrayList<Goal>();

    }

    //Requires: the goal's name length must >0
    //MODIFIES: this
    //EFFECTS: add the goal to the LongTermModule's goals if the goal's name is not in the list
    public void addGoal(String name) throws NameErrorException {
        for (Goal g: goals) {
            if (g.getName().equals(name)) {
                throw new NameErrorException();
            } 
        }
        Goal goal = new Goal(name);
        goals.add(goal);
    }

    //REQUIRES: goals cannot be empty
    //MODIFIES: this
    //EFFECTS: removes the goal from the LongTermModule's goals if the goal's name is in the list
    public void removeGoal(String name) throws NameErrorException {
        Goal removeTarget = null;
        for (Goal g : goals) {
            if (g.getName().equals(name)) {
                removeTarget = g;
                break;
            }
        }
        if (removeTarget != null) {
            goals.remove(removeTarget);
        } else {
            throw new NameErrorException();
        }
    }



    public ArrayList<Goal> getGoals() {
        return goals;
    } 

    //EFFECTS: returns the goal that has the same name as the name
    public Goal findGoal(String name) throws NameErrorException {
        for (Goal g: goals) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        throw new NameErrorException();
    }


}
