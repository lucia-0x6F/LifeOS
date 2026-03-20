package ui.gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import model.Goal;
import model.LongTerm;
import model.Task;
import model.WorkUnit;
import model.ShortTerm;

import persistence.JsonReader;
import persistence.JsonWriter;
import model.exception.NameErrorException;

public class MainFrame extends JFrame {
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
    private JLabel goalName;
    private JLabel goalCompleteStatus;
    private JPanel goalList;
    private JTextArea goalLinkedTasks;

    private ShortTerm shortTerm;
    private JsonReader jsonReaderShort;
    private JPanel taskList;
    private JLabel taskName;
    private JLabel taskTimes;
    private JLabel taskDeadline;
    private JLabel taskEnergyLevel;
    private JLabel taskCompleteStatus;
    private JTextArea taskLinkedGoal;
    private JTextField nameField;
    private JCheckBox completeStatus;
    private JDialog dialog;
    private List<JCheckBox> taskBoxes;
    private int ypos;
    private JButton confirm;
    private JButton edit;

    private JDialog dialog2;
    private List<JRadioButton> goalBox;
    private JButton confirm2;

    private JCheckBox completeStatus2;
    private JTextField nameField2;
    private JButton save;
    private JButton load;
    private JButton remove;

    private JsonWriter jsonWriterLong;
    private JsonWriter jsonWriterShort;

    private Task task;
    private Goal goal;

    private JDialog dialogEdit;

    private static final String JSON_STORE_LONG = "./data/longTerm.json";
    private static final String JSON_STORE_SHORT = "./data/shortTerm.json";
    
