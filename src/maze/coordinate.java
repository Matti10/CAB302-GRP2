package maze;

public class coordinate extends  Maze {
    public int row;
    public int col;

    public coordinate (int row ,int col)
    {
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