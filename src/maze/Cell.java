package maze;

public class Cell
{
    boolean topWall;
    boolean bottomWall;
    boolean leftWall;
    boolean rightWall;


    public Cell()
    {
        this.topWall = false;
        this.bottomWall = false;
        this.leftWall = false;
        this.rightWall = false;

    }
    public Cell(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;

    }

    public void edit(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {

        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
    }

    public void editWallUsingArray(boolean[] wallArray)
    {
        this.edit(wallArray[0], wallArray[1], wallArray[2], wallArray[3]);
    }


    public boolean[] toWallList(){
        return new boolean[]{bottomWall,leftWall,rightWall,topWall};
    }
    
    public Cell getCell(){
        return this;   
    }

    public boolean isEmpty()
    {
        if (topWall && bottomWall && leftWall && rightWall)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
}
