
import GUI.Chunk;
import GUI.TitleScreen;
import maze.*;

import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Maze testMaze = Maze.initMaze(3, 3, true, 0, 0, 9, 9, "someName");
        testMaze.randomMaze();


        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);


        System.out.print(testMaze.export());


    }
}
