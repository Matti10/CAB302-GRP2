package GUI;
import maze.Cell;
import maze.Coordinate;
import org.hamcrest.core.Every;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chunk extends JFrame implements MouseListener {
    GUI_utilities utility = new GUI_utilities();
    private TitleScreen screen;

    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints layoutConstraints = new GridBagConstraints();
    JPanel northEastCorner = utility.createPanel(Color.black);
    JPanel southEastCorner = utility.createPanel(Color.black);
    JPanel southWestCorner = utility.createPanel(Color.black);
    JPanel northWestCorner = utility.createPanel(Color.black);
    JPanel centerCell      = utility.createPanel(Color.white); //////////////
    JPanel topCell         = utility.createPanel(Color.black);
    JPanel bottomCell      = utility.createPanel(Color.black);
    JPanel rightCell       = utility.createPanel(Color.black);
    JPanel leftCell        = utility.createPanel(Color.black);
    JPanel backgroundCell = new JPanel();
    JPanel editorBackgroundCell = new JPanel();
    private boolean clicked = false;
    private Cell cellValue;
    private Coordinate coordinateValue;
    public boolean [] editorChunkValue;

    public void setCellValue(Cell cell){this.cellValue = cell;}

    public Cell getCellValue() {
        return cellValue;
    }
    public void setCoordinateValue(Coordinate coordinate){this.coordinateValue = coordinate;}

    public Coordinate getCoordinateValue() {
        return coordinateValue;
    }

    public void setScreen(TitleScreen parentScreen){
       this.screen = parentScreen;
    }


    public JPanel packChunk(Cell cell){ //chunks have to be added from left to right top to bottom
        boolean [] walls   = cell.toWallList();
        boolean bottomWall = walls[0];
        boolean leftWall   = walls[1];
        boolean rightWall  = walls[2];
        boolean topWall    = walls[3];



        this.backgroundCell.addMouseListener(this);
        this.backgroundCell.setLayout(layout);
        this.backgroundCell.setBackground(Color.gray);
        Dimension centerCellSize = new Dimension(20,20);

        Dimension  eastWestCellSize = new Dimension(20,5);
        Dimension  northSouthCellSize = new Dimension(5,20);
        Dimension  cornerCellSize = new Dimension(5,5);

        centerCell.setPreferredSize(centerCellSize);
        centerCell.setMinimumSize(centerCellSize);

        rightCell.setMinimumSize(eastWestCellSize);
        leftCell.setMinimumSize(eastWestCellSize);
        topCell.setMinimumSize(northSouthCellSize);
        bottomCell.setMinimumSize(northSouthCellSize);
        northEastCorner.setMinimumSize(cornerCellSize);
        northWestCorner.setMinimumSize(cornerCellSize);
        southWestCorner.setMinimumSize(cornerCellSize);
        southEastCorner.setMinimumSize(cornerCellSize);




        //row one
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 0;
        this.backgroundCell.add(this.northWestCorner,layoutConstraints);
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 1;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        this.backgroundCell.add(this.topCell,layoutConstraints);
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 2;
        this.backgroundCell.add(this.northEastCorner,layoutConstraints);
        //row 2
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 0;
        this.backgroundCell.add(this.leftCell,layoutConstraints);
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 1;
        layoutConstraints.fill = GridBagConstraints.NONE;
        this.backgroundCell.add(this.centerCell,layoutConstraints);
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        this.backgroundCell.add(this.rightCell,layoutConstraints);
        //row 3
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 0;
        this.backgroundCell.add(this.southWestCorner,layoutConstraints);
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 1;
        this.backgroundCell.add(this.bottomCell,layoutConstraints);
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 2;
        this.backgroundCell.add(this.southEastCorner,layoutConstraints);



        if(topWall    == false) topCell.setBackground(Color.white);
        if(bottomWall == false) bottomCell.setBackground(Color.white);
        if(leftWall   == false) leftCell.setBackground(Color.white);
        if(rightWall  == false) rightCell.setBackground(Color.white);
        if (topWall && bottomWall && leftWall && rightWall){
            centerCell.setBackground(Color.black);
        }

        pack();

        return this.backgroundCell;

    }

    public JPanel customChunk(boolean bottomWall, boolean leftWall, boolean rightWall, boolean topWall){ //chunks have to be added from left to right top to bottom
        this.editorChunkValue = new boolean[]{bottomWall, leftWall, rightWall, topWall};
        this.editorBackgroundCell.addMouseListener(this);
        this.editorBackgroundCell.setLayout(layout);
        this.editorBackgroundCell.setBackground(Color.gray);
        Dimension centerCellSize = new Dimension(40,40);
        Dimension  eastWestCellSize = new Dimension(40,10);
        Dimension  northSouthCellSize = new Dimension(10,40);
        Dimension  cornerCellSize = new Dimension(10,10);

        centerCell.setPreferredSize(centerCellSize);
        centerCell.setMinimumSize(centerCellSize);

        rightCell.setMinimumSize(eastWestCellSize);
        leftCell.setMinimumSize(eastWestCellSize);
        topCell.setMinimumSize(northSouthCellSize);
        bottomCell.setMinimumSize(northSouthCellSize);
        northEastCorner.setMinimumSize(cornerCellSize);
        northWestCorner.setMinimumSize(cornerCellSize);
        southWestCorner.setMinimumSize(cornerCellSize);
        southEastCorner.setMinimumSize(cornerCellSize);




        //row one
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 0;
        this.editorBackgroundCell.add(this.northWestCorner,layoutConstraints);
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 1;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        this.editorBackgroundCell.add(this.topCell,layoutConstraints);
        layoutConstraints.gridy = 0;
        layoutConstraints.gridx = 2;
        this.editorBackgroundCell.add(this.northEastCorner,layoutConstraints);
        //row 2
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 0;
        this.editorBackgroundCell.add(this.leftCell,layoutConstraints);
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 1;
        layoutConstraints.fill = GridBagConstraints.NONE;
        this.editorBackgroundCell.add(this.centerCell,layoutConstraints);
        layoutConstraints.gridy = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        this.editorBackgroundCell.add(this.rightCell,layoutConstraints);
        //row 3
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 0;
        this.editorBackgroundCell.add(this.southWestCorner,layoutConstraints);
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 1;
        this.editorBackgroundCell.add(this.bottomCell,layoutConstraints);
        layoutConstraints.gridy = 2;
        layoutConstraints.gridx = 2;
        this.editorBackgroundCell.add(this.southEastCorner,layoutConstraints);



        if(topWall    == false) topCell.setBackground(Color.white);
        if(bottomWall == false) bottomCell.setBackground(Color.white);
        if(leftWall   == false) leftCell.setBackground(Color.white);
        if(rightWall  == false) rightCell.setBackground(Color.white);
        int wallsOn = 0;
        if (topWall && bottomWall && leftWall && rightWall){
            centerCell.setBackground(Color.black);
        }

        pack();

        return this.editorBackgroundCell;

    }

    private void editChunk(){
        boolean [] clickedCellValue = screen.getCurrentlySelectedCell();

        boolean bottomWall = clickedCellValue[0];
        boolean leftWall   = clickedCellValue[1];
        boolean rightWall  = clickedCellValue[2];
        boolean topWall    = clickedCellValue[3];
        this.cellValue.edit(topWall,bottomWall,leftWall,rightWall);
        topCell.setBackground(Color.black);
        bottomCell.setBackground(Color.black);
        leftCell.setBackground(Color.black);
        rightCell.setBackground(Color.black);
        centerCell.setBackground(Color.white);
        if(topWall    == false) topCell.setBackground(Color.white);
        if(bottomWall == false) bottomCell.setBackground(Color.white);
        if(leftWall   == false) leftCell.setBackground(Color.white);
        if(rightWall  == false) rightCell.setBackground(Color.white);
        if (topWall && bottomWall && leftWall && rightWall){
            centerCell.setBackground(Color.black);
        }
        this.repaint();
        this.revalidate();
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == editorBackgroundCell) {
            screen.setCurrentlySelectedCell(editorChunkValue);
        }
        if(e.getSource() == backgroundCell){
            editChunk();
            System.out.print(this.coordinateValue);
            //System.out.print(this.cellValue);
        }


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
}