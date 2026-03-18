package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import model.Goal;
import model.LongTerm;
import model.Task;
import model.ShortTerm;

import persistence.JsonReader;

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

    private static final String JSON_STORE_LONG = "./data/longTerm.json";
    private static final String JSON_STORE_SHORT = "./data/shortTerm.json";
    
    public MainFrame() throws NameErrorException {
        jsonReaderLong = new JsonReader(JSON_STORE_LONG);
        jsonReaderShort = new JsonReader(JSON_STORE_SHORT);
        init();
        basicPanel();
        menuPanel();
        goalPanel();
        taskPanel();
        poemContainer();
        loadLong();
        loadShort();
        mainFrame();
    }
    
    public void init() {
        backGround = new JPanel();
        //backGround.setBackground(new Color(255, 236, 170));
        backGround.setBackground(new Color(0xF3E3AF));
        backGround.setBackground(new Color(0xFFF8E7));
        backGround.setLayout(null);
        backGround.setBounds(80, 28, 1380, 870);

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
        goalCompleteStatus.setBounds(20, 95, 240, 30);
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
            shortTerm = jsonReaderShort.readShortTerm();
            
            goalList = new JPanel();
            goalList.setLayout(new BoxLayout(goalList, BoxLayout.Y_AXIS));
            goalList.setBackground(new Color(0xDCC2A3));
            goalList.setBounds(20, 60, 250, 200);

            for (Goal g : longTerm.getGoals()) {
                JButton button = buttonStyle(g.getName());
                button.addActionListener(e -> actionPerformedGoal(e, g));
                goalList.add(button);
            }

            longTermPanel.add(goalList);

        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
        
    }


    private void loadShort() {
        try {
            shortTerm = jsonReaderShort.readShortTerm();
            taskList = new JPanel();
            taskList.setLayout(new BoxLayout(taskList, BoxLayout.Y_AXIS));
            taskList.setOpaque(false);
            taskList.setBounds(20, 60, 250, 200);

            for (Task t : shortTerm.getTasks()) {
                JButton button = buttonStyle(t.getName());
                button.addActionListener(e -> actionPerformedTask(e, t));
                taskList.add(button);
            }

            shortTermPanel.add(taskList);
        } catch (IOException e) {
            System.out.println("Cannot read from file");
            JOptionPane.showMessageDialog(this, "File not found");
        }
     
    }

    private void updateTaskInfo(Task t) {
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

       
}
