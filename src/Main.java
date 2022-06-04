
import GUI.Chunk;
import GUI.TitleScreen;
import maze.*;

import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Maze testMaze = Maze.initMaze(11, 11, true, 0, 0, 9, 9, "someName");
        testMaze.randomMaze();


        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);

    }
}
