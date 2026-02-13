package model;

import java.util.ArrayList;
/**
 * Sets the name and complete status for the goal, add or remove linkedTask of the goal
 */

public class Goal implements WorkUnit {
    private String name;
    private ArrayList<Task> linkedTasks;
    private boolean completeStatus;
 
    //EFFECTS: constructs an uncompleted Goal object
    public Goal(String name) {
        this.name = name;
        linkedTasks = new ArrayList<>();
        completeStatus = false;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add linkedTasks to the Goal's linkedTasks if the linkedTask's name is not in the list
    public void setLinkedTask(Task task) {
        if (!linkedTasks.contains(task)) {
            linkedTasks.add(task);
        }
    }
    
    //EFFECTS: returns the name list of linkedTasks
    public ArrayList<String> getLinkedTaskNames() {
        ArrayList<String> nameList = new ArrayList<>();
        for (Task t: linkedTasks) {
            nameList.add(t.getName());
        }
        return nameList;
    }

    //MODIFIES: this
    //EFFECTS: remove linkedTask from the Goal's linkedTasks if the linkedTask's name is in the list
    public void removeLinkedTask(Task task) {
        if (linkedTasks.contains(task)) {
            linkedTasks.remove(task);
        }
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<Task> getLinkedTasks() {
        return linkedTasks;
    }

    @Override
    public void markAsCompleted() {
        completeStatus = true;
    }

    @Override
    public void markAsUncompleted() {
        completeStatus = false;
    }

    public boolean getCompleteStatus() {
        return completeStatus;
    }

   
}
