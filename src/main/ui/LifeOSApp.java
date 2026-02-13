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
        Boolean stay = true;
        while (stay) {
            System.out.println("Select from:  ");
            System.out.println("\tV -> View the goal list");
            System.out.println("\tA -> add a goal to the goal list");
            System.out.println("\tR -> remove a goal from the goal list");
            System.out.println("\tB -> Go back to the main menu");
        
            String choice = input.next().toLowerCase();
            if (choice.equals("v")) {
                viewGoals();
                break;
            } else if (choice.equals("a")) {
                addGoalToLongTerm();
                break;
            } else if (choice.equals("r")) {
                removeGoalFromLongTerm();
                break;
            } else if (choice.equals("b")) {
                displayMenu();
                break;

            }
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
        try {
            longTerm.removeGoal(name);
            System.out.println("Goal removed successfully!");
        } catch (NameErrorException e)  {
            System.out.println("Cannot find this goal!");
        }

    }

    private void removeGoalFromLongTerm() throws NameErrorException {
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

    public void viewTasks() {
        System.out.println("Your Tasks:    ");
        ArrayList<Task> tasks = shortTerm.getTasks();
        for (Task t: tasks) {
            System.out.println(" " + t.getName());
        }           
        System.out.println("S -> Select a task and view details: ");
        System.out.println("B -> Go back to the task setting menu");
        String choice = input.next();
        if (choice.equals("S")) {
            System.out.println("Enter a task's name: ");
            choice = input.next();
            selectTasks(choice);
        } else {
            displayMenu();
        }
    }
    

    public void selectTasks(String choice) {
        ArrayList<Task> tasks = shortTerm.getTasks();
        Task found = null;
        for (Task t : tasks) {
            if (t.getName().equals(choice)) {
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
            System.out.println("Deadline: " + found.getDeadline());     //TODO: fix the deadline's initial setting
            System.out.println("Times: " + found.getTimes());
            taskSettingMenu(found); 
        } else {
            System.out.println("Cannot find this task!");
        }
    }

    public void taskSettingMenu(Task task) {
        Boolean stay = true;
        while (stay) {
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
            case "b":
                taskSettingMenu(task);
                break;
        }
    }

    public void setName(Task task) {
        System.out.println("Please enter the task name: ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        task.setName(name);
        System.out.println("Name changed successfully!");
    }

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

    public void setLinkedGoals(Task task) {
        System.out.println("Please enter a long term goal's name to link it to the task ");
        String goalName = input.next();
        while (goalName.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            goalName = input.next();
        }
        try {
            Goal foundGoal = longTerm.findGoal(goalName);
            task.setLinkedGoal(foundGoal);
            System.out.println("Set the linkedGoal successfully!");
        } catch (NameErrorException e) {
            System.out.println("Cannot find this goal!");
        }
        System.out.println("Energylevel set successfully!");
    }


    public void setAsCompleted(Task task) {
        task.markAsCompleted();
        System.out.println("This task has been marked as completed!");
    }


    public void setAsUnCompleted(Task task) {
        task.markAsUncompleted();
        System.out.println("This task has been marked as uncompleted!");
    }


    public void setDeadline(Task task) {
        System.out.println("Please set an deadline (example: 0212, which means Feb 12): ");
        int deadline = input.nextInt();
        if (isValidDate(deadline)) {
            task.setDeadline(deadline);
            System.out.println("Deadline set successfully!");
        } else {
            System.out.println("Invalid date! Please enter a new deadline!");
        }
    }

    public Boolean isValidDate(int deadline) {
        if (deadline < 0101 || deadline > 1231) {
            return false;
        }
        int monthNum = deadline / 100;
        int dayNum = deadline % 100;

        if (monthNum == 2 && dayNum > 29) {
            return false;
        }
        if ((monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) && dayNum > 30) {
            return false;
        }
        return true;
    }     //TODO: use some tests to validate!



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


    public void startShortTerm() throws NameErrorException {
        Boolean stay = true;
        while (stay) {
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
    }

        
    public void addTaskToShortTerm() throws NameErrorException {
        System.out.println("Please enter the name of the Task you want to add!");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name!");
            name = input.next();
        } 
        try {
            shortTerm.addTask(name);
            System.out.println("Task added successfully!");
        } catch (NameErrorException e)  {
            System.out.println("Cannot find this task!");
        }

    }

    public void removeGoalFromShortTerm() throws NameErrorException {
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






    

