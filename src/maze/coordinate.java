package maze;

public class coordinate extends Maze {
    public int row;
    public int col;

    public coordinate (int col,int row)
    {
        super(3,3,false/*, new coordinate(0,0),new coordinate(0,0)*/); //not sure about this...

        if (row < 0 || col < 0)
        {
            throw new IllegalArgumentException("Coordinates must be greater than zero");
        }
        if (row > length || col > height)
        {
            throw new IllegalArgumentException("Coordinates must be within than game size");
        }

        this.row = row;
        this.col = col;
    }

}