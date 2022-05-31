package db;

import javax.swing.SwingUtilities;

public class MazeList {
    private static void createAndShowGUI() {
        new MazeDBUI(new MazeListData());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
