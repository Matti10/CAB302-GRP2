package GUI;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Chunk extends JFrame{
    GUI_utilities utility = new GUI_utilities();

    JPanel northEastCorner = utility.createPanel(Color.black);
    JPanel southEastCorner = utility.createPanel(Color.black);
    JPanel southWestCorner = utility.createPanel(Color.black);
    JPanel northWestCorner = utility.createPanel(Color.black);
    JPanel centerCell      = utility.createPanel(Color.white);
    JPanel topCell         = utility.createPanel(Color.white);
    JPanel bottomCell      = utility.createPanel(Color.white);
    JPanel rightCell       = utility.createPanel(Color.white);
    JPanel leftCell        = utility.createPanel(Color.white);
    JFrame background = new JFrame();

    public JFrame packChunk(){
        this.background.add(this.northEastCorner);
        this.background.add(this.southEastCorner);
        this.background.add(this.southWestCorner);
        this.background.add(this.northWestCorner);
        this.background.add(this.centerCell);
        this.background.add(this.topCell);
        this.background.add(this.bottomCell);
        this.background.add(this.rightCell);
        this.background.add(this.leftCell);
        pack();
        return this.background;
    }






}