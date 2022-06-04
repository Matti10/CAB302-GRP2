package maze;

public class Coordinate {
    public int row;
    public int col;


    public Coordinate(int col, int row)
    {
//        super(3,3,false/*, new Coordinate(0,0),new Coordinate(0,0)*/); //not sure about this...


        this.row = row;
        this.col = col;
    }

    public String toString(){
        return  "col: " + col + " row: "  + row +"\n";
    }
}