package Gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadScreen extends JFrame implements ActionListener, Runnable {
    private GUIutilities guIutilities = new GUIutilities();
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    JButton backButton = new JButton("back");
    JButton loadButton = new JButton("Load");


    public LoadScreen(){
        GridLayout pageLayout = new GridLayout(1, 2);
        GridBagLayout buttonShelfLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel display = new JLabel("Maze Name | Maze Author | Date");
        // Creation of Elements
        JPanel scrollableContainer = guIutilities.createPanel(Color.white);
        JScrollPane scrollPane = new JScrollPane(scrollableContainer);

        JPanel backpage = guIutilities.createPanel(Color.darkGray);
        JPanel buttonShelf = guIutilities.createPanel(Color.darkGray);
        JPanel eastShelf = guIutilities.createPanel(Color.darkGray );




        //display setting for elements (not including colours which is set at creation)
        display.setSize(250,50);
        scrollableContainer.add(display);
        setResizable(false);
        backpage.setLayout(pageLayout);
        buttonShelf.setLayout(buttonShelfLayout);
        backButton.setSize(30,30);
        loadButton.setSize(30,30);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(backpage,BorderLayout.CENTER);
        buttonShelf.add(backButton);
        buttonShelf.add(loadButton);
        eastShelf.add(scrollPane);
        backpage.add(buttonShelf,BorderLayout.WEST);
        backpage.add(eastShelf,BorderLayout.EAST);


        backButton.addActionListener(this);
        loadButton.addActionListener(this);

        this.pack();
        eastShelf.setSize(WIDTH / 2, HEIGHT );
        buttonShelf.setSize(WIDTH / 2, HEIGHT );
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == backButton)
        {
            setVisible(false);
            new TitleScreen();
        }
        if (event.getSource() == loadButton)
        {

        }

    }

    @Override
    public void run() {
        setDefaultLookAndFeelDecorated(true);
    }
}
