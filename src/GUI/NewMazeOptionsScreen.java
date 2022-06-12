
package GUI;

import db.MazeListData;

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
    MazeListData data;
    ButtonGroup group = new ButtonGroup();
    TextField authorTextField = new TextField(7);

    JLabel authorLabel           = new JLabel("Author:");
    GridBagLayout authorPanelLayout = new GridBagLayout();
    JPanel authorPanel           = new JPanel();
    JButton finishButton = new JButton("finish");

    TextField mazeHeight = new TextField();
    JLabel mazeHeightLabel = new JLabel("Maze Height");
    TextField mazeLength = new TextField();
    JLabel mazeLengthLabel = new JLabel("Maze Length  ");

    public void CreateNewMazeOptionsGUI(){

        randomMazeNo.setSelected(true);
        randomMazePanel.add(randomiseOptionLabel);
        randomMazePanel.add(randomMazeYes);
        randomMazePanel.add(randomMazeNo);
        newOptionsFrame.add(randomMazePanel);
        randomMazePanel.setBackground(Color.yellow);
        group.add(randomMazeNo);
        group.add(randomMazeYes);
        authorTextField.setSize(100,20);
        authorPanel.setBackground(Color.pink);
        authorPanel.setLayout(authorPanelLayout);
        authorPanel.add(authorLabel);
        authorPanel.add(authorTextField);



        newOptionsFrame.add(authorPanel);
        sizeOfMazePanel.add(mazeHeightLabel);
        sizeOfMazePanel.add(mazeHeight);
        sizeOfMazePanel.add(mazeLengthLabel);
        sizeOfMazePanel.add(mazeLength);
        sizeOfMazePanel.setBackground(Color.black);
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

    }
}