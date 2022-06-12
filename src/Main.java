import GUI.TitleScreen;
import maze.*;

public class Main {
    public static void main(String[] args) {
        int len = 3;
        Maze testMaze = Maze.initMaze(len, len, false, 1, 0, len-1, len-1, "Default maze","N/A");
        testMaze.setRandomSolution(.6);
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);
    }
}