    public MainFrame() throws NameErrorException {
        jsonReaderLong = new JsonReader(JSON_STORE_LONG);
        jsonReaderShort = new JsonReader(JSON_STORE_SHORT);
        save();
        load();
        init();
        basicPanel();
        menuPanel();
        goalPanel();
        taskPanel();
        poemContainer();
        
        goalList = new JPanel();
        goalList.setLayout(new BoxLayout(goalList, BoxLayout.Y_AXIS));
        goalList.setOpaque(false);
        taskList = new JPanel();
        taskList.setLayout(new BoxLayout(taskList, BoxLayout.Y_AXIS));
        taskList.setOpaque(false);

        setScrollPane(longTermPanel, goalList);
        setScrollPane(shortTermPanel, taskList);

        editButtonTask();
        editButtonGoal();

        mainFrame();
    }
    
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
        titleLabel.setBounds(75, 80, 600, 80);
    }

    public void basicPanel() {
        shortTermPanel = new JPanel();
        shortTermPanel.setBackground(new Color(0xE6D2B8));
        shortTermPanel.setBounds(340, 250, 290, 270);
        shortTermPanel.setLayout(null);
        JLabel shortTermLabel = new JLabel("Short Term");
        shortTermLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        shortTermLabel.setBounds(20, 20, 200, 30);
        shortTermLabel.setForeground(new Color(0x6D4C41));
        shortTermPanel.add(shortTermLabel);

        longTermPanel = new JPanel();
        longTermPanel.setBackground(new Color(0xDCC2A3));
        longTermPanel.setBounds(340, 520, 290, 280);
        longTermPanel.setLayout(null);
        JLabel longTermLabel = new JLabel("Long Term");
        longTermLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        longTermLabel.setBounds(20, 20, 200, 30);
        longTermLabel.setForeground(new Color(0x6D4C41));
        longTermPanel.add(longTermLabel);
    }

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

        // addingPanel = new JPanel();
        // addingPanel.setBackground(new Color(0xF3E3AF));
        // addingPanel.setBounds(90, 320, 240,300);
        // addingPanel.setLayout(null);
        // JLabel addingLabel = new JLabel("Add a new Goal or Task");
        // addingLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 18));
        // addingLabel.setBounds(10, 10, 400, 30);
        // addingLabel.setForeground(new Color(0x6D4C41));
        // addingPanel.add(addingLabel);
    }

    public void goalPanel() {
        goalPanel = new JPanel();
        goalPanel.setBackground(new Color(0xB7C4A1));
        goalPanel.setLayout(null);
        goalPanel.setBounds(625,520, 280, 280);
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
        goalLinkedTasks.setBounds(20, 125, 240, 80);
        goalLinkedTasks.setEditable(false);
        goalLinkedTasks.setOpaque(false);
        goalLinkedTasks.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        goalPanel.add(goalLinkedTasks);
    }

    public void taskPanel() {
        taskPanel = new JPanel();
        taskPanel.setBackground(new Color(0xD0DCC2));
        taskPanel.setLayout(null);
        taskPanel.setBounds(625, 250, 280, 280);
        taskLabel = new JLabel("Task");
        taskLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        taskLabel.setBounds(20, 20, 200, 30);
        taskLabel.setOpaque(false);
        taskPanel.add(taskLabel);
        taskPanelDetails();
    }

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

        //Poem
        poemPanel = new JTextPane();
        poemPanel.setEditable(false);
        poemPanel.setOpaque(false);
        poemPanel.setBounds(20, 15, 440, 700); 
    }


    public void mainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(0xECE7DE));
        
        //backGround.add(addingPanel);
        backGround.add(menuPanel);   
        backGround.add(shortTermPanel);
        backGround.add(longTermPanel);
        backGround.add(goalPanel);
        backGround.add(taskPanel);
        backGround.add(titleLabel);
        backGround.add(text);
        panel.add(backGround);
        poemContainer.add(poemPanel);
        backGround.add(poemContainer);
        
        this.add(panel);
        this.setVisible(true);
        panel.setSize(getWidth(), getHeight());
    }

    public void actionPerformedGoal(ActionEvent e, Goal g) {
        updateGoalInfo(g);
    }

    public void actionPerformedTask(ActionEvent e, Task t) {
        updateTaskInfo(t);
    }

   

    private void loadLong() {
        try {
            longTerm = jsonReaderLong.readLongTerm();
        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
        
    }

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
            remove.setFont(null);
            
            goalList.add(row);
        }

        JButton addButton = buttonStyle("+");
        addButton.addActionListener(e -> addGoal());
        goalList.add(addButton);
        goalList.setOpaque(false);
        goalList.revalidate();
        goalList.repaint();

    }

    private void setScrollPane(JPanel panel, JPanel list) {
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(20, 60, 250, 200);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null); 
        panel.add(scrollPane);
    }


    private void loadShort() {
        try {
            shortTerm = jsonReaderShort.readShortTerm();

          

        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
     
    }

    private void renderShort() {
        taskList.removeAll();
        for (Task t : shortTerm.getTasks()) {
            JButton button = buttonStyle(t.getName());
            button.addActionListener(e -> actionPerformedTask(e, t));
            taskList.add(button);
        }

        JButton addButton = buttonStyle("+");
        addButton.addActionListener(e -> addTask());
        taskList.add(addButton);
        taskList.setOpaque(false);
        taskList.revalidate();
        taskList.repaint();
    }

    private void addTask() {
        dialog2 = new JDialog(this, "Add a Task", true);
        dialog2.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialog2.add(nameLabel);

        nameField2 = new JTextField();
        nameField2.setBounds(130, 20, 150, 25);
        dialog2.add(nameField2);

        JLabel cpmpleteStatus = new JLabel("Completed: ");
        cpmpleteStatus.setBounds(20, 60, 100, 25);
        dialog2.add(cpmpleteStatus);

        completeStatus2 = new JCheckBox();
        completeStatus2.setBounds(130, 60, 25, 25);
        dialog2.add(completeStatus2);

        JLabel taskLabel = new JLabel("Linked Goal: ");
        taskLabel.setBounds(20, 100, 100, 25);
        dialog2.add(taskLabel);
        goalBox();
        confirm2();
        confirmSetup2();
    }
    

    private void confirmSetup2() {
        confirm2.setBounds(80, ypos + 10, 100, 30);
        dialog2.add(confirm2);
        dialog2.setSize(320, ypos + 100);
        dialog2.setLocationRelativeTo(this);
        dialog2.setVisible(true);
    }

    private void confirm2() {
        confirm2 = new JButton("Confirm");
        confirm2.addActionListener(event -> confirm2Task());
    }

    public void confirm2Task() {
        try {
            Task task = new Task(nameField2.getText());
            if (completeStatus2.isSelected()) {
                task.setAsCompleted();
            }
            for (int i = 0; i < goalBox.size(); i++) {
                if (goalBox.get(i).isSelected()) {
                    task.setLinkedGoal(longTerm.getGoals().get(i));
                }
            }
            shortTerm.addTask(task.getName());
            JButton button = buttonStyle(task.getName());
            button.addActionListener(e -> actionPerformedTask(e, task));
            taskList.add(button, taskList.getComponentCount() - 1);
            taskList.revalidate();
            taskList.repaint();
            dialog2.dispose();
        } catch (NameErrorException ex) {
            JOptionPane.showMessageDialog(dialog, "Invalid name.");
        }
        
    }

    private void goalBox() {
        goalBox = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        ypos = 130;
        List<Goal> goals = longTerm.getGoals();
        for (int x = 0; x < goals.size(); x++) {
            JRadioButton rb = new JRadioButton(goals.get(x).getName());
            rb.setBounds(20, ypos, 280, 25);
            group.add(rb);
            dialog2.add(rb);
            goalBox.add(rb);
            ypos += 30;
        }
    }

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
                tasks = tasks + " " + taskName + "\n";
            }
        }
        goalLinkedTasks.setText(tasks);
        
    }

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


    private void addGoal() {
        dialog = new JDialog(this, "Add a Goal", true);
        dialog.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialog.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 20, 150, 25);
        dialog.add(nameField);

        JLabel cpmpleteStatus = new JLabel("Completed: ");
        cpmpleteStatus.setBounds(20, 60, 100, 25);
        dialog.add(cpmpleteStatus);

        completeStatus = new JCheckBox();
        completeStatus.setBounds(130, 60, 25, 25);
        dialog.add(completeStatus);

        JLabel taskLabel = new JLabel("Linked Tasks: ");
        taskLabel.setBounds(20, 100, 100, 25);
        dialog.add(taskLabel);
        taskBoxes(dialog);
        confirm();
        confirmSetup();
    }

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

    private void confirm() {
        confirm = new JButton("Confirm");
        confirm.addActionListener(event -> confirmGoal(null));
    }

    private void confirmGoal(Goal current) {
        Goal g = null;
        if (current == null) {
            g = new Goal(nameField.getText());
        } else {
            g = current;
            g.setName(nameField.getText());
        }
        if (completeStatus.isSelected()) {
            g.setAsCompleted();
        }
        for (int i = 0; i < taskBoxes.size(); i++) {
            if (taskBoxes.get(i).isSelected()) {
                g.setLinkedTask(shortTerm.getTasks().get(i));
            }
        }
        if (!longTerm.getGoals().contains(g)) {
             longTerm.addGoal(g);
        } 
         
        renderLong();
        updateGoalInfo(g);
        if (current == null) {
            dialog.dispose();
        } else {
            dialogEdit.dispose();
        }
        
    }
    
    private void confirmSetup() {
        confirm.setBounds(80, ypos + 10, 100, 30);
        dialog.add(confirm);
        dialog.setSize(320, ypos + 100);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JButton save() {
        save = buttonStyle("Save");
        save.addActionListener(e -> actionPerformedSave());
        save.setBounds(650,150,90,30);
        save.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));

        return save;
    }

    private void actionPerformedSave() {
        updateSavedData();
    }

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

    private JButton removeGoal(Goal g) {
        remove = buttonStyle("x");
        remove.addActionListener(e -> actionPerformedRemove(e, g));
        remove.setBounds(0,50,40,30);
        

        return remove;
    }

    private void actionPerformedRemove(ActionEvent e, Goal g) {
        try {
            longTerm.removeGoal(g.getName());
        } catch (NameErrorException ex) {
            JOptionPane.showMessageDialog(this, "Cannot remove goal.");
        }
        renderLong();
    }

    private JButton load() {
        load = buttonStyle("Load");
        load.addActionListener(e -> actionPerformedLoad());
        load.setBounds(650,100,90,30);
        load.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 30));
        return load;
    }

    private void actionPerformedLoad() {
        loadLong();
        renderLong();
        loadShort();
        renderShort();
        longTermPanel.revalidate();
        longTermPanel.repaint();
        shortTermPanel.revalidate();
        shortTermPanel.repaint();
    }

    private void editButtonTask() {
        edit = buttonStyle("edit");
        edit.setBounds(50, 26, 80, 25);
        edit.addActionListener(e -> editTask(task));
        taskPanel.add(edit);
    }

    private void editGoal(Goal goal) {
        dialogEdit = new JDialog(this, "Edit a Task", true);
        dialogEdit.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 20, 100, 25);
        dialogEdit.add(nameLabel);

        nameField = new JTextField(goal.getName());
        nameField.setBounds(130, 20, 150, 25);
        dialogEdit.add(nameField);

        completeStatus = new JCheckBox();
        completeStatus.setBounds(130, 60, 25, 25);
        dialogEdit.add(completeStatus);

        JCheckBox newStatusBox = new JCheckBox();
        newStatusBox.setBounds(130, 60, 25, 25);
        dialogEdit.add(newStatusBox);

        JLabel newLinkedTasks = new JLabel("Linked Tasks: ");
        newLinkedTasks.setBounds(20, 100, 100, 25);
        dialogEdit.add(newLinkedTasks);
        taskBoxes(dialogEdit);

        confirm = new JButton("Confirm");
        confirm.addActionListener(e -> confirmGoal(goal));
        confirm.setBounds(80, ypos + 10, 100, 30);
        
        dialogEdit.add(confirm);
        dialogEdit.setSize(320, ypos + 100);
        dialogEdit.setLocationRelativeTo(this);
        dialogEdit.setVisible(true);


    }
    

     private void editButtonGoal() {
        edit = buttonStyle("edit");
        edit.setBounds(50, 26, 80, 25);
        edit.addActionListener(e -> editGoal(goal));
        goalPanel.add(edit);
    }

     private void editTask(Task task) {
        
    }


}







        