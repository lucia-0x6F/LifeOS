package model;

import java.util.ArrayList;

import model.exception.NameErrorException;

/**
 * Add or remove task, find a task that has a specific name, view the task list 
 */
 
public class ShortTerm {
    private ArrayList<Task> tasks;

    //EFFECTS: construct a ShortTermModule object with no tasks 
    public ShortTerm() {
        tasks = new ArrayList<Task>();
    }
    

    //MODIFIES: this
    //EFFECTS: add task to the ShortTerm's tasks if the Task's name is not in the list, otherwise exception
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
    //EFFECTS: removes task from the ShortTermModule's tasks if the Task is in the list, otherwise exception
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

    //EFFECTS: returns the task that has the same name as the name, otherwise throw an exception
    public Task findTask(String name) throws NameErrorException {
        for (Task t: tasks) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new NameErrorException();
    }

    
}
