package maze;

public class Coordinate {
    public int y;
    public int x;


    public Coordinate(int x, int y)
    {
//        super(3,3,false/*, new Coordinate(0,0),new Coordinate(0,0)*/); //not sure about this...


        this.x = x;
        this.y = y;
    }

    public String toString(){
        return  "col (x): " + x + " row (y): "  + y +"\n";
    }
}