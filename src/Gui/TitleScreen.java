package GUI;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 3000;
    public static final int HEIGHT = 2000; // set these to have a consistent size between screens
    private static BorderLayout layout = new BorderLayout();

    JFrame background = new JFrame();
    Chunk maze = new Chunk();





    public void CreateGUI(){

        background.setSize(1500,700);
        background.setLocationRelativeTo(null);



        
        pack();
        background.setVisible(true);
    }

    public void AddMaze(){
        background.getContentPane().setBackground(Color.black);
        background.add(maze.packChunk());
        pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }



}
