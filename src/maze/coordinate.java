package maze;

public class coordinate {
    public int row;
    public int col;


    public coordinate (int col,int row)
    {
//        super(3,3,false/*, new coordinate(0,0),new coordinate(0,0)*/); //not sure about this...


        this.row = row;
        this.col = col;
    }

    public String toString(){
        return  "col: " + col + " row: "  + row +"\n";
    }
}