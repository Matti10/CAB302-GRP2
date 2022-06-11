package GUI;


import maze.Cell;
import maze.Maze;
import maze.Coordinate;

import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable, MouseListener, PropertyChangeListener {

    public static final int WIDTH = 3000;
    public static final int HEIGHT = 2000; // set these to have a consistent size between screens
    private static GridBagLayout backgroundLayout = new GridBagLayout();
    private GridBagConstraints mazeLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneLeftLayoutConstraints = new GridBagConstraints();
    private GridBagConstraints displayPaneRightLayoutConstraints = new GridBagConstraints();
    private Chunk[] chunkArray;
    GridBagLayout mazePaneLayout = new GridBagLayout();
    GridBagConstraints mazePaneConstraints = new GridBagConstraints();
    private boolean [] currentlySelectedCell;

    private boolean test = false;




    JFrame    background          = new JFrame();
    JMenuBar  menuBar             = new JMenuBar();
    JMenu     fileBar             = new JMenu("File");
    JMenuItem saveBar             = new JMenuItem("save");
    JMenuItem saveAsBar           = new JMenuItem("Save as");
    JMenuItem loadBar             = new JMenuItem("Load");
    JPanel    mazeDisplayPane     = new JPanel();
    JPanel    displayPaneLeft     = new JPanel();
    JPanel    displayPaneRight    = new JPanel();
    Dimension displayPanesSize    = new Dimension(300,100);
    JPanel    mazePiecesDisplay   = new JPanel();

    GridLayout mazePiecesDisplayLayout = new GridLayout(4,4,3,3);

    JScrollPane mazeScroll = new JScrollPane(mazeDisplayPane);

    // Dead Ends
    Chunk northDeadEnd = new Chunk();
    Chunk eastDeadEnd  = new Chunk();
    Chunk southDeadEnd = new Chunk();
    Chunk westDeadEnd  = new Chunk();

    // T pieces
    Chunk northTPiece = new Chunk();
    Chunk eastTPiece  = new Chunk();
    Chunk southTPiece = new Chunk();
    Chunk westTPiece  = new Chunk();
    // L pieces
    Chunk northLPiece = new Chunk();
    Chunk eastLPiece  = new Chunk();
    Chunk westLPiece  = new Chunk();
    Chunk southLPiece = new Chunk();
    // Straight pieces
    Chunk horizontalPiece = new Chunk();
    Chunk verticlePiece = new Chunk();
    // Absolute pieces
    Chunk allWalls = new Chunk();
    Chunk allPaths = new Chunk();


    private Maze currentMaze;


    private Chunk[] initChunks(int chunkCount) {
        Chunk[] chunks = new Chunk[chunkCount];
        for (int i=0;i<chunkCount;i++) {
            chunks[i] = new Chunk();
            chunks[i].setScreen(this);
        }
        return chunks;
    }

    public void setCurrentlySelectedCell(boolean [] value){
        currentlySelectedCell = value;
    }
    public boolean [] getCurrentlySelectedCell(){return currentlySelectedCell;}


    private void createMazePieces(){



        // Dead Ends
        northDeadEnd.setScreen(this);
        eastDeadEnd.setScreen(this);

        southDeadEnd.setScreen(this);

        westDeadEnd.setScreen(this);

        // T pieces
        northTPiece.setScreen(this);
        eastTPiece.setScreen(this);
        southTPiece.setScreen(this);
        westTPiece.setScreen(this);
        // L pieces
        northLPiece.setScreen(this);
        eastLPiece.setScreen(this);
        westLPiece.setScreen(this);
        southLPiece.setScreen(this);
        // Straight pieces
        horizontalPiece.setScreen(this);
        verticlePiece.setScreen(this);
        // Absolute pieces
        allWalls.setScreen(this);
        allPaths.setScreen(this);



        mazePiecesDisplay.setLayout(mazePiecesDisplayLayout);
        // Dead Ends
        mazePiecesDisplay.add(northDeadEnd.customChunk(false,true,true,true));
        mazePiecesDisplay.add(eastDeadEnd.customChunk (true,false,true,true));
        mazePiecesDisplay.add(southDeadEnd.customChunk(true,true,true,false));
        mazePiecesDisplay.add(westDeadEnd.customChunk (false,true,true,true));
        // T pieces
        mazePiecesDisplay.add(northTPiece.customChunk(true,false,false,false));
        mazePiecesDisplay.add(eastTPiece.customChunk (false,true,false,false));
        mazePiecesDisplay.add(southTPiece.customChunk(false,false,false,true));
        mazePiecesDisplay.add(westTPiece.customChunk (false,false,true,false));
        // L pieces
        mazePiecesDisplay.add(northLPiece.customChunk(true,true,false,false));
        mazePiecesDisplay.add(eastLPiece.customChunk (false,true,false,true));
        mazePiecesDisplay.add(southLPiece.customChunk(false,false,true,true));
        mazePiecesDisplay.add(westLPiece.customChunk (true,false,true,false));
        // Straight pieces
        mazePiecesDisplay.add(horizontalPiece.customChunk (true,false,false,true));
        mazePiecesDisplay.add(verticlePiece.customChunk (false,true,true,false));

        // Absolute pieces
        mazePiecesDisplay.add(allWalls.customChunk (true,true,true,true));
        mazePiecesDisplay.add(allPaths.customChunk (false,false,false,false));





    }


    public void CreateGUI(){

        menuBar.add(fileBar);
        fileBar.add(saveBar);
        saveBar.addActionListener(this);
        fileBar.add(saveAsBar);
        fileBar.add(loadBar);
        loadBar.addActionListener(this);
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

        mazePiecesDisplay.setBackground(Color.gray);  //add gui elements to display editing options
        createMazePieces();
        displayPaneLeft.add(mazePiecesDisplay);

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
        currentMaze = maze;
        mazeDisplayPane.removeAll();
        int [] mazeDim = maze.getDimensions();
        chunkArray = initChunks(mazeDim[0] * mazeDim[1]);
        Dimension ChunkSize = new Dimension(200,200);
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

                chunkArray[i].setMinimumSize(ChunkSize);
                chunkArray[i].setPreferredSize(ChunkSize);

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
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == loadBar){
            System.out.print("\nLoad Menu Item Clicked\n");
            Maze importedMaze = currentMaze.importMaze("someName");
            //Maze testBlankMaze = Maze.initMaze(3,3,false,(1,0),)

            this.AddMaze(importedMaze);
        }
        if(event.getSource() == saveBar){
            System.out.print("\nSave Menu Item Clicked\n");
            currentMaze.exportMaze("someName","author","1654643415");
        }

    }

    @Override
    public void run() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
