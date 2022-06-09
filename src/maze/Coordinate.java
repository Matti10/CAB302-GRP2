package maze;

public class Coordinate {
    public int x;
    public int y;


    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int[] toIntArray() {
        return new int[] {x,y};
    }

    public String toString(){
        return  "col (x): " + x + " row (y): "  + y +"\n";
    }
}