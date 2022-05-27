package Gui;

import javax.swing.*;
import java.awt.*;

public class GUIutilities {

    public JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }

    public void CreateCell(){
       JPanel background = createPanel(Color.white); // background of maze cell
        background.setLayout(new GridLayout(3,3));

        JPanel cellCorner1 = createPanel(Color.black);
        JPanel cellNorth = createPanel(Color.white);
        JPanel cellCorner2 = createPanel(Color.black);
        JPanel cellWest = createPanel(Color.white);
        JPanel cellCorner3 = createPanel(Color.black);
        JPanel cellEast = createPanel(Color.white);
        JPanel cellCorner4= createPanel(Color.black);
        JPanel cellSouth = createPanel(Color.white);
        JPanel cellCenter = createPanel(Color.white);

        // each of the cells will occupy a 3x3 grid

        background.add(cellCorner1);
        background.add(cellNorth);
        background.add(cellCorner2);
        background.add(cellWest);
        background.add(cellCorner3);
        background.add(cellEast);
        background.add(cellCorner4);
        background.add(cellSouth);
        background.add(cellCenter);

    }




}


