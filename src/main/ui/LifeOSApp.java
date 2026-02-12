package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Goal;
import model.LongTerm;
import model.NameErrorException;
import model.ShortTerm;
import model.Task;

public class LifeOSApp {
    private LongTerm longTerm;
    private ShortTerm shortTerm;
    private Scanner input;
    

    public LifeOSApp() throws NameErrorException {
        runLifeOSApp();
    }

    public void runLifeOSApp() throws NameErrorException {
        boolean keepGoing = true;
        init();

        while (keepGoing) {
            displayMenu();
            String cmd = input.next();
            cmd = cmd.toLowerCase();

            if (cmd.equals("Q")) {
                keepGoing = false;
            } else {
                processCommand(cmd);
                
            }
        }
        System.out.println("Goodbye!");
    }

    private void processCommand(String cmd) throws NameErrorException {
        if (cmd.equals("l")) {
            startLongTerm();
        } else if (cmd.equals("s")) {
            startShortTerm();
        } else if (cmd.equals("w")) {
            startWeeklySchedule();
        } else {
            System.out.println("Section does not exist...");
        }
    }

    private void init() {
        longTerm = new LongTerm();
        shortTerm = new ShortTerm();
        input = new Scanner(System.in);
    }

    private void displayMenu() {
        System.out.println("Select from:  ");
        System.out.println("\tL -> LongTermModule");
        System.out.println("\tS -> ShortTermModule");
        System.out.println("\tW -> WeeklyScheduleModule");
        System.out.println("\tQ -> Quit the app");
    }
    //First start the app and there's a welcome
    //then three options to choose
    //'L' to enter the LongTermModule
    //'S' to enter the ShortTermModule
    //'W' to enter the WeeklyScheduleModule
    //'Q' to quit the app

    private void startLongTerm() throws NameErrorException {
        System.out.println("Select from:  ");
        System.out.println("\tV -> View the goal list");
        System.out.println("\tA -> add a goal to the goal list");
        System.out.println("\tR -> remove a goal from the goal list");
        System.out.println("\tB -> Go back to the main menu");
        String choice = input.next().toLowerCase();
        if (choice.equals("v")) {
            viewGoals();
        } else if (choice.equals("a")) {
            addGoalToLongTerm();
        } else if (choice.equals("r")) {
            removeGoalFromLongTerm();
        } else if (choice.equals("b")) {
            displayMenu();

        }
    }
        //if 'L'
        //'V' to view the longTermGoal List
        //'A' to add a new goal to the goal list
        //'R' to remove a goal from the goal list
        //'B' to go back to the main menu

    public void viewGoals() {
        System.out.println("Your Goals:    ");
        ArrayList<Goal> goals = longTerm.getGoals();
        for (Goal g: goals) {
            System.out.println(" " + g.getName());
        }
    }


    private void addGoalToLongTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Goal you want to add!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next().toLowerCase();
        } 
        longTerm.addGoal(name);
        System.out.println("Goal added successfully!");
    }

    private void removeGoalFromLongTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Goal you want to remove!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next().toLowerCase();
        } 
        longTerm.removeGoal(name);
        System.out.println("Goal removed successfully!");
    }

    public void viewTasks() {
        System.out.println("Your Tasks:    ");
        ArrayList<Task> tasks = shortTerm.getTasks();
        for (Task t: tasks) {
            System.out.println(" " + t.getName());
        }           
        System.out.println("Enter a task name to view details: ");
        selectTasks();
    }
    

    public void selectTasks() {
        String name = input.next().toLowerCase();
        ArrayList<Task> tasks = shortTerm.getTasks();
        Task found = null;
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                found = t;
                break;
            }   
        }

        if (found != null) {
            System.out.println("Task" + found.getName() + "'s information is here!'");
            System.out.println("Name: " + found.getName());
            System.out.println("EnergyLevel: " + found.getEnergyLevel());
            System.out.println("LinkedGoal: " + found.getLinkedGoal());
            System.out.println("CompleteStatus: " + found.getCompleteStatus());
            System.out.println("Deadline: " + found.getDeadline());
            System.out.println("Times: " + found.getTimes());
            taskSettingMenu(found); 
        } else {
            System.out.println("Cannot find this task!");
        }
    }

    public void taskSettingMenu(Task task) {
        System.out.println("Select from:  ");
        System.out.println("\tn -> set name");
        System.out.println("\te -> set energylevel");
        System.out.println("\tl -> set linked goal");
        System.out.println("\tc -> mark goal as completed");
        System.out.println("\tu -> mark goal as uncompleted");
        System.out.println("\td -> set deadline");
        System.out.println("\tt -> set times");
        String choice = input.next().toLowerCase();
        modifyTask(choice, task);
    }

    public void modifyTask(String choice, Task task) {
        switch (choice) {
            case "n":
                setName(task);
                break;
            case "e":
                setEnergyLevel(task);
                break;
            case "l":
                setLinkedGoals(task);
                break;
            case "c":
                setAsCompleted(task);
                break;
            case "u":
                setAsUnCompleted(task);
                break;
            case "d":
                setDeadline(task);
                break;
            case "t":
                setTimes(task);
                break;
        }
    }

    public void setName(Task task) {

    }

    public void setEnergyLevel(Task task) {

    }

    public void setLinkedGoals(Task task) {

    }


    public void setAsCompleted(Task task) {

    }


    public void setAsUnCompleted(Task task) {

    }


    public void setDeadline(Task task) {

    }


    public void setTimes(Task task) {

    }


    public void startShortTerm() throws NameErrorException {
        System.out.println("Select from:  ");
        System.out.println("\tV -> View the task list");
        System.out.println("\tA -> add a task to the task list");
        System.out.println("\tR -> remove a task from the task list");
        System.out.println("\tB -> Go back to the main menu");

        String choice = input.next().toLowerCase();
        if (choice.equals("v")) {
            viewTasks();
        } else if (choice.equals("a")) {
            addTaskToShortTerm();
        } else if (choice.equals("r")) {
            removeGoalFromShortTerm();
        } else if (choice.equals("b")) {
            displayMenu();
        }
    }

        
    public void addTaskToShortTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Task you want to add!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        shortTerm.addTask(name);
        System.out.println("Task added successfully!");
    }

    public void removeGoalFromShortTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Task you want to remove!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        shortTerm.removeTask(name);
        System.out.println("Task removed successfully!");

    }
        
        //if 'S'
    //'V' to view the shortTermTask List
    //'A' to add a new task to the task list
    //'R' to remove a task from the task list
    //'B' to go back to the main menu

    private void startWeeklySchedule() {
        
    }

        
    //if 'W'
    //'V' to view the timeBlocks List
    //'A' to add a timeBlock to the timeBlock list
    //'M' to view the tasks that matches the energyLevel of the selected timeBlock

}

    //if 'LV'
    //'N' to see the next goal
    //'NM' to reset the name of the goal
    //'L' to add linkedTask
    //'R' to remove linkedTask
    //'C' to mark goal as completed
    //'U' to mark goal as uncompleted

    //if 'SV'
    //'N' to see the next task
    //'N' to reset the name of the task
    //'L' to add linkedGoal
    //'E' to set energyLevel
    //'R' to remove linkedGoal
    //'C' to mark task as completed
    //'U' to mark task as uncompleted
    //'D' to set deadline
    //'T' to set times

     //if 'WV'
    //'N' to see the next timeBlock
    //'P' to set the time period
    //'E' to set energyLevel
    //'D' to set dayOfWeek






    

