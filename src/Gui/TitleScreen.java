package GUI;


import maze.Cell;
import maze.Maze;
import maze.Coordinate;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 3000;
    public static final int HEIGHT = 2000; // set these to have a consistent size between screens
    private static GridBagLayout backgroundLayout = new GridBagLayout();
    private GridBagConstraints mazeLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneLeftLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneRightLayoutConstraints = new GridBagConstraints();
    private Chunk[] chunkArray;
    GridBagLayout mazePaneLayout = new GridBagLayout();
    GridBagConstraints mazePaneConstraints = new GridBagConstraints();




    JFrame    background          = new JFrame();
    JMenuBar  menuBar             = new JMenuBar();
    JMenu     fileBar             = new JMenu("File");
    JMenu     saveBar             = new JMenu("save");
    JMenu     saveAsBar           = new JMenu("Save as");
    JMenu     loadBar             = new JMenu("Load");
    JPanel    mazeDisplayPane     = new JPanel();
    JPanel    displayPaneLeft     = new JPanel();
    JPanel    displayPaneRight    = new JPanel();
    Dimension displayPanesSize    = new Dimension(300,100);

    JScrollPane mazeScroll = new JScrollPane(mazeDisplayPane);




    private Chunk[] initChunks(int chunkCount) {
        Chunk[] chunks = new Chunk[chunkCount];
        for (int i=0;i<chunkCount;i++) {
            chunks[i] = new Chunk();
        }
        return chunks;
    }


    public void CreateGUI(){

        menuBar.add(fileBar);
        fileBar.add(saveBar);
        fileBar.add(saveAsBar);
        fileBar.add(loadBar);
        background.setJMenuBar(menuBar);


        background.setSize(1500,700);
        background.setLocationRelativeTo(null);
        background.setLayout(backgroundLayout);
        background.setDefaultCloseOperation(EXIT_ON_CLOSE);


        displayPaneLeft.setPreferredSize(displayPanesSize);
        displayPaneLeft.setMinimumSize(displayPanesSize);
        displayPaneLeftLayoutConstraints.gridx = 0;
        displayPaneLeftLayoutConstraints.gridy = 0;
        displayPaneLeftLayoutConstraints.weightx = 0.0;
        displayPaneLeftLayoutConstraints.weighty = 0.;
        displayPaneLeftLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneLeft, displayPaneLeftLayoutConstraints);

        mazeLayoutConstraints.gridx = 4;
        mazeLayoutConstraints.gridy = 0;

        mazeLayoutConstraints.weightx = 0.1;
        mazeLayoutConstraints.weighty = 0.1;
        mazeLayoutConstraints.fill = GridBagConstraints.BOTH;
        mazeDisplayPane.setBackground(Color.yellow);
        mazeDisplayPane.setLayout(mazePaneLayout);


        background.add(mazeScroll, mazeLayoutConstraints);


        displayPaneRight.setPreferredSize(displayPanesSize);
        displayPaneRight.setMinimumSize(displayPanesSize);
        displayPaneRightLayoutConstraints.gridx = 5;
        displayPaneRightLayoutConstraints.gridy = 0;
        displayPaneRightLayoutConstraints.weightx = 0.0;
        displayPaneRightLayoutConstraints.weighty = 0.2;
        displayPaneRightLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneRight,displayPaneRightLayoutConstraints);






        this.repaint();
        this.validate();
        pack();
        background.setVisible(true);
    }

    public void AddMaze(Maze maze){

        int [] mazeDim = maze.getDimensions();
        chunkArray = initChunks(mazeDim[0] * mazeDim[1]);

        Coordinate thisCoord;
        int i = 0;
        for(int y = 0; y < mazeDim[1] ; y++){
            for(int x = 0; x < mazeDim[0]; x++){
                thisCoord = new Coordinate(x,y);
                Cell thisCell = maze.getCell(thisCoord);
                mazePaneConstraints.gridx = x;
                mazePaneConstraints.gridy = y;
                mazePaneConstraints.ipady = 1;
                mazePaneConstraints.ipadx = 1;

                mazeDisplayPane.add(chunkArray[i].packChunk(thisCell),mazePaneConstraints);
                i++;
            }
            System.out.print(i+",");
        }
        System.out.print("Done!");

        pack();
        background.revalidate();
        background.repaint();

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }



}
