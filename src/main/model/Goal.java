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

    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add linkedTasks to the Goal's linkedTasks if the linkedTask's name is not in the list
    public void addLinkedTask(Task task) {
            if (!linkedTasks.contains(task)) {
                linkedTasks.add(task);
        }
    }

    //REQUIRES: linkedTasks cannot empty
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

    public boolean getCompleteStatus() {
        return completeStatus;
    }
}
