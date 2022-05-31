package GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_utilities {

    public JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }

    public JPanel CreateCell(boolean [] array ){

        boolean topWall = array[0];
        boolean bottomWall = array[1];
        boolean leftWall = array[2];
        boolean rightWall = array[3];

        JPanel background = createPanel(Color.white); // background of maze cell
        background.setLayout(new GridLayout(3,3));

        JPanel cellTop     =   createPanel(Color.black);
        JPanel cellBottom  =   createPanel(Color.black);
        JPanel cellLeft    =   createPanel(Color.black);
        JPanel cellRight   =   createPanel(Color.black);
        JPanel cellCenter  =   createPanel(Color.white);
        JPanel cellCorner1 =   createPanel(Color.black);
        JPanel cellCorner2 =   createPanel(Color.black);
        JPanel cellCorner3 =   createPanel(Color.black);
        JPanel cellCorner4 =   createPanel(Color.black);

        // detects if a while should be off and then turns it off
        if(topWall    == false) cellTop.setBackground(Color.white);
        if(bottomWall == false) cellBottom.setBackground(Color.white);
        if(leftWall   == false) cellLeft.setBackground(Color.white);
        if(rightWall  == false) cellRight.setBackground(Color.white);



        // each of the cells will occupy a 3x3 grid

        background.add(cellCorner1);
        background.add(cellTop);
        background.add(cellCorner2);

        background.add(cellLeft);
        background.add(cellCenter);
        background.add(cellRight);

        background.add(cellCorner3);
        background.add(cellBottom);
        background.add(cellCorner4);



        return background;

    }




}


