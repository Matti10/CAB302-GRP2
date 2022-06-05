
import GUI.Chunk;
import GUI.TitleScreen;
import db.MazeListData;
import maze.*;
import java.util.*;


import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        MazeListData mazeData = new MazeListData();

        int len = 10;

        Maze testMaze = Maze.initMaze(len, len, false, 1, 0, 9, 3, "someName", mazeData);

        List<Coordinate> noWalls = testMaze.setRandomSolution(0.8);

        System.out.print(noWalls.toString());
        for (Coordinate move : testMaze.getFirstSolution()) {
           System.out.print(move.toString());
        }

        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);

    }
}
