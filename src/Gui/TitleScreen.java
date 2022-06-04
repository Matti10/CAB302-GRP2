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

    Chunk test = new Chunk();


    JFrame background = new JFrame();
    Chunk maze = new Chunk();
    JPanel mazeDisplayPane = new JPanel();
    JPanel displayPaneLeft = new JPanel();
    JPanel displayPaneRight = new JPanel();


    JScrollPane mazeScroll = new JScrollPane(mazeDisplayPane);




    private Chunk[] initChunks(int chunkCount) {
        Chunk[] chunks = new Chunk[chunkCount];
        for (int i=0;i<chunkCount;i++) {
            chunks[i] = new Chunk();
        }
        return chunks;
    }


    public void CreateGUI(){
        background.setSize(1500,700);
        background.setLocationRelativeTo(null);
        background.setLayout(backgroundLayout);
        background.setDefaultCloseOperation(EXIT_ON_CLOSE);




        displayPaneLeftLayoutConstraints.gridx = 0;
        displayPaneLeftLayoutConstraints.gridy = 0;
        displayPaneLeftLayoutConstraints.weightx = 0.0;
        displayPaneLeftLayoutConstraints.weighty = 0.1;
        displayPaneLeftLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneLeft, displayPaneLeftLayoutConstraints);

        mazeLayoutConstraints.gridx = 1;
        mazeLayoutConstraints.gridy = 0;
        mazeLayoutConstraints.weightx = 0.1;
        mazeLayoutConstraints.weighty = 0.1;
        mazeLayoutConstraints.fill = GridBagConstraints.BOTH;
        mazeDisplayPane.setBackground(Color.yellow);
        mazeDisplayPane.setLayout(mazePaneLayout);


        background.add(mazeDisplayPane, mazeLayoutConstraints);
        mazeScroll.revalidate();


        displayPaneRightLayoutConstraints.gridx = 2;
        displayPaneRightLayoutConstraints.gridy = 0;
        displayPaneRightLayoutConstraints.weightx = 0.0;
        displayPaneRightLayoutConstraints.weighty = 0.2;
        displayPaneRightLayoutConstraints.fill = GridBagConstraints.VERTICAL;
        background.add(displayPaneRight,displayPaneRightLayoutConstraints);







        pack();
        background.setVisible(true);
    }

    public void AddMaze(Maze maze){

        int [] mazeDim = maze.getDimensions();
        chunkArray = initChunks(mazeDim[0] * mazeDim[1]);

        Coordinate thisCoord;
        int i = 0;
        /*boolean L = true;
        boolean R = false;
        boolean T = true;
        boolean B = false;
        boolean temp;*/
        for(int row = 0; row < mazeDim[1] ; row++){
            for(int column = 0; column < mazeDim[0]; column++){
                thisCoord = new Coordinate(column,row);
                //System.out.print(row+"\n");
                //System.out.print(column+"\n\n");
                Cell thisCell = maze.getCell(thisCoord);
                //thisCell.edit(T,B,L,R);
                mazePaneConstraints.gridx = row;
                mazePaneConstraints.gridy = column;
                mazeDisplayPane.add(chunkArray[i].packChunk(thisCell),mazePaneConstraints);
                i++;
                /*temp = L;
                L = R;
                R = temp;*/
            }
            /*temp = T;
            T = B;
            B = temp;*/
            System.out.print(i+",");
        }
        System.out.print("Done!");

        pack();

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }



}
