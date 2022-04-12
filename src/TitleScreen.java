import  java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame implements  ActionListener, Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public TitleScreen(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }
}
