package GUI;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 3000;
    public static final int HEIGHT = 2000; // set these to have a consistent size between screens

    private JPanel pnlOne;

    private JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);
        return panel;
    }

    private void CreateGUI(){
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pnlOne  = createPanel(Color.white);

        //panel related code to go here
        pack();
        setVisible(true);
    }


    public TitleScreen(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }



}
