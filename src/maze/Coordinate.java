package maze;

public class Coordinate {
    public int x; //x part of coord
    public int y; //y part of coord

    /**
     * Creates a new Coord
     * @param x - X part
     * @param y - Y part
     */
    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Converts coord to array of 2 ints
     * @return - Array form of Coord in the format [x,y]
     */
    public int[] toIntArray() {
        return new int[] {x,y};
    }

    /**
     * @return - Returns the coords in a readable string
     */
    public String toString(){
        return  "col (x): " + x + " row (y): "  + y +"\n";
    }
}