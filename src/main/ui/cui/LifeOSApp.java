package ui.cui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Goal;
import model.LongTerm;
import model.ShortTerm;
import model.Task;
import model.WorkUnit;
import model.exception.NameErrorException;

import persistence.JsonReader;
import persistence.JsonWriter;

// Represents the LifeOS application
@ExcludeFromJacocoGeneratedReport
public class LifeOSApp {
    private static final String JSON_STORE_LONG = "./data/longTerm.json";
    private static final String JSON_STORE_SHORT = "./data/shortTerm.json";
    private LongTerm longTerm;
    private ShortTerm shortTerm;
    private Scanner input;
    private JsonWriter jsonWriterLong;
    private JsonWriter jsonWriterShort;
    
    // MODIFIES: this
    //EFFECTS: constructs longTerm and shortTerm and runs the LifeOSApp
    public LifeOSApp() throws NameErrorException {
        input = new Scanner(System.in);
        longTerm = new LongTerm("My long term");
        shortTerm = new ShortTerm("My short term");
        jsonWriterLong = new JsonWriter(JSON_STORE_LONG);
        jsonWriterShort = new JsonWriter(JSON_STORE_SHORT);
        runLifeOSApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runLifeOSApp() throws NameErrorException {
        boolean keepGoing = true;
        init();

        while (keepGoing) {
            displayMenu();
            String cmd = input.next();
            cmd = cmd.toLowerCase();

            if (cmd.equals("q")) {
                saveChoice();
                keepGoing = false;
            } else {
                processCommand(cmd);
                
            }
        }
        System.out.println("Goodbye!");
    }
    
    // EFFECTS: asks the user whether to save before quitting the app
    private void saveChoice() {
        System.out.println("Do you want to save your progress before quitting? (y/n)");
        String choice = input.next().toLowerCase();

        if (choice.equals("y")) {
            saveLongTerm();
            saveShortTerm();
            System.out.println("Progress saved!");
        } 
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String cmd) throws NameErrorException {
        if (cmd.equals("l")) {
            startLongTerm();
        } else if (cmd.equals("s")) {
            startShortTerm();
        } else if (cmd.equals("p")) {
            load();
        } else if (cmd.equals("q")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Section does not exist...");
        }
    }

    //MODIFIES: this
    // EFFECTS: initializes input scanner
    private void init() {
        input = new Scanner(System.in);
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Welcome to LifeOS!");
        System.out.println("Select from:  ");
        System.out.println("\tL -> LongTermModule");
        System.out.println("\tS -> ShortTermModule");
        System.out.println("\tP -> Load the previous status");
        System.out.println("\tQ -> Quit the app");
    }

    
    //MODIFIES: this
    //EFFECTS: shows long-term goal menu
    private void startLongTerm() throws NameErrorException {
        while (true) {
            displayGoals();
            System.out.println("Select from:  ");
            System.out.println("\tType a goal's name -> view a goal's details");
            System.out.println("\tA -> add a goal to the goal list");
            System.out.println("\tR -> remove a goal from the goal list");
            System.out.println("\tS -> save long term to file");
            System.out.println("\tB -> Go back to the previous menu");
            
            String choiceInitial = input.next();
            String choice = choiceInitial.toLowerCase();
            if (choice.equals("a")) {
                addGoalToLongTerm();
            } else if (choice.equals("r")) {
                removeGoalFromLongTerm();
            } else if (choice.equals("s")) {
                saveLongTerm();
            } else if (choice.equals("b")) {
                return;
            } else {
                selectGoals(choiceInitial);
            }
        }
    }
    
    // EFFECTS: displays the names of the goals in longTerm
    private void displayGoals() {
        System.out.println("Your Goals:    ");
        ArrayList<Goal> goals = longTerm.getGoals();
        for (Goal g: goals) {
            System.out.println(" " + g.getName());
        }           
    }

    //MODIFIES: this
    //EFFECTS: adds a new goal with given name if the goal does not exists, otherwise goes back to goal setting menu
    private void addGoalToLongTerm() {
        System.out.println("Please enter the name of the Goal you want to add!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        try {
            longTerm.addGoal(name);
            Goal goal = longTerm.findGoal(name);
            System.out.println("Goal added successfully!");
            goalSettingMenu(goal);
        } catch (NameErrorException e)  {
            System.out.println("Goal already exist!");
        }

    }

    //MODIFIES: this
    //EFFECTS: removes the goal with the name if it can be found
    private void removeGoalFromLongTerm() {
        System.out.println("Please enter the name of the Goal you want to remove!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next().toLowerCase();
        } 
        try {
            longTerm.removeGoal(name);
            System.out.println("Goal removed successfully!");
        } catch (NameErrorException e)  {
            System.out.println("Cannot find this goal!");
        }
    }
    
    //MODIFIES: this
    //EFFECTS: prints goal info if goal can be found
    public void selectGoals(String choice) throws NameErrorException {
        ArrayList<Goal> goals = longTerm.getGoals();
        Goal found = null;
        for (Goal g : goals) {
            if (g.getName().equals(choice)) {
                found = g;
                break;
            }   
        }
        if (found != null) {
            printGoalInfo(found);
        } else {
            System.out.println("Cannot find this goal!");
        }
    }
    
    //MODIFIES: this
    //EFFECTS: prints complete information of the goal and goes to goal setting menu  
    public void printGoalInfo(Goal found) throws NameErrorException {
        System.out.println("Goal " + found.getName() + "'s information is here!'");
        System.out.println("Name: " + found.getName());
        System.out.println("linkedTasks: " + found.getLinkedTaskNames());
        System.out.println("CompleteStatus: " + found.getCompleteStatus());
        goalSettingMenu(found); 
        
    }

    //MODIFIES: goal
    //EFFECTS: processes user commands to modify goal
    public void goalSettingMenu(Goal goal) throws NameErrorException {
        Boolean stay = true;
        while (stay) {
            System.out.println("Select from:  ");
            System.out.println("\tn -> set name");
            System.out.println("\tl -> set linked tasks");
            System.out.println("\tr -> remove linked tasks");
            System.out.println("\tc -> mark goal as completed");
            System.out.println("\tu -> mark goal as uncompleted");
            System.out.println("\ti -> to see information of this goal");
            System.out.println("\tb -> to go back");
            String choice = input.next().toLowerCase();
            if (choice.equals("b")) {
                return;
            }
            modifyGoal(choice, goal);
        }
    }

    //MODIFIES: goal
    //EFFECTS: modify the goal based on user choice
    public void modifyGoal(String choice, Goal goal) throws NameErrorException {
        switch (choice) {
            case "n":
                setName(goal);
                break;
            case "l":
                setLinkedTasks(goal);
                break;
            case "r":
                removeLinkedTask(goal);
                break;
            case "c":
                setAsCompleted(goal);
                break;
            case "u":
                setAsUnCompleted(goal);
                break;
            case "i":
                printGoalInfo(goal);
                break;
            case "b":
                return;
        }
    }

    //MODIFIES: goal, shortTerm
    //EFFECTS: links a task to the goal if task can be found, otherwise add it to the shortTerm
    public void setLinkedTasks(Goal goal) throws NameErrorException {
        System.out.println("Please enter a short term task's name to link it to the goal ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        if (shortTerm.findTask(name) == null) {
            shortTerm.addTask(name);
        }
        Task foundTask = shortTerm.findTask(name);
        goal.setLinkedTask(foundTask);
        foundTask.setLinkedGoal(goal);
        System.out.println("Set the link successfully!");
    }

    //MODIFIES: this, goal
    //EFFECTS: removes a task from the goal if task can be found
    public void removeLinkedTask(Goal goal) {
        while (true) {
            System.out.println("Type a task's name -> remove it from the linkedTasks (B to go back)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("b")) {
                return;
            }
            while (choice.length() == 0) {
                System.out.println("Invalid name! Please try another name!");
                choice = input.next();
            }
            Task foundTask = null;
            for (Task t: goal.getLinkedTasks()) {
                if (t.getName().equals(choice)) {
                    foundTask = t;
                }
            }
            if (foundTask == null) {
                System.out.println("Cannot find this task!");
                continue;
            }
            removeLinks(goal, foundTask);
            return;
        }
    }

    //MODIFIES: goal, foundTask
    //EFFECTS: removes a task from the goal if task can be found
    private void removeLinks(Goal goal, Task foundTask) {
        goal.removeLinkedTask(foundTask);
        foundTask.setLinkedGoal(null);
        System.out.println("Removed the link successfully!");
    }
    

    // MODIFIES: this
    // EFFECTS: shows shortTerm goal menu
    public void startShortTerm() throws NameErrorException {
        while (true) {  
            displayTasks();
            System.out.println("Select from:  ");
            System.out.println("Type a task's name -> view a task's details");
            System.out.println("\tA -> add a task to the task list");
            System.out.println("\tR -> remove a task from the task list");
            System.out.println("\tS -> save short term to file");
            System.out.println("\tL -> load short term from file");
            System.out.println("\tB -> Go back to the previous menu");
            String choiceInitial = input.next();
            String choice = choiceInitial.toLowerCase();
            if (choice.equals("a")) {
                addTaskToShortTerm();
            } else if (choice.equals("r")) {
                removeTaskFromShortTerm();
            } else if (choice.equals("s")) {
                saveShortTerm();
            } else if (choice.equals("b")) {
                return;
            } else {
                selectTasks(choiceInitial);
            }
        }
    }

    //EFFECTS: displays the names of the tasks in shortTerm
    private void displayTasks() {
        System.out.println("Your Tasks:    ");
        ArrayList<Task> tasks = shortTerm.getTasks();
        for (Task t: tasks) {
            System.out.println(" " + t.getName());
        }         
    }

    //MODIFIES: this
    //EFFECTS: adds a new task with given name if the task does not exist, otherwise goes back to task setting menu
    public void addTaskToShortTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Task you want to add!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        try {
            shortTerm.addTask(name);
            Task task = shortTerm.findTask(name);
            System.out.println("Task added successfully!");
            taskSettingMenu(task);
        } catch (NameErrorException e)  {
            System.out.println("Task already exist!");
        }

    }

