import GUI.TitleScreen;
import maze.Maze;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Maze testMaze = new Maze(3, 3, true, "testMaze"/*,new coordinate(0,0), new coordinate(9,9)*/);

/*        testMaze.randomMaze();
        coordinate[] allWalls = new coordinate[6];
        allWalls[0] = new coordinate(0, 0);
        allWalls[1] = new coordinate(0, 1);
        allWalls[2] = new coordinate(0, 2);
        allWalls[3] = new coordinate(1, 2);
        allWalls[4] = new coordinate(2, 0);
        allWalls[5] = new coordinate(2, 2);


        testMaze.edit(allWalls, new Cell(true, true, true, true));


        System.out.print(testMaze.ToString());
        //System.out.print(Maze.getFirstSolution(testMaze));
*/
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.CreateGUI();
        titleScreen.AddMaze();
    }
//    public static void main(String args[]){
//        TitleScreen gui = new TitleScreen("Main Screen");
//    }
}
