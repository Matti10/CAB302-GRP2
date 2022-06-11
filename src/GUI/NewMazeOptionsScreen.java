
package GUI;

import db.MazeListData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMazeOptionsScreen extends JFrame implements ActionListener {
    JFrame newOptionsFrame = new JFrame();
    GridLayout newOptionsFrameLayout = new GridLayout();
    JPanel randomMazePanel = new JPanel();
    JPanel authorPanel = new JPanel();
    JPanel sizeOfMazePanel = new JPanel();
    JRadioButton randomMazeYes = new JRadioButton("yes");
    JRadioButton randomMazeNo = new JRadioButton("no");
    MazeListData data;
    ButtonGroup group = new ButtonGroup();
    TextField author = new TextField(7);

    public void CreateNewMazeOptionsGUI(){
        randomMazeNo.setSelected(true);
        randomMazePanel.add(randomMazeYes);
        randomMazePanel.add(randomMazeNo);
        newOptionsFrame.add(randomMazePanel);
        randomMazePanel.setBackground(Color.yellow);
        group.add(randomMazeNo);
        group.add(randomMazeYes);
        author.setSize(100,20);
        authorPanel.setBackground(Color.pink);
        authorPanel.add(author);
        sizeOfMazePanel.setBackground(Color.black);

        newOptionsFrame.add(authorPanel);
        newOptionsFrame.add(sizeOfMazePanel);
        newOptionsFrame.setLayout(newOptionsFrameLayout);
        newOptionsFrame.setVisible(true);
        newOptionsFrame.setResizable(false);
        newOptionsFrame.setSize(400,400);
        newOptionsFrame.setLocationRelativeTo(null);
        TitleScreen titleScreen = new TitleScreen();
        newOptionsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}