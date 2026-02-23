package model;

import java.util.ArrayList;

import model.exception.NameErrorException;

/**
 * Add or remove goal, find a goal that has a specific name, view the goal list 
 */

public class LongTerm {
    private ArrayList<Goal> goals;
    
    //EFFECTS: construct a LongTermModule object with no goals
    public LongTerm() {
        goals = new ArrayList<Goal>();

    }

    //MODIFIES: this
    //EFFECTS: add the goal to the LongTermModule's goals if the goal's name is not in the list, otherwise exception
    public void addGoal(String name) throws NameErrorException {
        for (Goal g: goals) {
            if (g.getName().equals(name)) {
                throw new NameErrorException();
            } 
        }
        Goal goal = new Goal(name);
        goals.add(goal);
    }

    //MODIFIES: this
    //EFFECTS: removes the goal from the LongTermModule's goals if the goal's name is in the list, otherwise exception
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

    //EFFECTS: returns the goal that has the same name as the name, otherwise throw an exception
    public Goal findGoal(String name) throws NameErrorException {
        for (Goal g: goals) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        throw new NameErrorException();
    }


}
