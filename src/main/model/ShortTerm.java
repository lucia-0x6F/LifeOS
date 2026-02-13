package model;

import java.util.ArrayList;

public class ShortTerm {
    private ArrayList<Task> tasks;

    //EFFECTS: construct a ShortTermModule object
    public ShortTerm() {
        tasks = new ArrayList<Task>();
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks if the Task's name is not in the list
    public void addTask(String name) throws NameErrorException {
        for (Task t: tasks) {
            if (t.getName().equals(name)) {
                throw new NameErrorException();
            } 
        }
        Task task = new Task(name);
        tasks.add(task);
    }

    //REQUIRES: tasks cannot be empty
    //MODIFIES: this
    //EFFECTS: removes task from the ShortTermModule's tasks if the Task's name is in the list
    public void removeTask(String name) throws NameErrorException {
        Task removeTarget = null;
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                removeTarget = t;
                break;
            }
        }
        if (removeTarget != null) {
            tasks.remove(removeTarget);
        } else {
            throw new NameErrorException();
        }
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    } 

    //EFFECTS: returns the task that has the same name as the name
    public Task findTask(String name) throws NameErrorException {
        for (Task t: tasks) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new NameErrorException();
    }

    
}
