package ui.gui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.Event;
import model.EventLog;
import model.Goal;
import model.LongTerm;
import model.Task;
import model.ShortTerm;

import persistence.JsonReader;
import persistence.JsonWriter;
import model.exception.NameErrorException;

// Represents the graphical user interface of the LifeOS application.
@ExcludeFromJacocoGeneratedReport
public class MainFrame extends JFrame implements WindowListener {
    private JPanel backGround;
    private JPanel menuPanel;
    private JPanel shortTermPanel;
    private JPanel longTermPanel;
    private JPanel goalPanel;
    private JPanel taskPanel;
    private JLabel titleLabel;
    private JPanel panel;
    private JTextPane poemPanel;
    private JPanel poemContainer;
    private JLabel goalLabel;
    private JLabel taskLabel;
    private JTextArea text;

    private LongTerm longTerm;
    private JsonReader jsonReaderLong;
    private JPanel goalList;
    private JLabel goalName;
    private JLabel goalCompleteStatus;
    private JTextArea goalLinkedTasks;

    private ShortTerm shortTerm;
    private JsonReader jsonReaderShort;
    private JPanel taskList;
    private JLabel taskName;
    private JLabel taskCompleteStatus;
    private JLabel taskTimes;
    private JLabel taskDeadline;
    private JLabel taskEnergyLevel;
    private JTextArea taskLinkedGoal;
    
    private JTextField nameFieldGoal;
    private JCheckBox completeStatusGoal;
    private JDialog dialogGoal;
    private List<JCheckBox> taskBoxes;
    private JButton confirmGoal;

    private JTextField nameFieldTask;
    private JCheckBox completeStatusTask;
    private JTextField energyLevelTask;
    private JTextField timesTask;
    private JTextField deadlineTask;
    private JDialog dialogTask;
    private List<JRadioButton> goalBox;
    private JButton confirmTask;

    private int ypos;
    private JButton save;
    private JButton load;
    private JButton remove;
    private JButton edit;

    private JsonWriter jsonWriterLong;
    private JsonWriter jsonWriterShort;

    private Task task;
    private Goal goal;
    private JDialog dialogEdit;

    private static final Path DATA_DIRECTORY = Paths.get(
            System.getProperty("user.home"), "LifeOS", "data");
    private static final String JSON_STORE_LONG = DATA_DIRECTORY.resolve("LongTerm.json").toString();
    private static final String JSON_STORE_SHORT = DATA_DIRECTORY.resolve("ShortTerm.json").toString();
    
    //MODIFIES: this
    //EFFECTS: initializes jsonReader, jsonWriter and the panels
    public MainFrame() throws NameErrorException {
        jsonReaderLong = new JsonReader(JSON_STORE_LONG);
        jsonReaderShort = new JsonReader(JSON_STORE_SHORT);

        longTerm = new LongTerm("");
        shortTerm = new ShortTerm("");

        prepareDataFiles();
        save();
        load();
        init();
        shortTermPanel();
        longTermPanel();
        menuPanel();
        goalPanel();
        taskPanel();
        poemContainer();
        
        listPanel();

        setScrollPane(longTermPanel, goalList);
        setScrollPane(shortTermPanel, taskList);

        editButtonTask();
        editButtonGoal();

        mainFrame();
        actionPerformedLoad();
    }

