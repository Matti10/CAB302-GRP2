package Gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeLoadOptions extends JFrame implements ActionListener, Runnable {
    private GUIutilities guIutilities = new GUIutilities();

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    GridLayout pageLayout = new GridLayout(1, 2);
    GridBagLayout buttonShelfLayout = new GridBagLayout();

    GridBagConstraints gbc = new GridBagConstraints();


    private void createMazeLoadScreen(){
        // Creation of Elements
        JPanel backpage = guIutilities.createPanel(Color.darkGray);
        JPanel buttonShelf = guIutilities.createPanel(Color.white);
        JPanel comboBoxShelf = guIutilities.createPanel(Color.pink );

        JButton backButton = new JButton("back");
        JButton loadButton = new JButton("Load");
        String[] displayPlaceHolder = {"This is a test lmao", "Google"};
        JComboBox mazeList = new JComboBox(displayPlaceHolder);  // this will need an array of the mazes  stored in the database to display

        //display setting for elements (not including colours which is set at creation)

        mazeList.setSize(100,100);
        mazeList.setSize(100,100);
        setResizable(false);
        backpage.setLayout(pageLayout);
        buttonShelf.setLayout(buttonShelfLayout);
        backButton.setSize(30,30);
        loadButton.setSize(30,30);
        getContentPane().add(backpage,BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonShelf.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonShelf.add(loadButton);
        comboBoxShelf.add(mazeList,BorderLayout.EAST);
        backpage.add(buttonShelf,BorderLayout.WEST);
        backpage.add(comboBoxShelf,BorderLayout.EAST);




        this.pack();
        comboBoxShelf.setSize(WIDTH / 2, HEIGHT );
        buttonShelf.setSize(WIDTH / 2, HEIGHT );
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createMazeLoadScreen();
        setDefaultLookAndFeelDecorated(true);
    }
}
