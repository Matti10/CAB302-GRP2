package GUI;

import maze.Maze;
import db.MazeListData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadScreen extends JFrame implements ActionListener {
    MazeListData mData;
    String[][] dispData;
    String[] formattedData;

    public void setmData(MazeListData mData) {
        this.mData = mData;
        this.dispData = mData.getDisplayData();
        this.formattedData = new String[this.mData.getSize()];
        for (int i=0;i<mData.getSize();i++) {
            this.formattedData[i] = "Maze: "+this.dispData[i][0]+
                    " - Author: "+this.dispData[i][1]+
                    " - Dimensions: "+this.dispData[i][2]+
                    " - Created: "+this.dispData[i][3]+
                    " - Edited: "+this.dispData[i][4];
        }
    }

    private JFrame background = new JFrame();
    JButton loadButton = new JButton("load");
    TitleScreen screen;
    String selectedMaze;

    private GridLayout backgroundLayout = new GridLayout(2,1);

    public void setScreen(TitleScreen titleScreen){this.screen = titleScreen;}


    public void createGUI(){
        background.setLayout(backgroundLayout);
        mazeList.addActionListener(this);
        background.setVisible(true);
        background.add(loadButton);
        background.add(mazeList);
        loadButton.addActionListener(this);
        background.setSize(400,200);
        background.setLocationRelativeTo(null);
    }

    String[] data = new String[] {"Maze: test - Author: author - Dimensions: 3x3 - Created: 20/12/2000 - Edited: 20/12/2000"};
    private JComboBox mazeList = new JComboBox(formattedData);


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadButton){
            if (selectedMaze != null) {
                screen.setImportedMazeName(this.selectedMaze);
                screen.loadImportedMaze();
                this.background.dispose();
            }
        }
        if(e.getSource() == mazeList){
            String selection = (String)mazeList.getSelectedItem();
            if (selection != null) {
                String[] splitSelection = selection.split(" - ");
                char[] chars = splitSelection[0].toCharArray();
                selectedMaze = "";
                for (int i=6;i<chars.length;i++) {
                    selectedMaze+=chars[i];
                }
            }
        }
    }
}
