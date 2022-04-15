package maze;

public class Cell  extends  Maze
{
    boolean topWall;
    boolean bottomWall;
    boolean leftWall;
    boolean rightWall;

    public Cell(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
    }

//    public Class getCell()
//    {
//        return ;
//    }

}
