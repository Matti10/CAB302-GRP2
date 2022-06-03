package GUI;
import maze.Cell;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Chunk extends JFrame{
    GUI_utilities utility = new GUI_utilities();

    GridLayout layout = new GridLayout(3,3,6,6);
    JPanel northEastCorner = utility.createPanel(Color.black);
    JPanel southEastCorner = utility.createPanel(Color.black);
    JPanel southWestCorner = utility.createPanel(Color.black);
    JPanel northWestCorner = utility.createPanel(Color.black);
    JPanel centerCell      = utility.createPanel(Color.pink);
    JPanel topCell         = utility.createPanel(Color.black);
    JPanel bottomCell      = utility.createPanel(Color.black);
    JPanel rightCell       = utility.createPanel(Color.black);
    JPanel leftCell        = utility.createPanel(Color.black);
    JPanel backgroundCell = new JPanel();



    public JPanel packChunk(Cell cell){ //chunks have to be added from left to right top to bottom
        boolean [] walls =cell.toWallList();
        boolean bottomWall = walls[0];
        boolean leftWall   = walls[1];
        boolean rightWall  = walls[2];
        boolean topWall    = walls[3];
        //Dimension TestD = new Dimension(20,20);
        //backgroundCell.setPreferredSize(TestD);


        this.backgroundCell.setLayout(layout);
        this.backgroundCell.setBackground(Color.GRAY);
        //row one
        this.backgroundCell.add(this.northWestCorner);
        this.backgroundCell.add(this.topCell);
        this.backgroundCell.add(this.northEastCorner);
        //row 2
        this.backgroundCell.add(this.leftCell);
        this.backgroundCell.add(this.centerCell);
        this.backgroundCell.add(this.rightCell);
        //row 3
        this.backgroundCell.add(this.southWestCorner);
        this.backgroundCell.add(this.bottomCell);
        this.backgroundCell.add(this.southEastCorner);
        pack();


        if(topWall    == false) topCell.setBackground(Color.black);
        if(bottomWall == false) bottomCell.setBackground(Color.black);
        if(leftWall   == false) leftCell.setBackground(Color.black);
        if(rightWall  == false) rightCell.setBackground(Color.black);

        return this.backgroundCell;
    }








}