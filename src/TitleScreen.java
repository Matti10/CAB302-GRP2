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




    private JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }

    private void CreateGUI(){

        JButton NewMazeButton = new JButton("New Maze");

        JButton RandomMazeButton = new JButton("Random Maze");

        JButton LoadButton = new JButton("Load Maze");

        GridLayout buttonLayout = new GridLayout(3,1); // layout for ButtonShelf

        BorderLayout pageLayout = new BorderLayout(); // layout for page

        buttonLayout.setVgap(40);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //#############################
        pnlNorth = createPanel(Color.pink);
        pnlEast = createPanel(Color.pink);
        pnlSouth = createPanel(Color.pink);
        pnlWest = createPanel(Color.pink);
        pnlCenter = createPanel(Color.pink);


        //#############################
        pnlOne  = createPanel(Color.darkGray); //background Panel

        ButtonShelf = createPanel(Color.GRAY);

        MazeDisplayPanel = createPanel(Color.white);

        pnlOne.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        MazeDisplayPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        ButtonShelf.setLayout(buttonLayout);

        pnlOne.add(MazeDisplayPanel); // added panel to display list of recent mazes

        pnlOne.add(ButtonShelf);



        ButtonShelf.add(RandomMazeButton);

        ButtonShelf.add(NewMazeButton);

        ButtonShelf.add(LoadButton);

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
