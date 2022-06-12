package GUI;


import maze.Cell;
import maze.Maze;
import maze.Coordinate;

import  java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

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
    BorderLayout rightPaneLayout = new BorderLayout();
    private String importedMazeName;

    private boolean test = false;

    public void setImportedMazeName(String importedMazeName) {
        this.importedMazeName = importedMazeName;
    }

    JFrame    background          = new JFrame();
    JMenuBar  menuBar             = new JMenuBar();
    JMenu     fileBar             = new JMenu("File");
    JMenuItem saveBar             = new JMenuItem("save");
    JMenuItem saveAsBar           = new JMenuItem("Save as");
    JMenuItem loadBar             = new JMenuItem("Load");
    JMenuItem newMazeBar          = new JMenuItem("New Maze");
    JMenuItem exportMazeBar       = new JMenuItem("export");
    JPanel    mazeDisplayPane     = new JPanel();
    JPanel    displayPaneLeft     = new JPanel();
    JPanel    displayPaneRight    = new JPanel();
    Dimension displayPanesSize    = new Dimension(300,100);
    JPanel    mazePiecesDisplay   = new JPanel();
    private Maze currentMaze;
    public void setCurrentMaze(Maze maze){this.currentMaze = maze;}

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
        fileBar.add(newMazeBar);
        fileBar.add(exportMazeBar);
        exportMazeBar.addActionListener(this);
        newMazeBar.addActionListener(this);
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
        mazeDisplayPane.setBackground(Color.darkGray);
        mazeDisplayPane.setLayout(mazePaneLayout);


        background.add(mazeScroll, mazeLayoutConstraints);

        displayPaneRight.setLayout(rightPaneLayout);
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
        JProgressBar loadingBar = new JProgressBar();
        loadingBar.setValue(0);
        loadingBar.setStringPainted(true);
        displayPaneRight.add(loadingBar);
        currentMaze = maze;
        mazeDisplayPane.removeAll();
        int [] mazeDim = maze.getDimensions();
        chunkArray = initChunks(mazeDim[0] * mazeDim[1]);
        //used for loading bar
        double totalChunks = mazeDim[1] * mazeDim[0];
        double chunksCompleted = 0;
        int percentCompleted = 0;
        //
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
                chunkArray[i].setCellValue(thisCell);
                chunkArray[i].setCoordinateValue(thisCoord);
                mazeDisplayPane.add(chunkArray[i].packChunk(thisCell),mazePaneConstraints);
                i++;
                //loading bar
                chunksCompleted++;
                percentCompleted = (int)((chunksCompleted / totalChunks) * 100 );
                loadingBar.setValue(percentCompleted);
            }
            System.out.print(i+",");
        }
        System.out.print("Done!");
        displayPaneRight.remove(loadingBar);
        pack();
        background.revalidate();
        background.repaint();

    }

    public void loadImportedMaze() {
        Maze importedMaze = currentMaze.importMaze("someName");
        this.AddMaze(importedMaze);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == loadBar){
            System.out.print("\nLoad Menu Item Clicked\n");
            LoadScreen lScreen = new LoadScreen();
            background.setVisible(false);
            lScreen.createGUI();
            lScreen.setScreen(this);
        }
        if(event.getSource() == saveBar){
            System.out.print("\nSave Menu Item Clicked\n");
            currentMaze.exportMaze("someName","author");
        }
        if ( event.getSource() == newMazeBar){
            NewMazeOptionsScreen optionsScreen = new NewMazeOptionsScreen();
            optionsScreen.createNewMazeOptionsGUI();
            optionsScreen.setScreen(this);
            background.setVisible(false);
        }
        if(event.getSource() == exportMazeBar){
            Dimension d = mazeDisplayPane.getSize();
            BufferedImage image = new BufferedImage(d.width,d.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            mazeDisplayPane.print(g2d);
            g2d.dispose();
            try {
                ImageIO.write(image, "PNG", new File("test.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
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
