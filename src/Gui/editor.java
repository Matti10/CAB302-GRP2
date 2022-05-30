package Gui;/*
 *
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class editor extends JFrame{
    private GUIutilities guIutilities = new GUIutilities();

    public editor() {
        super("MAZE EDITOR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel window = new JPanel(new BorderLayout());

        //TASKBAR - more of a line above editor for spacing reasons now - Jhy 29/04/22
        JPanel taskbar = new JPanel();
        taskbar.setBorder(new LineBorder(Color.black));
        taskbar.setPreferredSize(new Dimension(0,40));
        taskbar.setMaximumSize(new Dimension(0,40));
        taskbar.setMinimumSize(new Dimension(0,40));
        window.add(taskbar, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        JMenu insertMenu = new JMenu("Insert");
        JMenu optionsMenu = new JMenu("Insert");

        // Sub menu of fileMenu
        JMenu saveMenu = new JMenu("Save");
        JMenu saveAsMenu = new JMenu("Save As");
        JMenu saveExitMenu = new JMenu("Save & Exit");

        fileMenu.add(saveMenu);
        fileMenu.add(saveAsMenu);
        fileMenu.add(saveExitMenu);
        //*************************

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(insertMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);


        this.setJMenuBar(menuBar);


/* ******************************************************************************* */

        //MAZE
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        JPanel mazePane = new JPanel(layout);
        window.add(mazePane, BorderLayout.CENTER);

        //MAZE -> MAZE DISPLAY


        setPreferredSize(new Dimension(450, 110));
        GridLayout mazeLayout = new GridLayout();
        mazeLayout.setColumns(100);
        mazeLayout.setRows(100);

        JPanel mazeDisplay = new JPanel(mazeLayout);
        mazeDisplay.setSize(100,100);
        mazePane.add(mazeDisplay, BorderLayout.CENTER);


        boolean [] innerArray  = {true,true,true,true};
        boolean [][] testArray  = {};
        List<boolean[]> myList = new ArrayList<boolean[]>();

        for(int i = 0; i < 10000; i++)
        {
            myList.add(i,innerArray);
        }


        for(boolean [] cell : myList ){
            mazeDisplay.add(guIutilities.CreateCell(cell));//pass array to here
        }


        //MAZE -> INFO
        JPanel mazeInfo = new JPanel(new BorderLayout());
        mazeInfo.setBorder(new LineBorder(Color.black));
        mazePane.add(mazeInfo, BorderLayout.SOUTH);

        //MAZE -> INFO -> CURSOR
        JPanel cursorCoordinate = new JPanel();
        mazeInfo.add(cursorCoordinate, BorderLayout.EAST);

        JLabel xCoordinate = new JLabel("x: 4");
        JLabel yCoordinate = new JLabel("y: 3");
        cursorCoordinate.add(xCoordinate);
        cursorCoordinate.add(yCoordinate);

/* ******************************************************************************** */

        //EDIT

        JPanel editPane = new JPanel(new BorderLayout());
        editPane.setBackground(Color.gray);
        editPane.setBorder(new LineBorder(Color.black));
        editPane.setPreferredSize(new Dimension(screenSize.width*2/8,0));
        editPane.setMaximumSize(new Dimension(screenSize.width*2/8,0));
        editPane.setMinimumSize(new Dimension(screenSize.width*2/8,0));
        window.add(editPane, BorderLayout.WEST);

        //EDIT -> TOP HALF
        JPanel topEdit = new JPanel(new BorderLayout());
        topEdit.setBorder(new LineBorder(Color.blue));
        topEdit.setPreferredSize(new Dimension(0,(screenSize.height*3/4-40)/2));
        //topEdit.setMaximumSize(new Dimension(0,(screenSize.height*7/8-40)/2));
        //topEdit.setMinimumSize(new Dimension(0,(screenSize.height/2-40)/2));
        editPane.add(topEdit, BorderLayout.NORTH);

        //EDIT -> TOP HALF -> HEADER
        JLabel tileHeader = new JLabel("Maze Pieces");
        tileHeader.setHorizontalAlignment(JLabel.CENTER);
        topEdit.add(tileHeader, BorderLayout.NORTH);

        //EDIT -> TOP HALF -> TILE SELECT
        JPanel tileSelector = new JPanel(new GridLayout(5,2));
        tileSelector.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10,10,10,10),
                new LineBorder(Color.black)
        ));
        //size prefs
        topEdit.add(tileSelector, BorderLayout.CENTER);

        JButton[] tiles;
        tiles = new JButton[10];
        for (int i=0;i<10;i++) {
            tiles[i] = new JButton(String.valueOf(i));
            tileSelector.add(tiles[i]);
        }

        //EDIT -> BOTTOM HALF
        JPanel btmEdit = new JPanel(new BorderLayout());
        btmEdit.setBorder(new LineBorder(Color.red));
        btmEdit.setPreferredSize(new Dimension(0,(screenSize.height*3/4-40)/2));
        //btmEdit.setMaximumSize(new Dimension(0,(screenSize.height*7/8-40)/2));
        //btmEdit.setMinimumSize(new Dimension(0,(screenSize.height/2-40)/2));
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
        moreOptions.setBorder(new EmptyBorder(10,10,10,10));
        btmEdit.add(moreOptions, BorderLayout.CENTER);

/* ******************************************************************************** */

        //ERROR

        JPanel errorPane = new JPanel(new BorderLayout());
        errorPane.setPreferredSize(new Dimension(0,screenSize.height/8));
        errorPane.setMaximumSize(new Dimension(0,screenSize.height/8));
        errorPane.setMinimumSize(new Dimension(0,screenSize.height/8));
        window.add(errorPane, BorderLayout.SOUTH);

        //ERROR -> HEADER
        JPanel errorPaneHeader = new JPanel();
        errorPaneHeader.setBackground(Color.gray);
        errorPane.add(errorPaneHeader,BorderLayout.NORTH);
        JLabel errorTitle = new JLabel("ERROR LOG");
        errorTitle.setForeground(Color.white);
        errorPaneHeader.add(errorTitle);

        //ERROR -> LOG
        JPanel errorLog = new JPanel(new GridLayout(6,1));
        errorLog.setBackground(Color.lightGray);
        JLabel msg1 = new JLabel(" > error 1");
        JLabel msg2 = new JLabel(" > error 2");
        JLabel msg3 = new JLabel(" > error 3");
        JLabel msg4 = new JLabel(" > error 4");
        JLabel msg5 = new JLabel(" > error 5");
        JLabel msg6 = new JLabel(" > error 6");

        errorPane.add(errorLog,BorderLayout.CENTER);
        errorLog.add(msg1);
        errorLog.add(msg2);
        errorLog.add(msg3);
        errorLog.add(msg4);
        errorLog.add(msg5);
        errorLog.add(msg6);

/* ******************************************************************************** */

        getContentPane().add(window);
        // Display the window.

        //https://alvinalexander.com/blog/post/jfc-swing/how-determine-get-screen-size-java-swing-app/
        setPreferredSize(new Dimension(screenSize.width*7/8,screenSize.height*7/8));
        setMaximumSize(screenSize.getSize());
        setMinimumSize(new Dimension(screenSize.width*5/8,screenSize.height*5/8));
        setLocation(new Point(screenSize.width/16,screenSize.height/16));
        pack();
        setVisible(true);
    }

    /**
     * @param args
     * creates args (???)
     */


}
