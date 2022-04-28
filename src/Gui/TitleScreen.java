package Gui;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500; // set these to have a consistent size between screens

    private JPanel pnlOne; //main panel
    private JPanel MazeDisplayPanel;
    private JPanel ButtonShelf; // panel to store buttons
    private JPanel pnlNorth;
    private JPanel pnlEast;
    private JPanel pnlSouth;
    private JPanel pnlWest;
    private JPanel pnlCenter;

    BorderLayout pageLayout = new BorderLayout(); // layout for back pages


    public ImageIcon logo = new ImageIcon("mazeLogo.png"); //potential logo

    private JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }
    // The Below buttons need to be external so the even listener works due to Scope
    JButton NewMazeButton = new JButton("New Maze");
    JButton RandomMazeButton = new JButton("Random Maze");
    JButton LoadButton = new JButton("Load Maze");

    public TitleScreen(){

        GridLayout buttonLayout = new GridLayout(3,1); // layout for ButtonShelf
        buttonLayout.setVgap(20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Frames used to help set the layout
        pnlNorth = createPanel(Color.darkGray);
        pnlNorth.setPreferredSize(new Dimension(150,150));
        pnlEast = createPanel(Color.darkGray);
        pnlEast.setPreferredSize(new Dimension(150,150));
        pnlSouth = createPanel(Color.darkGray);
        pnlSouth.setPreferredSize(new Dimension(150,150));
        pnlWest = createPanel(Color.darkGray);
        pnlWest.setPreferredSize(new Dimension(150,150));
        pnlCenter = createPanel(Color.darkGray);
        pnlCenter.setPreferredSize(new Dimension(250,250));


        //#############################
        pnlOne  = createPanel(Color.darkGray); //background Panel
        pnlOne.setLayout(pageLayout);

        pnlOne.add(pnlNorth,BorderLayout.NORTH);
        pnlOne.add(pnlEast,BorderLayout.EAST);
        pnlOne.add(pnlSouth,BorderLayout.SOUTH);
        pnlOne.add(pnlWest,BorderLayout.WEST);
        pnlOne.add(pnlCenter,BorderLayout.CENTER);



        ButtonShelf = createPanel(Color.GRAY);
        MazeDisplayPanel = createPanel(Color.white);
        MazeDisplayPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        ButtonShelf.setLayout(buttonLayout);
        pnlCenter.add(MazeDisplayPanel); // added panel to display list of recent mazes
        pnlOne.add(ButtonShelf);

        ButtonShelf.add(RandomMazeButton);
        ButtonShelf.add(NewMazeButton);
        ButtonShelf.add(LoadButton);

        RandomMazeButton.addActionListener(this);
        NewMazeButton.addActionListener(this);
        LoadButton.addActionListener(this);
        //panel related code to go here
        getContentPane().add(pnlOne,BorderLayout.CENTER);
        pack();
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent event)
    {

        if(event.getSource() == NewMazeButton)
        {
            setVisible(false);
            new editor();
        }
        else if (event.getSource() == RandomMazeButton)
        {

        }
        else if (event.getSource() == LoadButton)
        {
            setVisible(false);
            new LoadScreen();
        }



    }

    @Override
    public void run() {


        setDefaultLookAndFeelDecorated(true);
    }



}



