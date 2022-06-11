package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadScreen extends JFrame implements ActionListener {

    private JFrame background = new JFrame();
    JButton loadButton = new JButton("load");
    TitleScreen screen;
    String selectedMaze;
    private GridLayout backgroundLayout = new GridLayout(2,1);

    public void setScreen(TitleScreen titleScreen){this.screen = titleScreen;}
    String [] data = {"Author: Jhy, MazeName: The Deep, Dimensions: 40 x 40, Time Create: 104010, Last Edited"};
    private JComboBox mazeList = new JComboBox(data);


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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadButton){
            screen.setImportedMazeName(this.selectedMaze);
            //screen.loadImportedMaze();
            this.background.dispose();

        }
        if(e.getSource() == mazeList){
            selectedMaze = (String)mazeList.getSelectedItem();
        }
    }
}
