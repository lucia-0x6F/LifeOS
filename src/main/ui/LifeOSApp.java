package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Goal;
import model.LongTerm;
import model.NameErrorException;
import model.ShortTerm;
import model.Task;
import model.WorkUnit;

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

            if (cmd.equals("q")) {
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
        } else if (cmd.equals("q")) {
            System.out.println("Goodbye!");
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
        System.out.println("Welcome to LifeOS!");
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
            } else if (choice.equals("a")) {
                addGoalToLongTerm();
            } else if (choice.equals("r")) {
                removeGoalFromLongTerm();
            } else if (choice.equals("b")) {
                return;

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
        System.out.println("S -> Select a goal and view details: ");
        System.out.println("B -> Go back to the goal setting menu");
        String choice = input.next().toLowerCase();
        if (choice.equals("s")) {
            System.out.println("Enter a goal's name: ");
            choice = input.next();
            selectGoals(choice);
        } else {
            return;
        }
    }

    private void addGoalToLongTerm() throws NameErrorException {
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
    

   
    
    

    public void selectGoals(String choice) {
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
    
    public void printGoalInfo(Goal found) {
        System.out.println("Goal " + found.getName() + "'s information is here!'");
        System.out.println("Name: " + found.getName());
        System.out.println("linkedTasks: " + found.getLinkedTaskNames());
        System.out.println("CompleteStatus: " + found.getCompleteStatus());
        goalSettingMenu(found); 
        
    }

    public void goalSettingMenu(Goal goal) {
        Boolean stay = true;
        while (stay) {
            System.out.println("Select from:  ");
            System.out.println("\tn -> set name");
            System.out.println("\tl -> set linked tasks");
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

    public void modifyGoal(String choice, Goal goal) {
        switch (choice) {
            case "n":
                setName(goal);
                break;
            case "l":
                setLinkedTasks(goal);
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

    public void setLinkedTasks(Goal goal) {
        System.out.println("Please enter a short term task's name to link it to the goal ");
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        try {
            Task foundTask = shortTerm.findTask(name);
            goal.setLinkedTask(foundTask);
            System.out.println("Set the linkedTask successfully!");
        } catch (NameErrorException e) {
            System.out.println("Cannot find this task!");
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
        String choice = input.next().toLowerCase();
        if (choice.equals("s")) {
            System.out.println("Enter a task's name: ");
            choice = input.next();
            selectTasks(choice);
        } else {
            return;
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
            printTaskInfo(found);
        } else {
            System.out.println("Cannot find this task!");
        }
    }
    
    public void printTaskInfo(Task found) {
        System.out.println("Task " + found.getName() + "'s information is here!'");
        System.out.println("Name: " + found.getName());
        System.out.println("EnergyLevel: " + found.getEnergyLevel());
        System.out.println("LinkedGoal: " + found.getLinkedGoal().getName());
        System.out.println("CompleteStatus: " + found.getCompleteStatus());
        System.out.println("Deadline: " + found.getDeadline());     
        System.out.println("Times: " + found.getTimes());
        taskSettingMenu(found); 
    }
    

    public void taskSettingMenu(Task task) {
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

    public void modifyTask(String choice, Task task) {
        switch (choice) {
            case "n": setName(task);
                break;
            case "e": setEnergyLevel(task);
                break;
            case "l": setLinkedGoals(task);
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
        String name = input.next();
        while (name.length() == 0) {
            System.out.println("Invalid name! Please try another name!");
            name = input.next();
        }
        try {
            Goal foundGoal = longTerm.findGoal(name);
            task.setLinkedGoal(foundGoal);
            System.out.println("Set the linkedGoal successfully!");
        } catch (NameErrorException e) {
            System.out.println("Cannot find this goal!");
        }
    }


    public void setAsCompleted(WorkUnit unit) {
        unit.markAsCompleted();
        System.out.println("This task has been marked as completed!");
    }


    public void setAsUnCompleted(WorkUnit unit) {
        unit.markAsUncompleted();
        System.out.println("This task has been marked as uncompleted!");
    }


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
                return;
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
            Task task = shortTerm.findTask(name);
            System.out.println("Task added successfully!");
            taskSettingMenu(task);
        } catch (NameErrorException e)  {
            System.out.println("Task already exist!");
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
        

    private void startWeeklySchedule() {
        
    }

        
    //if 'W'
    //'V' to view the timeBlocks List
    //'A' to add a timeBlock to the timeBlock list
    //'M' to view the tasks that matches the energyLevel of the selected timeBlock

}

     //if 'WV'
    //'N' to see the next timeBlock
    //'P' to set the time period
    //'E' to set energyLevel
    //'D' to set dayOfWeek






    

