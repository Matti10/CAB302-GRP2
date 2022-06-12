
package GUI;

import db.MazeListData;
import maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMazeOptionsScreen extends JFrame implements ActionListener {
    JFrame newOptionsFrame = new JFrame();
    GridLayout newOptionsFrameLayout = new GridLayout(4,1);
    JPanel finishPanel = new JPanel();
    JPanel randomMazePanel = new JPanel();
    JPanel sizeOfMazePanel = new JPanel();
    JRadioButton randomMazeYes = new JRadioButton("yes");
    JRadioButton randomMazeNo = new JRadioButton("no");
    JLabel randomiseOptionLabel = new JLabel("Randomize?");
    ButtonGroup group = new ButtonGroup();
    TextField mazeNameTextField = new TextField(7);
    JLabel mazeNameLabel = new JLabel("Name of Maze:");
    GridBagLayout mazeNamePanelLayout = new GridBagLayout();
    JPanel mazeNamePanel = new JPanel();
    JButton finishButton = new JButton("finish");
    TextField mazeHeight = new TextField();
    JLabel mazeHeightLabel = new JLabel("Maze Height");
    TextField mazeLength = new TextField();
    JLabel mazeLengthLabel = new JLabel("Maze Length  ");
    TitleScreen screen;
    JTextField randomValue = new JTextField("0",3);
    JLabel randomValueExplained = new JLabel("enter a value between 0 and 1");
    GridBagLayout randomMazePaneLayout = new GridBagLayout();
    GridBagConstraints randomMazePaneConstraints = new GridBagConstraints();

    public void setScreen(TitleScreen screen){this.screen = screen;}

    public void createNewMazeOptionsGUI(){

        randomMazeNo.setSelected(true);
        randomMazePanel.setLayout(randomMazePaneLayout);
        randomMazePaneConstraints.gridy = 0;
        randomMazePaneConstraints.gridx = 0;
        randomMazePanel.add(randomiseOptionLabel,randomMazePaneConstraints);
        randomMazePaneConstraints.gridy = 0;
        randomMazePaneConstraints.gridx = 1;
        randomMazePanel.add(randomMazeYes,randomMazePaneConstraints);
        randomMazeYes.addActionListener(this);
        randomMazePaneConstraints.gridy = 0;
        randomMazePaneConstraints.gridx = 2;
        randomMazePanel.add(randomMazeNo,randomMazePaneConstraints);
        randomMazeNo.addActionListener(this);
        randomMazePaneConstraints.gridy = 1;
        randomMazePaneConstraints.gridx = 0;
        randomMazePanel.add(randomValueExplained,randomMazePaneConstraints);
        randomMazePaneConstraints.gridy = 1;
        randomMazePaneConstraints.gridx = 1;
        randomMazePanel.add(randomValue,randomMazePaneConstraints);
        newOptionsFrame.add(randomMazePanel);
        group.add(randomMazeNo);
        group.add(randomMazeYes);
        mazeNameTextField.setSize(100,20);
        mazeNamePanel.setLayout(mazeNamePanelLayout);
        mazeNamePanel.add(mazeNameLabel);
        mazeNamePanel.add(mazeNameTextField);



        newOptionsFrame.add(mazeNamePanel);
        sizeOfMazePanel.add(mazeHeightLabel);
        sizeOfMazePanel.add(mazeHeight);
        sizeOfMazePanel.add(mazeLengthLabel);
        sizeOfMazePanel.add(mazeLength);
        newOptionsFrame.add(sizeOfMazePanel);
        newOptionsFrame.add(finishPanel);
        finishButton.addActionListener(this);
        finishPanel.add(finishButton);
        newOptionsFrame.setLayout(newOptionsFrameLayout);
        newOptionsFrame.setVisible(true);
        newOptionsFrame.setResizable(false);
        newOptionsFrame.setSize(400,400);
        newOptionsFrame.setLocationRelativeTo(null);
        newOptionsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == finishButton){

            int len = 0;
            try{
                String lenString = mazeLength.getText();
                len = Integer.parseInt(lenString);
            }
            catch(NumberFormatException Exception){
                Exception.printStackTrace();
            }
            int height = 0;
            try{
                String heightString = mazeHeight.getText();
                height = Integer.parseInt(heightString);

            }
            catch(NumberFormatException exception){
                exception.printStackTrace();
            }
            int randomAmount = 0;
            try{
                String randomString = randomValue.getText();
                randomAmount = Integer.parseInt(randomString);
            }
            catch(NumberFormatException exception){
                exception.printStackTrace();
            }
            String nameOfMaze = mazeNameTextField.getText();
            MazeListData data = new MazeListData();
            Maze maze = Maze.initMaze(len,height,true,1,0,len - 1, height - 1,nameOfMaze);
            screen.setMazeName(nameOfMaze);
            if(randomMazeYes.isSelected() == true){
                maze.setRandomSolution(randomAmount);
            }

            screen.setCurrentMaze(maze);

            this.screen.background.setVisible(true);
            screen.mazeDisplayPane.removeAll();
            screen.AddMaze(maze);
            newOptionsFrame.dispose();

        }


    }
}