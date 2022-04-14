import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000; // set these to have a consistent size between screens

    private JPanel pnlOne;
    private JPanel MazeDisplayPanel;

    private JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }

    private void CreateGUI(){
        JButton NewMazeButton = new JButton("New Maze");
        JButton RandomMazeButton = new JButton("Random Maze");
        JButton LoadButton = new JButton("Load Maze");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pnlOne  = createPanel(Color.darkGray); //background
        MazeDisplayPanel = createPanel(Color.white);
        pnlOne.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        MazeDisplayPanel.setBorder(BorderFactory.createEmptyBorder(300, 30, 10, 30));


        pnlOne.add(MazeDisplayPanel);
        pnlOne.add(RandomMazeButton);
        pnlOne.add(NewMazeButton);
        pnlOne.add(LoadButton);

        //panel related code to go here
        getContentPane().add(pnlOne,BorderLayout.CENTER);
        pack();
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
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

        CreateGUI();
    }



}
