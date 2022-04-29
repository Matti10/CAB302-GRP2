package Gui;


import com.sun.source.tree.ContinueTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoadOptionsScreen extends JFrame implements ActionListener, Runnable {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    GUIutilities guIutilities = new GUIutilities();

    JButton backButton = new JButton("Back");
    JButton continueButton = new JButton("Finish");

    GridLayout grdLayout = new GridLayout(0, 3);
    public LoadOptionsScreen()
    {
        JPanel backButtonShelf = guIutilities.createPanel(Color.darkGray);
        JPanel background = guIutilities.createPanel(Color.darkGray);
        JPanel pnlEast = guIutilities.createPanel(Color.white);
        pnlEast.setPreferredSize(new Dimension(150,310));
        JPanel pnlSouth = guIutilities.createPanel(Color.darkGray);
        pnlSouth.setPreferredSize(new Dimension(150,70));
        pnlSouth.add(backButtonShelf);
        backButtonShelf.setSize(30,100);
        pnlSouth.setLayout(grdLayout);
        JPanel pnlWest = guIutilities.createPanel(Color.darkGray);
        pnlWest.setPreferredSize(new Dimension(150,310));
        JPanel pnlNorth = guIutilities.createPanel(Color.darkGray);
        pnlNorth.setPreferredSize(new Dimension(150,70));
        JPanel pnlCenter = guIutilities.createPanel(Color.white);
        pnlCenter.setPreferredSize(new Dimension(150,310));
        background.setLayout(new BorderLayout());

        backButtonShelf.add(backButton);
        backButton.setSize(30,30);
        pnlWest.add(continueButton);

        background.add(pnlCenter,BorderLayout.CENTER);
       // background.add(pnlEast,BorderLayout.EAST); Removed for now
        background.add(pnlSouth,BorderLayout.SOUTH);
        background.add(pnlWest,BorderLayout.WEST);
        background.add(pnlNorth,BorderLayout.NORTH);
        getContentPane().add(background,BorderLayout.CENTER);

        setVisible(true);
        this.pack();
        this.setSize(WIDTH,HEIGHT);
        setResizable(false);


        continueButton.addActionListener(this);
        backButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if(event.getSource() == continueButton)
        {
            setVisible(false);
            new editor();
        }
        else if(event.getSource() == backButton)
        {
            setVisible(false);
            new TitleScreen();
        }

    }

    @Override
    public void run() {

    }
}
