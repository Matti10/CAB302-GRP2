package GUI;

import db.MazeListData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadScreen extends JFrame implements ActionListener {
    public String[] getData() {
        MazeListData mData = new MazeListData();
        String[][] dispData = mData.getDisplayData();
        String[] formattedData = new String[mData.getSize()];
        for (int i=0;i<mData.getSize();i++) {
            long createdTime = Long.parseLong(dispData[i][3]);
            long editedTime = Long.parseLong(dispData[i][4]);
            Date createdDate = new java.util.Date(createdTime*1000L);
            Date editedDate = new java.util.Date(editedTime*1000L);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
            //sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+10"));

            formattedData[i] = "Maze: "+dispData[i][0]+
                    " - Author: "+dispData[i][1]+
                    " - Dimensions: "+dispData[i][2]+
                    " - Created: "+sdf.format(createdDate)+
                    " - Edited: "+sdf.format(editedDate);
        }
        return formattedData;
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
        background.setSize(800,200);
        background.setLocationRelativeTo(null);
    }

    private JComboBox mazeList = new JComboBox(getData());

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadButton){
            if (selectedMaze != null) {
                screen.background.setVisible(true);
                screen.setMazeName(this.selectedMaze);
                screen.loadImportedMaze();
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
