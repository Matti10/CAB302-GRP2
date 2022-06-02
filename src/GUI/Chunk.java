package GUI;
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
    JPanel topCell         = utility.createPanel(Color.white);
    JPanel bottomCell      = utility.createPanel(Color.white);
    JPanel rightCell       = utility.createPanel(Color.white);
    JPanel leftCell        = utility.createPanel(Color.white);
    JPanel backgroundCell = new JPanel();


    public JPanel packChunk(){ //chunks have to be added from left to right top to bottom
        this.backgroundCell.setLayout(layout);
        this.backgroundCell.setSize(15,15);
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
        return this.backgroundCell;
    }








}