import GUI.NewMazeOptionsScreen;
import GUI.LoadScreen;
import GUI.TitleScreen;
import db.MazeListData;
import maze.*;
import java.util.*;


import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int len = 3;
        Maze testMaze = Maze.initMaze(len, len, false, 1, 0, len-1, len-1, "someName");

        List<Coordinate> noWalls = testMaze.setRandomSolution(.6);

        System.out.print(noWalls.toString());
        for (Coordinate move : testMaze.getFirstSolution()) {
           System.out.print(move.toString());
        }

        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);
       // NewMazeOptionsScreen screen = new NewMazeOptionsScreen();
        //screen.CreateNewMazeOptionsGUI();

    }
}