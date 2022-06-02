package GUI;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 3000;
    public static final int HEIGHT = 2000; // set these to have a consistent size between screens
    private static GridBagLayout backgroundLayout = new GridBagLayout();
    private GridBagConstraints mazeLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneLeftLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneRightLayoutConstraints = new GridBagConstraints();

    JFrame background = new JFrame();
    Chunk maze = new Chunk();
    JPanel mazeDisplayPane = new JPanel();
    JPanel displayPaneLeft = new JPanel();
    JPanel displayPaneRight = new JPanel();


    public void CreateGUI(){
        background.setSize(1500,700);
        background.setLocationRelativeTo(null);
        background.setLayout(backgroundLayout);
        background.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mazeLayoutConstraints.gridx = 1;
        mazeLayoutConstraints.gridy = 0;
        mazeLayoutConstraints.gridheight = 0;
        mazeLayoutConstraints.gridwidth = 0;
        mazeLayoutConstraints.weightx = 0;
        mazeLayoutConstraints.weighty = 0;
        mazeLayoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        mazeDisplayPane.setBackground(Color.yellow);
        background.add(mazeDisplayPane, mazeLayoutConstraints);


        displayPaneLeftLayoutConstraints.gridx = 0;
        displayPaneLeftLayoutConstraints.gridy = 0;
        displayPaneLeftLayoutConstraints.gridheight = 1;
        displayPaneLeftLayoutConstraints.gridwidth = 0;
        displayPaneLeftLayoutConstraints.weightx = 0.0;
        displayPaneLeftLayoutConstraints.weighty = 0.1;
        displayPaneLeftLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneLeft, displayPaneLeftLayoutConstraints);
        /*
        displayPaneRightLayoutConstraints.gridx = 3;
        displayPaneRightLayoutConstraints.gridy = 0;
        displayPaneRightLayoutConstraints.gridheight = 1;
        displayPaneRightLayoutConstraints.gridwidth = 0;
        displayPaneRightLayoutConstraints.weightx = 0.0;
        displayPaneRightLayoutConstraints.weighty = 0.1;
        displayPaneRightLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneLeft,displayPaneRightLayoutConstraints);

         */






        pack();
        background.setVisible(true);
    }

    public void AddMaze(){
        background.getContentPane().setBackground(Color.black);
        pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }



}
