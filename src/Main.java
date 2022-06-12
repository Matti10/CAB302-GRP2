import GUI.NewMazeOptionsScreen;
import GUI.TitleScreen;
import db.MazeListData;
import maze.*;
import java.util.*;


import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        MazeListData mazeData = new MazeListData();
        // to get information on all mazes currently saved, call mazeData.getDisplayData().
        // returns a String[][], data selectable via String[i][j] where i indexes through
        // the mazes and j indexes through that maze's info (all strings):
        // [i][0] = maze name
        // [i][1] = author
        // [i][2] = maze dimensions
        // [i][3] = date/time created (in unix, will need to convert)
        // [i][4] = date/time edited (in unix, will need to convert)
        // lmk if any other info should be returned also. ordered by date edited, probably.

        int len = 5;

        Maze testMaze = Maze.initMaze(len, len, false, 1, 0, len-1, len-1, "someName", mazeData);

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