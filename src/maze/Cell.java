package maze;

public class Cell  extends  Maze
{
    boolean topWall;
    boolean bottomWall;
    boolean leftWall;
    boolean rightWall;
    boolean isStart;
    boolean isEnd;

    public Cell(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {
        super(100,100,false, new coordinate(0,0),new coordinate(0,0)); //not sure about this...
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.isStart = false;
        this.isEnd = false;
    }

    public Cell(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall, boolean isStart, boolean isEnd)
    {
        super(100,100,false, new coordinate(0,0),new coordinate(0,0)); //not sure about this...
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }


    public void edit(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
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