    //MODIFIES: this
    //EFFECTS: removes the task with the name if it can be found
    public void removeTaskFromShortTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Task you want to remove!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        try {
            shortTerm.removeTask(name);
            System.out.println("Task removed successfully!");
        } catch (NameErrorException e)  {
            System.out.println("Cannot find this task!");
        }

    }
    
    //MODIFIES: this
    //EFFECTS: prints task info if the task can be found
    public void selectTasks(String choice) throws NameErrorException {
        ArrayList<Task> tasks = shortTerm.getTasks();
        Task found = null;
        for (Task t : tasks) {
            if (t.getName().equals(choice)) {
                found = t;
                break;
            }   
        }
        if (found != null) {
            printTaskInfo(found);
        } else {
            System.out.println("Cannot find this task!");
        }
    }

    //MODIFIES: this
    //EFFECTS: prints complete information of the task and goes to task setting menu  
    public void printTaskInfo(Task found) throws NameErrorException {
        System.out.println("Task " + found.getName() + "'s information is here!'");
        System.out.println("Name: " + found.getName());
        System.out.println("EnergyLevel: " + found.getEnergyLevel());
        if (found.getLinkedGoal() != null) {
            System.out.println("LinkedGoal: " + found.getLinkedGoal().getName());
        } else {
            System.out.println("LinkedGoal: " + "null");
        }
        System.out.println("CompleteStatus: " + found.getCompleteStatus());
        System.out.println("Deadline: " + found.getDeadline());     
        System.out.println("Times: " + found.getTimes());
        taskSettingMenu(found); 
    }
    
    //MODIFIES: this
    //EFFECTS: processes user commands to modify task
    public void taskSettingMenu(Task task) throws NameErrorException {
        Boolean stay = true;
        while (stay) {
            System.out.println("Select from:  ");
            System.out.println("\tn -> set name");
            System.out.println("\te -> set energylevel");
            System.out.println("\tl -> set linked goal");
            System.out.println("\tc -> mark task as completed");
            System.out.println("\tu -> mark task as uncompleted");
            System.out.println("\td -> set deadline");
            System.out.println("\tt -> set times");
            System.out.println("\ti -> to see information of this task");
            System.out.println("\tb -> to go back");
            String choice = input.next().toLowerCase();
            if (choice.equals("b")) {
                return;
            }
            modifyTask(choice, task);
        }
    }

    //MODIFIES: task
    //EFFECTS: modify the task based on user choice
    public void modifyTask(String choice, Task task) throws NameErrorException {
        switch (choice) {
            case "n": setName(task);
                break;
            case "e": setEnergyLevel(task);
                break;
            case "l": setLinkedGoal(task);
                break;
            case "c": setAsCompleted(task);
                break;
            case "u": setAsUnCompleted(task);
                break;
            case "d": setDeadline(task);
                break;
            case "t": setTimes(task);
                break;
            case "i": printTaskInfo(task);
                break;
            case "b": return;
        }
    }

    //MODIFIES: unit
    //EFFECTS: sets a new name for the unit
    public void setName(WorkUnit unit) {
        System.out.println("Please enter the task name: ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        unit.setName(name);
        System.out.println("Name changed successfully!");
    }

    //MODIFIES: task
    //EFFECTS: sets energy level between 1–5
    public void setEnergyLevel(Task task) {
        System.out.println("Please choose an energylevel (from 1 to 5): ");
        int energylevel = input.nextInt();
        while (energylevel < 1 || energylevel > 5) {
            System.out.println("Invalid number! Please try another name!");
            energylevel = input.nextInt();
        }
        task.setEnergyLevel(energylevel);
        System.out.println("Energylevel set successfully!");
    }

    //MODIFIES: task, foundGoal, longTerm
    //EFFECTS: links a goal to the task if goal can be found, otherwise add it to longTerm
    public void setLinkedGoal(Task task) throws NameErrorException {
        System.out.println("Please enter a long term goal's name to link it to the task ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        if (longTerm.findGoal(name) == null) {
            longTerm.addGoal(name);
        }
        Goal foundGoal = longTerm.findGoal(name);
        task.setLinkedGoal(foundGoal);
        foundGoal.setLinkedTask(task);
        System.out.println("Set the link successfully!");
            
    }
    

    //MODIFIES: unit
    //EFFECTS: sets the unit as completed
    public void setAsCompleted(WorkUnit unit) {
        unit.setAsCompleted();
        System.out.println("This task has been marked as completed!");
    }

    //MODIFIES: unit
    //EFFECTS: sets the unit as uncompleted
    public void setAsUnCompleted(WorkUnit unit) {
        unit.setAsUncompleted();
        System.out.println("This task has been marked as uncompleted!");
    }

    // MODIFIES: task
    // EFFECTS: set the deadline if it is valid
    public void setDeadline(Task task) {
        System.out.println("Please set an deadline (example: 0212, which means Feb 12): ");
        String deadline = input.next();
        if (isValidDate(deadline)) {
            task.setDeadline(deadline);
            System.out.println("Deadline set successfully!");
        } else {
            System.out.println("Invalid date! Please enter a new deadline!");
        }
    }

    // EFFECTS: returns true if deadline is a valid date number, otherwise false    
    public boolean isValidDate(String deadline) {
        int date = 0;
        try {
            date = Integer.parseInt(deadline);
        } catch (NumberFormatException e) {
            return false;
        }
        if (date < 101 || date > 1231) {
            return false;
        }
        int monthNum = date / 100;
        int dayNum = date % 100;
        if ((monthNum < 1 || monthNum > 12 || dayNum < 1) || (monthNum == 2 && dayNum > 29) 
                || ((monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) && dayNum > 30) 
                || (dayNum > 31)) {
            return false;
        }
        return true;
    }

    // MODIFIES: task
    // EFFECTS: sets times for the task if time is not 0
    public void setTimes(Task task) {
        System.out.println("Please enter the times to do this task: ");
        int times = input.nextInt();
        while (times == 0) {
            System.out.println("Invalid times! Please try another times!");
            times = input.nextInt();
        }
        task.setTimes(times);
        System.out.println("Times set successfully!");
    }

    // EFFECTS: saves the longTerm to file
    private void saveLongTerm() {
        try {
            jsonWriterLong.open();
            jsonWriterLong.write(longTerm);
            jsonWriterLong.close();
            System.out.println("Saved " + longTerm.getName() + " to " + JSON_STORE_LONG);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_LONG);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void load() {
        
        JsonReader jsonReaderLong = new JsonReader(JSON_STORE_LONG);
        JsonReader jsonReaderShort = new JsonReader(JSON_STORE_SHORT);
        
        try {
            longTerm = jsonReaderLong.readLongTerm();
            System.out.println("Loaded " + longTerm.getName() + " from " + JSON_STORE_LONG);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_LONG);
        }

        try {
            shortTerm = jsonReaderShort.readShortTerm();
            System.out.println("Loaded " + shortTerm.getName() + " from " + JSON_STORE_SHORT);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SHORT);
        }
        jsonReaderShort.setLinks(longTerm.getGoals());
        System.out.println("Links betwen longTerm and shortTerm set successfully!");
    }
    

    // EFFECTS: saves the longTerm to file
    private void saveShortTerm() {
        try {
            jsonWriterShort.open();
            jsonWriterShort.write(shortTerm);
            jsonWriterShort.close();
            System.out.println("Saved " + shortTerm.getName() + " to " + JSON_STORE_SHORT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SHORT);
        }
    }

    



}







    

