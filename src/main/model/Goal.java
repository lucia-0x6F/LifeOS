package model;

import java.util.ArrayList;

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

    //MODIFIES: this
    //EFFECTS: sets the the name as Goal's name 
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add linkedTasks to the Goal's linkedTasks if the linkedTask's name is not in the list
    public void addLinkedTask(Task task) {
        for (Task t: linkedTasks) {
            if (!linkedTasks.contains(task)) {
                linkedTasks.add(task);
            }
        }
    }

    //REQUIRES: linkedTasks cannot empty
    //MODIFIES: this
    //EFFECTS: remove linkedTask from the Goal's linkedTasks if the linkedTask's name is in the list
    public void removeLinkedTask(Task task) {
        for (Task t: linkedTasks) {
            if (linkedTasks.contains(task) && t.getName().equals(task.getName())) {
                linkedTasks.remove(t);
            }
        }
    }

    //EFFECTS: returns the Goal's name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: returns the Goal's linkedTasks
    public ArrayList<Task> getLinkedTasks() {
        return linkedTasks;
    }

    //MODIFIES: this
    //EFFECTS: sets the Goal as completed
    public void markAsCompleted() {
        completeStatus = true;
    }

    //MODIFIES: this
    //EFFECTS: sets the Goal as uncompleted
    public void markAsUncompleted() {
        completeStatus = false;
    }

    //EFFECTS: returns the Goal's completeStatus
    public boolean getCompleteStatus() {
        return completeStatus;
    }
}
