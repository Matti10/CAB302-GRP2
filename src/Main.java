
import GUI.Chunk;
import GUI.TitleScreen;
import maze.*;

import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Maze testMaze = new Maze(10, 10, true/*,new coordinate(0,0), new coordinate(9,9)*/);
        testMaze.initMazeArray();



        /*
       testMaze.randomMaze();
        Coordinate[] allWalls = new Coordinate[6];
        allWalls[0] = new Coordinate(0, 0);
        allWalls[1] = new Coordinate(0, 1);
        allWalls[2] = new Coordinate(0, 2);
        allWalls[3] = new Coordinate(1, 2);
        allWalls[4] = new Coordinate(2, 0);
        allWalls[5] = new Coordinate(2, 2);


        testMaze.edit(allWalls, new Cell(true, true, true, true));


        System.out.print(testMaze.ToString());
        */
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze(testMaze);


        System.out.print(testMaze.export());


    }
//    public static void main(String args[]){
//        TitleScreen gui = new TitleScreen("Main Screen");
//    }
}
