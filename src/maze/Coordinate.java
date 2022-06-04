package maze;

public class Coordinate {
    public int yCoord;
    public int xCoord;


    public Coordinate(int xCoord, int yCoord)
    {
//        super(3,3,false/*, new Coordinate(0,0),new Coordinate(0,0)*/); //not sure about this...


        this.yCoord = yCoord;
        this.xCoord = xCoord;
    }

    public String toString(){
        return  "col: " + xCoord + " row: "  + yCoord +"\n";
    }
}