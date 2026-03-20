package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.exception.NameErrorException;
import persistence.Writable;

/**
 * Add or remove goal, find a goal that has a specific name, view the goal list 
 */

public class LongTerm implements Writable {
    private ArrayList<Goal> goals;
    private String name;
    
    //EFFECTS: construct a LongTermModule object with no goals
    public LongTerm(String name) {
        this.name = name;
        goals = new ArrayList<Goal>();

    }

    public String getName() {
        return name;
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
    //EFFECTS: add the goal to the LongTerm's goals
    public void addGoal(Goal goal) {
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

    //EFFECTS: returns the goal if the name can be found, otherwise returns null
    public Goal findGoal(String name) {
        for (Goal g: goals) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("goals", goalsToJson());
        return json;
    }

    // EFFECTS: returns goals in this longTerm as a JSON array
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g : goals) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

}
