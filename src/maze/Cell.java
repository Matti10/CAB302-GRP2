package maze;

public class Cell
{
    //wall definitions
    boolean topWall;
    boolean bottomWall;
    boolean leftWall;
    boolean rightWall;

    /**
     * Default constructor - All walls off
     */
    public Cell()
    {
        this.topWall = false;
        this.bottomWall = false;
        this.leftWall = false;
        this.rightWall = false;

    }

    /**
     * Creates a cell, set walls to true to turn on false to turn off
     * @param topWall
     * @param bottomWall
     * @param leftWall
     * @param rightWall
     */
    public Cell(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;

    }

    /**
     * Edit this cell
     * set walls to true to turn on false to turn off
     * @param topWall
     * @param bottomWall
     * @param leftWall
     * @param rightWall
     */
    public void edit(boolean topWall, boolean bottomWall, boolean leftWall, boolean rightWall)
    {

        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
    }

    /**
     * Pass an array of walls in to edit a cell
     * This is a cheat way of implementing an Iterable :)
     * @param wallArray - Array of 'walls' (bools) in for form of [topWall, bottomWall, leftWall, rightWall]
     */
    public void editWallUsingArray(boolean[] wallArray)
    {
        this.edit(wallArray[0], wallArray[1], wallArray[2], wallArray[3]);
    }

    /**
     * @return The walls of the cell in an array formatted as: [bottomWall,leftWall,rightWall,topWall]
     */
    public boolean[] toWallList(){
        return new boolean[]{bottomWall,leftWall,rightWall,topWall};
    }

    /**
     *
     * @return the current cell
     */
    public Cell getCell(){
        return this;   
    }

    /**
     * Cell is said to be empty if all walls are turned off
     * @return True if no walls are on, else false
     */
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
