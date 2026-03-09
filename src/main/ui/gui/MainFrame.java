package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;  
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

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
    //private JButton button;
    
    public MainFrame() throws NameErrorException {
        init();
        basicPanel();
        menuPanel();
        subPanel();
        poemContainer();
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
        shortTermPanel.setBounds(340, 250, 290, 280);
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

    public void subPanel() {
        goalPanel = new JPanel();
        goalPanel.setBackground(new Color(0xD0DCC2));
        goalPanel.setLayout(null);
        goalPanel.setBounds(625,250, 280, 280);
        goalLabel = new JLabel("Goal");
        goalLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        goalLabel.setBounds(20, 20, 200, 30);
        goalLabel.setForeground(new Color(0x6D4C41));
        goalPanel.add(goalLabel);

        taskPanel = new JPanel();
        taskPanel.setBackground(new Color(0xB7C4A1));
        taskPanel.setLayout(null);
        taskPanel.setBounds(625, 520, 280, 280);
        taskLabel = new JLabel("Task");
        taskLabel.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
        taskLabel.setBounds(20, 20, 200, 30);
        taskLabel.setForeground(new Color(0x6D4C41));
        taskPanel.add(taskLabel);
    }

    // public void labelPanel() {
    //     JPanel panelA = new JPanel();
    //     panelA.setBackground(new Color(0xF4DB78));
    //     panelA.setBounds(1290, 310, 90, 250);
    //     panelA.setLayout(null);
    //     JLabel labelA = new JLabel("Save");
    //     labelA.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
    //     labelA.setBounds(10, 20, 80, 30);
    //     //labelA.setForeground(new Color(0x6D4C41));
    //     panelA.add(labelA);

        
    //     JPanel panelB = new JPanel();
    //     panelB.setBackground(new Color(0xF4DB78));
    //     panelB.setBounds(1290, 550, 90, 250);
    //     panelB.setLayout(null);
    //     JLabel labelB = new JLabel("Load");
    //     labelB.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
    //     labelB.setBounds(10, 20, 80, 30);
    //     //labelB.setForeground(new Color(0x6D4C41));
    //     panelB.add(labelB);

    //     JPanel panelC = new JPanel();
    //     panelC.setBackground(new Color(0xF4DB78));
    //     panelC.setBounds(1290, 80, 90, 250);
    //     panelC.setLayout(null);
    //     JLabel labelC = new JLabel("Quit");
    //     labelC.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 20));
    //     labelC.setBounds(10, 20, 80, 30);
    //     //labelC.setForeground(new Color(0x6D4C41));
    //     panelC.add(labelC);
    // }

    public void saveButton() {
        // button = new JButton();
        // button.setBounds(1000, 800, 100, 50);
        // button.addActionListener((ActionListener) this);
        // button.setText("Button1");
        // button.setFocusable(false);
        // button.setIcon(icon);
        // button.setHorizontalAlignment(JButton.CENTER);
        // button.setVerticalAlignment(JButton.BOTTOM);
        // button.setFont(new Font("Arial",Font.BOLD, 15));
        // button.setForeground(Color.cyan);
        // button.setBackground(new Color(0xB7C4A1));
        // button.setBorder(BorderFactory.createEtchedBorder());
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
        //backGround.add(button);
        // backGround.add(panelC);
        // backGround.add(panelA);
        // backGround.add(panelB);
        panel.add(backGround);
        // panel.add(panelC);
        // panel.add(panelA);
        // panel.add(panelB);
        poemContainer.add(poemPanel);
        backGround.add(poemContainer);
        
        this.add(panel);
        this.setVisible(true);
        panel.setSize(getWidth(), getHeight());
    }
       
}