    // MODIFIES: the user's LifeOS data directory
    // EFFECTS: creates the data directory and empty JSON files if necessary
    private void prepareDataFiles() {
        try {
            Files.createDirectories(DATA_DIRECTORY);
            Path longFile = DATA_DIRECTORY.resolve("LongTerm.json");
            Path shortFile = DATA_DIRECTORY.resolve("ShortTerm.json");
            if (Files.notExists(longFile)) {
                Files.writeString(longFile, "{\n    \"name\": \"\",\n    \"goals\": []\n}\n");
            }
            if (Files.notExists(shortFile)) {
                Files.writeString(shortFile, "{\n    \"name\": \"\",\n    \"tasks\": []\n}\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot create LifeOS data files", e);
        }
    }

    private void listPanel() {
        goalList = new JPanel();
        goalList.setLayout(new BoxLayout(goalList, BoxLayout.Y_AXIS));
        goalList.setOpaque(false);
        taskList = new JPanel();
        taskList.setLayout(new BoxLayout(taskList, BoxLayout.Y_AXIS));
        taskList.setOpaque(false);
    }
    
    //MODIFIES: this
    //EFFECTS: initializes the backGround panel, the main panel, adds the titleLabel
    public void init() {
        ImageIcon icon = new ImageIcon("LifeOS.png");
        this.setIconImage(icon.getImage());

        backGround = new JPanel();
        //backGround.setBackground(new Color(255, 236, 170));
        backGround.setBackground(new Color(0xF3E3AF));
        backGround.setBackground(new Color(0xFFF8E7));
        backGround.setLayout(null);
        backGround.setBounds(80, 28, 1380, 870);
        backGround.add(save);
        backGround.add(load);

        panel = new JPanel();
        panel.setBackground(new Color(255, 236, 170));
        panel.setBackground(new Color(0xD7C8AA));
        panel.setBackground(new Color(0xFFF8E7));
        panel.setLayout(null);

        titleLabel = new JLabel("Welcome to LifeOS");
        titleLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 65));
        titleLabel.setForeground(new Color(0x6D4C41));
        titleLabel.setBounds(70, 80, 600, 80);
    }

    //MODIFIES: this
    //EFFECTS: sets the shortTerm and longTerm panel
    public void shortTermPanel() {
        shortTermPanel = new JPanel();
        shortTermPanel.setBackground(new Color(0xE6D2B8));
        shortTermPanel.setBounds(340, 250, 290, 270);
        shortTermPanel.setBounds(75, 250, 290, 270);
        shortTermPanel.setLayout(null);
        JLabel shortTermLabel = new JLabel("Short Term");
        shortTermLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        shortTermLabel.setBounds(20, 20, 200, 30);
        shortTermLabel.setForeground(new Color(0x6D4C41));
        shortTermPanel.add(shortTermLabel);

        JButton button1 = buttonStyle("+");
        button1.addActionListener(e -> addTask());
        button1.setBounds(120, 22, 30, 30);
        shortTermPanel.add(button1);

        longTermPanel();
    }

    private void longTermPanel() {
        longTermPanel = new JPanel();
        longTermPanel.setBackground(new Color(0xDCC2A3));
        longTermPanel.setBounds(75, 520, 290, 280);
        longTermPanel.setLayout(null);
        JLabel longTermLabel = new JLabel("Long Term");
        longTermLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        longTermLabel.setBounds(20, 20, 200, 30);
        longTermLabel.setForeground(new Color(0x6D4C41));
        longTermPanel.add(longTermLabel);

        JButton button2 = buttonStyle("+");
        button2.addActionListener(e -> addGoal());
        button2.setBounds(120, 22, 30, 30);
        longTermPanel.add(button2);
    }

    //MODIFIES: this
    //EFFECTS: sets the basic information of a task
    private void addTask() {
        dialogTask = new JDialog(this, "Add a Task", true);
        dialogTask.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialogTask.add(nameLabel);

        nameFieldTask = new JTextField();
        nameFieldTask.setBounds(130, 20, 150, 25);
        dialogTask.add(nameFieldTask);

        JLabel statusLabel = new JLabel("CompleteStatus: ");
        statusLabel.setBounds(20, 60, 100, 25);
        dialogTask.add(statusLabel);

        completeStatusTask = new JCheckBox();
        completeStatusTask.setBounds(130, 60, 25, 25);
        dialogTask.add(completeStatusTask);

        addTaskDetails();

        JLabel goalLabel = new JLabel("Linked Goal: ");
        goalLabel.setBounds(20, 220, 100, 25);
        dialogTask.add(goalLabel);
        goalBox(dialogTask);
        confirmTaskButton();
        confirmSetupTask();
    }

     //MODIFIES: this
    //EFFECTS: adds the dialog of information of a goal to be filled
    private void addGoal() {
        dialogGoal = new JDialog(this, "Add a Goal", true);
        dialogGoal.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialogGoal.add(nameLabel);

        nameFieldGoal = new JTextField();
        nameFieldGoal.setBounds(130, 20, 150, 25);
        dialogGoal.add(nameFieldGoal);

        JLabel statusLabel = new JLabel("CompleteStatus: ");
        statusLabel.setBounds(20, 60, 100, 25);
        dialogGoal.add(statusLabel);

        completeStatusGoal = new JCheckBox();
        completeStatusGoal.setBounds(130, 60, 25, 25);
        dialogGoal.add(completeStatusGoal);

        JLabel taskLabel = new JLabel("Linked Tasks: ");
        taskLabel.setBounds(20, 100, 100, 25);
        dialogGoal.add(taskLabel);
        taskBoxes(dialogGoal);
        confirmGoalButton();
        confirmSetupGoal();
    }


    //MODIFIES: this
    //EFFECTS: sets the menuPanel
    public void menuPanel() {
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0xF3E8D3));
        menuPanel.setBounds(75, 250, 270,550);
        menuPanel.setLayout(null);
        JLabel menuLabel = new JLabel("Main Menu");
        menuLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        menuLabel.setBounds(20, 20, 200, 30);
        menuLabel.setForeground(new Color(0x6D4C41));
        menuPanel.add(menuLabel);

    }

    //MODIFIES: this
    //EFFECTS: sets the goalPanel, adds the name, completeStatus and linkedTasks labels and textAreas
    public void goalPanel() {
        goalPanel = new JPanel();
        goalPanel.setBackground(new Color(0xB7C4A1));
        goalPanel.setLayout(null);
        goalPanel.setBounds(365,520, 280, 280);
        goalLabel = new JLabel("Goal");
        goalLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        goalLabel.setBounds(20, 20, 200, 30);
        goalLabel.setOpaque(false);
        goalPanel.add(goalLabel);

        goalName = new JLabel();
        goalName.setBounds(20, 65, 240, 25);
        goalPanel.add(goalName);

        goalCompleteStatus = new JLabel("");
        goalCompleteStatus.setBounds(20, 95, 240, 25);
        goalPanel.add(goalCompleteStatus);
        
        goalLinkedTasks = new JTextArea();
        goalLinkedTasks.setBounds(20, 125, 240, 150);
        goalLinkedTasks.setEditable(false);
        goalLinkedTasks.setOpaque(false);
        goalLinkedTasks.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        goalPanel.add(goalLinkedTasks);
    }

    //MODIFIES: this
    //EFFECTS: sets the taskPanel
    public void taskPanel() {
        taskPanel = new JPanel();
        taskPanel.setBackground(new Color(0xD0DCC2));
        taskPanel.setLayout(null);
        taskPanel.setBounds(365, 250, 280, 280);

        taskLabel = new JLabel("Task");
        taskLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        taskLabel.setBounds(20, 20, 200, 30);
        taskLabel.setOpaque(false);
        taskPanel.add(taskLabel);
        taskPanelDetails();
    }

    //MODIFIES: this
    //EFFECTS:  adds the name, completeStatus, energyLevel, times, deadline, linkedGoal labels and textAreas
    public void taskPanelDetails() {
        taskName = new JLabel();
        taskName.setBounds(20, 65, 240, 25);
        taskPanel.add(taskName);
        taskCompleteStatus = new JLabel("");
        taskCompleteStatus.setBounds(20, 95, 240, 25);
        taskPanel.add(taskCompleteStatus);

        taskEnergyLevel = new JLabel("");
        taskEnergyLevel.setBounds(20, 155, 240, 25);
        taskPanel.add(taskEnergyLevel);

        taskTimes = new JLabel("");
        taskTimes.setBounds(20, 125, 240, 25);
        taskPanel.add(taskTimes);

        taskDeadline = new JLabel("");
        taskDeadline.setBounds(20, 185, 240, 25);
        taskPanel.add(taskDeadline);

        
        taskLinkedGoal = new JTextArea();
        taskLinkedGoal.setBounds(20, 215, 240, 25);
        taskLinkedGoal.setEditable(false);
        taskLinkedGoal.setOpaque(false);
        taskLinkedGoal.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        taskPanel.add(taskLinkedGoal);

    }

    //MODIFIES: this
    //EFFECTS: sets the poemPanel and the introduction texts
    public void poemContainer() {
        poemContainer = new JPanel();
        poemContainer.setBackground(new Color(0xF3E3AF));
        poemContainer.setBounds(900, 50, 380, 750);
        poemContainer.setLayout(null);

        text = new JTextArea("LifeOS helps you convert your long-term goals into short-term tasks.\n"
        + "Light your life. Ignite your intent. Focus your future. Evolve with ease.");
        text.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 18));
        text.setForeground(new Color(0xA87C6A));
        text.setEditable(false);
        text.setOpaque(false);
        text.setBounds(75, 165, 700, 80);

        poemPanel = new JTextPane();
        poemPanel.setEditable(false);
        poemPanel.setOpaque(false);
        poemPanel.setBounds(20, 15, 440, 700); 
    }

    //MODIFIES: this
    //EFFECTS: adds the panels to the backGround panel and render the window
    public void mainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(900, 960);
        this.getContentPane().setBackground(new Color(0xECE7DE));
        this.addWindowListener(this);

        backGround.add(shortTermPanel);
        backGround.add(longTermPanel);
        backGround.add(goalPanel);
        backGround.add(taskPanel);
        backGround.add(titleLabel);
        backGround.add(text);
        panel.add(backGround);
        poemContainer.add(poemPanel);
        
        this.add(panel);
        this.setVisible(true);
        panel.setSize(getWidth(), getHeight());
    }

    //MODIFIES: this
    //EFFECTS: updates the information of this goal when the button was pressed
    public void actionPerformedGoal(ActionEvent e, Goal g) {
        updateGoalInfo(g);
    }

    //MODIFIES: this
    //EFFECTS: updates the information of this task when the button was pressed
    public void actionPerformedTask(ActionEvent e, Task t) {
        updateTaskInfo(t);
    }

   //MODIFIES: this
   //EFFECTS: reads the longTerm json file, shows a dialog when exception
    private void loadLong() {
        try {
            jsonReaderLong = new JsonReader(JSON_STORE_LONG); 
            longTerm = jsonReaderLong.readLongTerm();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
        
    }

    //MODIFIES: this
    //EFFECTS: updates the goalList and adds add and remove button
    private void renderLong() {
        goalList.removeAll();
        for (Goal g : longTerm.getGoals()) {
            JButton button = buttonStyle(g.getName());
            button.addActionListener(e -> actionPerformedGoal(e, g));

            JPanel row = new JPanel(new BorderLayout());
            row.add(removeGoal(g), java.awt.BorderLayout.EAST);
            row.add(button, java.awt.BorderLayout.WEST);
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(250, 30));
            
            goalList.add(row);
        }

        // JButton addButton = buttonStyle("+");
        // addButton.addActionListener(e -> addGoal());
        // goalList.add(addButton);
        goalList.setOpaque(false);
        goalList.revalidate();
        goalList.repaint();

    }

    //MODIFIES: this
    //EFFECTS: adds a scrollPane for this list
    private void setScrollPane(JPanel panel, JPanel list) {
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(20, 60, 250, 200);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null); 
        panel.add(scrollPane);
    }

   //MODIFIES: this
   //EFFECTS: reads the shortTerm json file, shows a dialog when exception
    private void loadShort() {
        try {
            jsonReaderShort = new JsonReader(JSON_STORE_SHORT); 
            shortTerm = jsonReaderShort.readShortTerm();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
     
    }
        
    //MODIFIES: this
    //EFFECTS: updates the taskList and adds add and remove button
    private void renderShort() {
        taskList.removeAll();
        for (Task t : shortTerm.getTasks()) {
            JButton button = buttonStyle(t.getName());
            button.addActionListener(e -> actionPerformedTask(e, t));
            JPanel row = new JPanel(new BorderLayout());
            row.add(removeTask(t), java.awt.BorderLayout.EAST);
            row.add(button, java.awt.BorderLayout.WEST);
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(250, 30));
            taskList.add(row);
        }

        // JButton addButton = buttonStyle("+");
        // addButton.addActionListener(e -> addTask());
        // taskList.add(addButton);
        taskList.setOpaque(false);
        taskList.revalidate();
        taskList.repaint();
    }

    //EFFECTS: returns a remove button
    private JButton removeTask(Task t) {
        JButton remove = buttonStyle("x");
        remove.setFont(null);
        remove.addActionListener(e -> actionPerformedRemoveTask(e, t));
        return remove;
    }

    //MODIFIES: this, shortTerm
    //EFFECTS: removes the task from taskList and removes the linking to the linkedGoal, 
    //         updates the taskList when the button was pressed
    private void actionPerformedRemoveTask(ActionEvent e, Task t) {
        try {
            shortTerm.removeTask(t.getName());
        } catch (NameErrorException ex) {
            JOptionPane.showMessageDialog(this, "Cannot remove task.");
        }
        if (t.getLinkedGoal() != null) {
            t.getLinkedGoal().removeLinkedTask(t);
            t.setLinkedGoal(null);
        }
        renderShort();
    }

    

    //MODIFIES: dialogTask
    //EFFECTS: sets the detailed information of a task
    private void addTaskDetails() {
        JLabel energyLevelLabel = new JLabel("EnergyLevel: ");
        energyLevelLabel.setBounds(20, 100, 100, 25);
        dialogTask.add(energyLevelLabel);

        energyLevelTask = new JTextField();
        energyLevelTask.setBounds(130, 100, 150, 25);
        dialogTask.add(energyLevelTask);

        JLabel timesLabel = new JLabel("Times: ");
        timesLabel.setBounds(20, 140, 100, 25);
        dialogTask.add(timesLabel);

        timesTask = new JTextField();
        timesTask.setBounds(130, 140, 150, 25);
        dialogTask.add(timesTask);

        JLabel deadlineLabel = new JLabel("Deadline: ");
        deadlineLabel.setBounds(20, 180, 100, 25);
        dialogTask.add(deadlineLabel);

        deadlineTask = new JTextField();
        deadlineTask.setBounds(130, 180, 150, 25);
        dialogTask.add(deadlineTask);
    }
    
    //MODIFIES: dialogTask
    //EFFECTS: adds the confirm button to the dialog
    private void confirmSetupTask() {
        confirmTask.setBounds(80, ypos + 10, 100, 30);
        dialogTask.add(confirmTask);
        dialogTask.setSize(320, ypos + 100);
        dialogTask.setLocationRelativeTo(this);
        dialogStyle(dialogTask);
        dialogTask.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the confirm button and links the action listener
    private void confirmTaskButton() {
        confirmTask = new JButton("Confirm");
        confirmTask.addActionListener(event -> confirmTask(null));
    }

    //MODIFIES: dialog
    //EFFECTS: adds the radioButton for each goals that could link
    private void goalBox(JDialog dialog) {
        goalBox = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        ypos = 250;
        List<Goal> goals = longTerm.getGoals();
        for (int x = 0; x < goals.size(); x++) {
            JRadioButton rb = new JRadioButton(goals.get(x).getName());
            rb.setBounds(20, ypos, 280, 25);
            group.add(rb);
            dialog.add(rb);
            goalBox.add(rb);
            ypos += 30;
        }
    }

    //MODIFIES: this
    //EFFECTS: updates the information of the chosen task
    private void updateTaskInfo(Task t) {
        task = t;
        taskName.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        taskCompleteStatus.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        taskTimes.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        taskDeadline.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        taskLinkedGoal.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        taskEnergyLevel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
    
    
        taskName.setText("Name: " + t.getName());
        taskEnergyLevel.setText("EnergyLevel: " + t.getEnergyLevel());
        taskTimes.setText("Times: " + t.getTimes());
        taskDeadline.setText("Deadline: " + t.getDeadline());

        if (t.getCompleteStatus()) {
            taskCompleteStatus.setText("CompleteStatus: " + "Completed");
        } else {
            taskCompleteStatus.setText("CompleteStatus: " + "Uncompleted");
        }

        if (t.getLinkedGoal() != null) {
            taskLinkedGoal.setText(t.getLinkedGoal().getName());; 
        } else {
            taskLinkedGoal.setText("No linked goal.");
        }
    }

    //MODIFIES: this
    //EFFECTS: updates the information of the chosen goal
    private void updateGoalInfo(Goal g) {
        goal = g;
        goalName.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        goalCompleteStatus.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        goalLinkedTasks.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));

        goalName.setText("Name: " + g.getName());
        String status = "";
        if (g.getCompleteStatus()) {
            status = "Completed";
        } else {
            status = "Uncompleted";
        }
        goalCompleteStatus.setText("Status: " + status);
        
        List<String> taskNames = g.getLinkedTaskNames();
        
        String tasks = ""; 
        
        if (taskNames.isEmpty()) {
            tasks = "No linked tasks.";
        } else {
            for (String taskName : taskNames) {
                tasks = tasks + taskName + "\n";
            }
        }
        goalLinkedTasks.setText(tasks);
        
    }

    //EFFECTS: returns a button that has style
    public JButton buttonStyle(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 16));
        button.setForeground(new Color(0x6D4C41));
        button.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        return button;
    }

   
    //MODIFIES: dialog
    //EFFECTS: adds the checkBoxes for each tasks that could link
    private void taskBoxes(JDialog dialog) {
        taskBoxes = new ArrayList<>();

        ypos = 130;
        List<Task> tasks = shortTerm.getTasks();
        for (int x = 0; x < tasks.size(); x++) {
            JCheckBox cb = new JCheckBox(tasks.get(x).getName());
            cb.setBounds(20, ypos, 280, 25);
            dialog.add(cb);
            taskBoxes.add(cb);
            ypos += 30;
        }
    }

    //MODIFIES: this
    //EFFECTS: creates the confirm button and links the action listener
    private void confirmGoalButton() {
        confirmGoal = new JButton("Confirm");
        confirmGoal.addActionListener(event -> confirmGoal(null));
    }

    //MODIFIES: this, shortTerm
    //EFFECTS: updates the information of the goal and the goalList, then closes the dialog
    private void confirmGoal(Goal current) {
        try {
            Goal g = current;
            if (current == null) {
                g = new Goal(nameFieldGoal.getText());
                longTerm.addGoal(g.getName());
            } else {
                g.setName(nameFieldGoal.getText());
            }
            if (completeStatusGoal.isSelected()) {
                completeGoal(g);
            }
            clearLinkedGoals(g);
            for (int i = 0; i < taskBoxes.size(); i++) {
                if (taskBoxes.get(i).isSelected()) {
                    g.setLinkedTask(shortTerm.getTasks().get(i));
                    shortTerm.getTasks().get(i).setLinkedGoal(g);
                }
            }
            updateGoalView(g);
            dialogDisposeGoal(current);
        } catch (NameErrorException e) {
            JOptionPane.showMessageDialog(dialogGoal, "Invalid name.");
        }
        
    }

    //MODIFIES: g
    //EFFECTS: sets the goal as completed and pops up the image
    private void completeGoal(Goal g) {
        g.setAsCompleted();
        completeImage();
    }

    //MODIFIES: this
    //EFFECTS: updates the goalList and the information of the goal
    private void updateGoalView(Goal g) {
        renderLong();
        updateGoalInfo(g);
    }

    //MODIFIES: g
    //EFFECTS: clears the linking relationship
    private void clearLinkedGoals(Goal g) {
        for (int i = g.getLinkedTasks().size() - 1; i >= 0; i--) {
            Task t = g.getLinkedTasks().get(i);
            g.removeLinkedTask(t);
            t.setLinkedGoal(null);
        }
    }

    //MODIFIES: dialogGoal, dialogEdit
    //EFFECTS: if there isn't a current goal, closes dialogGoal. Otherwise closes dialogEdit
    private void dialogDisposeGoal(Goal current) {
        if (current == null) {
            dialogGoal.dispose();
        } else {
            dialogEdit.dispose();
        }
    }
    
    //MODIFIES: dialogGoal
    //EFFECTS: adds the confirm button to the dialog
    private void confirmSetupGoal() {
        confirmGoal.setBounds(80, ypos + 10, 100, 30);
        dialogGoal.add(confirmGoal);
        dialogGoal.setSize(320, ypos + 100);
        dialogGoal.setLocationRelativeTo(this);
        dialogStyle(dialogGoal);
        dialogGoal.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: returns the save button
    private JButton save() {
        save = buttonStyle("Save");
        save.addActionListener(e -> actionPerformedSave());
        save.setBounds(650,520,90,30);
        save.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));

        return save;
    }

    //MODIFIES: JSON_STORE_LONG, JSON_STORE_SHORT
    //EFFECTS: saves the data to json files and pops up the dialog
    private void actionPerformedSave() {
        updateSavedData();
        JOptionPane.showMessageDialog(this, "Saved Successfully");
    }

    //MODIFIES: JSON_STORE_LONG, JSON_STORE_SHORT
    //EFFECTS: writes the data to json files, if exception then pops up dialog
    private void updateSavedData() {
        jsonWriterLong = new JsonWriter(JSON_STORE_LONG);
        jsonWriterShort = new JsonWriter(JSON_STORE_SHORT);
        try {
            jsonWriterLong.open();
            jsonWriterLong.write(longTerm);
            jsonWriterLong.close();
            
            jsonWriterShort.open();
            jsonWriterShort.write(shortTerm);
            jsonWriterShort.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot save to file");
        }
    }

    //EFFECTS: returns a remove button
    private JButton removeGoal(Goal g) {
        remove = buttonStyle("x");
        remove.setFont(null);
        remove.addActionListener(e -> actionPerformedRemove(e, g));
        remove.setBounds(0,50,40,30);
        

        return remove;
    }

    //MODIFIES: this, longTerm
    //EFFECTS: removes the goal from goalList and the link, updates the goalList. If exception then pops up dialog 
    private void actionPerformedRemove(ActionEvent e, Goal g) {
        try {
            longTerm.removeGoal(g.getName());
        } catch (NameErrorException ex) {
            JOptionPane.showMessageDialog(this, "Cannot remove goal.");
        }
        renderLong();
        for (Task t: g.getLinkedTasks()) {
            t.setLinkedGoal(null);
        }
    }

    //MODIFIES: this
    //EFFECTS: returns the load button
    private JButton load() {
        load = buttonStyle("Load");
        load.addActionListener(e -> actionPerformedLoad());
        load.setBounds(650,480,90,30);
        load.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));
        return load;
    }

    //MODIFIES: this
    //EFFECTS: load the json files, set the links and updates the goalList and taskList
    private void actionPerformedLoad() {
        loadLong();
        loadShort();
        jsonReaderShort.setLinks(longTerm.getGoals());
        renderLong();
        renderShort();
        longTermPanel.revalidate();
        longTermPanel.repaint();
        shortTermPanel.revalidate();
        shortTermPanel.repaint();
    }

    //MODIFIES: taskPanel
    //EFFECTS: adds the edit button and sets the link to the dialog
    private void editButtonTask() {
        edit = buttonStyle("edit");
        edit.setBounds(50, 26, 80, 25);
        edit.addActionListener(e -> editTask(task));
        taskPanel.add(edit);
    }

    //MODIFIES: this
    //EFFECTS: pops up the dialog and the information of the goal
    private void editGoal(Goal goal) {
        dialogEdit = new JDialog(this, "Edit a Goal", true);
        dialogEdit.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialogEdit.add(nameLabel);

        nameFieldGoal = new JTextField(goal.getName());
        nameFieldGoal.setBounds(130, 20, 150, 25);
        dialogEdit.add(nameFieldGoal);

        JLabel completeStatusLabel = new JLabel("CompleteStatus: ");
        completeStatusLabel.setBounds(20, 60, 100, 25);
        dialogEdit.add(completeStatusLabel);

        completeStatusGoal = new JCheckBox();
        completeStatusGoal.setBounds(130, 60, 25, 25);
        dialogEdit.add(completeStatusGoal);

        JLabel newLinkedTasks = new JLabel("Linked Tasks: ");
        newLinkedTasks.setBounds(20, 100, 100, 25);
        dialogEdit.add(newLinkedTasks);
        taskBoxes(dialogEdit);

        confirmGoal = new JButton("Confirm");
        confirmGoal.addActionListener(e -> confirmGoal(goal));
        confirmGoal.setBounds(80, ypos + 10, 100, 30);
        
        dialogEdit();
    }

     //MODIFIES: goalPanel
    //EFFECTS: adds the edit button and sets the link to the dialog
    private void editButtonGoal() {
        edit = buttonStyle("edit");
        edit.setBounds(50, 26, 80, 25);
        edit.addActionListener(e -> editGoal(goal));
        goalPanel.add(edit);
    }

    //MODIFIES: this
    //EFFECTS: pops up the dialog and the information of the task
    private void editTask(Task task) {
        dialogEdit = new JDialog(this, "Edit a Task", true);
        dialogEdit.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialogEdit.add(nameLabel);

        nameFieldTask = new JTextField(task.getName());
        nameFieldTask.setBounds(130, 20, 150, 25);
        dialogEdit.add(nameFieldTask);

        JLabel statusLabel = new JLabel("Completed: ");
        statusLabel.setBounds(20, 60, 100, 25);
        dialogEdit.add(statusLabel);

        completeStatusTask = new JCheckBox();
        completeStatusTask.setSelected(task.getCompleteStatus());
        completeStatusTask.setBounds(130, 60, 25, 25);
        dialogEdit.add(completeStatusTask);

        JLabel goalLabel = new JLabel("Linked Goal: ");
        goalLabel.setBounds(20, 220, 100, 25);
        dialogEdit.add(goalLabel);
        goalBox(dialogEdit);

        confirmGoal = new JButton("Confirm");
        confirmGoal.addActionListener(e -> confirmTask(task));
        confirmGoal.setBounds(80, ypos + 10, 100, 30);

        editTaskDetails(task);
        
    }

    //MODIFIES: this
    //EFFECTS: adds the detailed information to the dialog
    private void editTaskDetails(Task task) {
        JLabel energyLevelLabel = new JLabel("EnergyLevel: ");
        energyLevelLabel.setBounds(20, 100, 100, 25);
        dialogEdit.add(energyLevelLabel);

        energyLevelTask = new JTextField(task.getEnergyLevel());
        energyLevelTask.setBounds(130, 100, 150, 25);
        dialogEdit.add(energyLevelTask);
         
        JLabel timesLabel = new JLabel("Times: ");
        timesLabel.setBounds(20, 140, 100, 25);
        dialogEdit.add(timesLabel);

        timesTask = new JTextField(task.getTimes());
        timesTask.setBounds(130, 140, 150, 25);
        dialogEdit.add(timesTask);

        JLabel deadlineLabel = new JLabel("Deadline: ");
        deadlineLabel.setBounds(20, 180, 100, 25);
        dialogEdit.add(deadlineLabel);

        deadlineTask = new JTextField(task.getDeadline());
        deadlineTask.setBounds(130, 180, 150, 25);
        dialogEdit.add(deadlineTask);
        dialogEdit();
    }

    //MODIFIES: this
    //EFFECTS: adds the confirm button and shows the dialog
    private void dialogEdit() {
        dialogEdit.add(confirmGoal);
        dialogEdit.setSize(320, ypos + 100);
        dialogEdit.setLocationRelativeTo(this);
        dialogStyle(dialogEdit);
        dialogEdit.setVisible(true);
    }

    //MODIFIES: this, shortTerm
    //EFFECTS: updates the information of the task and the taskList, then closes the dialog
    private void confirmTask(Task current) {
        try {
            Task t = current;
            if (current == null) {
                t = new Task(nameFieldTask.getText());
                shortTerm.addTask(t.getName());
            } else {
                t.setName(nameFieldTask.getText());
            }
            if (completeStatusTask.isSelected()) {
                completeTask(t);
            }

            updateTaskDetails(t);

            for (int i = 0; i < goalBox.size(); i++) {
                if (goalBox.get(i).isSelected()) {
                    t.setLinkedGoal(longTerm.getGoals().get(i));
                    longTerm.getGoals().get(i).setLinkedTask(t);
                }
            }
            updateTaskView(t);
            dialogDisposeTask(current);
        } catch (NameErrorException e) {
            JOptionPane.showMessageDialog(dialogTask, "Invalid name.");
        }
    }

    //MODIFIES: t
    //EFFECTS: updates the detailed information of the task
    private void updateTaskDetails(Task t) {
        t.setEnergyLevel(Integer.parseInt(energyLevelTask.getText()));
        t.setTimes(Integer.parseInt(timesTask.getText()));
        t.setDeadline(deadlineTask.getText());
    }

    //MODIFIES: t
    //EFFECTS: sets the task as completed and pops up the image
    private void completeTask(Task t) {
        t.setAsCompleted();
        completeImage();
    }

    //MODIFIES: this
    //EFFECTS: updates the taskList and the task information
    private void updateTaskView(Task t) {
        renderShort();
        updateTaskInfo(t);
    }

    //MODIFIES: dialogTask, dialogEdit
    //EFFECTS: if there isn't a current task, closes dialogTask. Otherwise closes dialogEdit
    private void dialogDisposeTask(Task current) {
        if (current == null) {
            dialogTask.dispose();
        } else {
            dialogEdit.dispose();
        }
    }

    //MODIFIES: dialog
    //EFFECTS: sets the style for the dialog
    private void dialogStyle(JDialog dialog) {
        for (java.awt.Component c : dialog.getContentPane().getComponents()) {
            c.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 14));
        }
    }

    //EFFECTS: pops up a dialog with image
    private void completeImage() {
        ImageIcon image = new ImageIcon("image.png");
        JOptionPane.showMessageDialog(this, "", "Good job!", JOptionPane.PLAIN_MESSAGE, image);
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        updateSavedData();
        printLog(EventLog.getInstance());
    }


    // EFFECTS: prints out all events in the eventLog
    public void printLog(EventLog eventlog) {
        for (Event event : eventlog) {
            System.out.println(event);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosed(WindowEvent e) {
       
    }

    @Override
    public void windowIconified(WindowEvent e) {
       
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       
    }

    @Override
    public void windowActivated(WindowEvent e) {
       
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    



    
}







