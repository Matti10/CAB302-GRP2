package Gui;

import javax.swing.*;
import java.awt.*;

public class GUIutilities {

    public JPanel createPanel (Color c) {
        var panel = new JPanel();
        panel.setBackground(c);

        return panel;
    }
}
