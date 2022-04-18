/*
 * Created on 24/04/2006
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class editor extends JFrame {

    public editor() {
        super("MAZE EDITOR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel window = new JPanel(new BorderLayout());

        //TASKBAR
        JPanel taskbar = new JPanel();
        taskbar.setBorder(BorderFactory.createLineBorder(Color.black));
        window.add(taskbar, BorderLayout.NORTH);

        JButton file = new JButton("File");
        JButton edit = new JButton("Edit");
        JButton save = new JButton("Save");
        taskbar.add(file);
        taskbar.add(edit);
        taskbar.add(save);

/**********************************************************************************/

        //MAZE
        JPanel mazePane = new JPanel(new BorderLayout());
        window.add(mazePane, BorderLayout.CENTER);

        //MAZE -> MAZE DISPLAY
        JPanel mazeDisp = new JPanel(new GridLayout(5,5));
        mazePane.add(mazeDisp, BorderLayout.CENTER);

        JButton[] mazeButtons;
        mazeButtons = new JButton[25];
        for (int i=0;i<25;i++) {
            mazeButtons[i] = new JButton(String.valueOf(i));
            mazeDisp.add(mazeButtons[i]);
        }

        //MAZE -> INFO
        JPanel mazeInfo = new JPanel(new BorderLayout());
        mazeInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        mazePane.add(mazeInfo, BorderLayout.SOUTH);

        //MAZE -> INFO -> CURSOR
        JPanel cursorCoord = new JPanel();
        mazeInfo.add(cursorCoord, BorderLayout.EAST);

        JLabel xCoord = new JLabel("x: 4");
        JLabel yCoord = new JLabel("y: 3");
        cursorCoord.add(xCoord);
        cursorCoord.add(yCoord);

/**********************************************************************************/

        //EDIT
        int editMinHeight = 1080;

        JPanel editPane = new JPanel(new BorderLayout());
        editPane.setBorder(BorderFactory.createLineBorder(Color.black));
        editPane.setPreferredSize(new Dimension(300,editMinHeight));
        window.add(editPane, BorderLayout.WEST);

        //EDIT -> TOP HALF
        JPanel topEdit = new JPanel(new BorderLayout());
        topEdit.setPreferredSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()));
        editPane.add(topEdit, BorderLayout.NORTH);

        //EDIT -> TOP HALF -> HEADER
        JLabel tileHeader = new JLabel("Maze Pieces");
        tileHeader.setHorizontalAlignment(JLabel.CENTER);
        topEdit.add(tileHeader, BorderLayout.NORTH);

        //EDIT -> TOP HALF -> TILE SELECT
        JPanel tileSelector = new JPanel(new GridLayout(5,2));
        tileSelector.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new LineBorder(Color.black)));
        tileSelector.setPreferredSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()-20));
        tileSelector.setMaximumSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()-20));
        topEdit.add(tileSelector, BorderLayout.CENTER);

        JButton[] tiles;
        tiles = new JButton[10];
        for (int i=0;i<10;i++) {
            tiles[i] = new JButton(String.valueOf(i));
            tileSelector.add(tiles[i]);
        }

        //EDIT -> BOTTOM HALF
        JPanel btmEdit = new JPanel(new BorderLayout());
        btmEdit.setPreferredSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()));
        editPane.add(btmEdit, BorderLayout.SOUTH);

        JPanel moreOptions = new JPanel(new GridLayout(4,1));

        JButton opt1 = new JButton("opt 1");
        JButton opt2 = new JButton("opt 2");
        JButton opt3 = new JButton("opt 3");
        JButton opt4 = new JButton("opt 4");
        moreOptions.add(opt1);
        moreOptions.add(opt2);
        moreOptions.add(opt3);
        moreOptions.add(opt4);
        moreOptions.setBorder(new EmptyBorder(10, 10, 10, 10));
        moreOptions.setPreferredSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()-20));
        moreOptions.setMaximumSize(new Dimension(300,(editMinHeight/2)-taskbar.getHeight()-20));
        btmEdit.add(moreOptions, BorderLayout.CENTER);

/**********************************************************************************/

        getContentPane().add(window);
        // Display the window.
        setPreferredSize(new Dimension(1920, 1080));
        setMinimumSize(new Dimension(1280,720));
        setLocation(new Point(200, 100));
        pack();
        setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        setDefaultLookAndFeelDecorated(true);
        new editor();
    }

}
