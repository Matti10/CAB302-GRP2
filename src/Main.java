import maze.Maze;


public class Main {
    public static void main(String[] args)
    {
        Maze testMaze = new Maze(10,10,true/*,new coordinate(0,0), new coordinate(9,9)*/);

        testMaze.randomMaze();

        System.out.print(testMaze.ToString());
    }
//    public static void main(String args[]){
//        TitleScreen gui = new TitleScreen("Main Screen");
//    }
}
