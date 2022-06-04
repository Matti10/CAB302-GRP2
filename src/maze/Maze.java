package maze;

import java.util.*;

public class Maze {

    public static void main(String[] args) {


        Maze testMaze = initMaze(5, 5, true, 1, 0, 2, 1,"TestMaze");

        Coordinate[] allWalls = new Coordinate[6];
        allWalls[0] = new Coordinate(0, 0);
        allWalls[1] = new Coordinate(0, 1);
        allWalls[2] = new Coordinate(0, 2);
        allWalls[3] = new Coordinate(1, 2);
        allWalls[4] = new Coordinate(2, 0);
        allWalls[5] = new Coordinate(2, 2);

        testMaze.edit(allWalls, new Cell(true, true, true, true));

        System.out.print(testMaze.ToString());
        for (Coordinate coord : testMaze.getFirstSolution()) {
            System.out.print(coord.toString());
        }

    }

    Coordinate startPosition;
    Coordinate endPosition;
    int xCount;
    int yCount;
    Cell[][] mazeArray; //2d array of cell objects
    boolean isSealed;
    String name;
    ArrayList<imageLocation> images;

    public Maze() {
        //initialise maze properties
        this.xCount = 0;
        this.yCount = 0;
        this.isSealed = true;
        this.mazeArray = new Cell[xCount][yCount];
    }

    // maze constructor
    //length [1 to 100], height [1 to 100], isSealed [true or false] - calvey
    public Maze(int xCount, int yCount, boolean isSealed, String name) {

        if (xCount < 1 || yCount < 1) {
            throw new IllegalArgumentException("Length and Height must be greater than zero");
        }

        //initialise maze properties
        this.xCount = xCount;
        this.yCount = yCount;
        this.isSealed = isSealed;
        this.name = name;
        this.mazeArray = new Cell[xCount][yCount];

    }

    //start/end position column [0 to length-1], row [0 to height-1] (assuming indexed from 0, or is it from 1?)  - calvey
    //setter for the start and end location of the maze
    public void setStartEndPos(Coordinate startPosition, Coordinate endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    //initialises the gameArray
    public void initMazeArray() {
        //initialise each cell in mazeArray
        for (int x = 0; x < xCount; x++) {
            for (int y = 0; y < yCount; y++) {
                //fill mazeArray with blank cells
                mazeArray[x][y] = new Cell(false, false, false, false);
            }
        }
    }


    //add an image to the maze
    public void addImage(String name, String path, Coordinate location)
    {
        images.add(new imageLocation(path,name,location));
    }

    public void removeImage(Coordinate location)
    {
        try
        {
            //loop through all positions in list plus 1. If n + 1 is reached, error is thrown as image doesn't exist
            int i = 0;
            for (; i < images.size() + 1; i++) {
                if (images.get(i).location == location)
                {
                    break;
                }
            }

            images.remove(i);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("No image found at this location");
        }

    }

    //backend helper function to update a cell and then edit the walls of the surrounding cells to match the edited cell
    public void editGameArray(Coordinate pos, Cell newCell) {
        //set new cell
        mazeArray[pos.x][pos.y] = newCell;

        // todo - tidy this

        try {
            mazeArray[pos.x - 1][pos.y].rightWall = newCell.leftWall;
        } catch (Exception e) {
            //if there is no cell to the left, do nothing
        }
        try {
            //if there is a cell to the right, match its left wall with the current cells right wall
            mazeArray[pos.x + 1][pos.y].leftWall = newCell.rightWall;
        } catch (Exception e) {
            //if there is no cell to the right, do nothing
        }
        try {
            mazeArray[pos.x][pos.y + 1].topWall = newCell.bottomWall;
        } catch (Exception e) {
            //if there is no cell below, do nothing
        }
        try {
            mazeArray[pos.x][pos.y - 1].bottomWall = newCell.topWall;
        } catch (Exception e) {
            //if there is no cell above, do nothing
        }

    }

    public int[] getDimensions() {
        return new int[] {this.xCount, this.yCount};
    }
    //length [1 to 100], height [1 to 100], isSealed [true or false], start/end posx [0 to length-1], start/end posy [0 to height-1]
    public static Maze initMaze(int xCount, int yCount, boolean isSealed, int startPositionX, int startPositionY, int endPositionX, int endPositionY, String name) {
        //create maze object
        Maze maze = new Maze(xCount, yCount, isSealed, name);

        //set start and  end pos
        maze.setStartEndPos(maze.newCoord(startPositionX, startPositionY), maze.newCoord(endPositionX, endPositionY));

        maze.initMazeArray();

        return maze;
    }

    //fill maze object with random maze
    // todo - make random maze solvable
    public void randomMaze() {
        Random rand = new Random();

        // iterate over all maze cells and assign them with a random wall type
        for (int xCoord = 0; xCoord < xCount; xCoord++) {
            for (int yCoord = 0; yCoord < yCount; yCoord++) {
                Cell currentCell = mazeArray[xCoord][yCoord];

                if (xCoord == 0) {
                    //if the cell is in the first column, randomly assign the wall
//                    currentCell.leftWall = rand.nextBoolean();
                    currentCell.leftWall = true;
                } else {
                    // if it's not the first column set left wall to be the leftCells right wall (as they're the same wall)
                    Cell lefCell = mazeArray[xCoord - 1][yCoord];
                    currentCell.leftWall = lefCell.rightWall;
                }

                if (yCoord == 0) {
                    //if the cell is in the first row, randomly set its top wall
//                    currentCell.topWall = rand.nextBoolean();
                    currentCell.topWall = true;
                } else {
                    //if it's not the first row set top wall to be the aboveCells bottom wall (as they're the same wall)
                    Cell aboveCell = mazeArray[xCoord][yCoord - 1];
                    currentCell.topWall = aboveCell.bottomWall;
                }



                randomWalls(currentCell);

            }
        }
    }

    //randomly sets the walls of a given cell
    static Cell randomWalls(Cell currentCell) {
        Random rand = new Random();

        //set right & bottom wall to be random
        currentCell.rightWall = rand.nextBoolean();
        currentCell.bottomWall = rand.nextBoolean();

        return currentCell;
    }

    //return cell of given Coordinate
    public Cell getCell(Coordinate coord) {
        return mazeArray[coord.x][coord.y];
    }

    public Maze getMaze() {
        return this;
    }

    public Cell[][] getMazeArray() {
        return mazeArray;
    }

    //crete a new Coordinate
    Coordinate newCoord(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be greater than zero");
        }
        if (x > xCount || y > yCount) {
            throw new IllegalArgumentException("Coordinates must be within than game size");
        }

        return new Coordinate(x, y);
    }

    //outputs the maze to a string (badly)
    public String ToString() {
        String output = "\n";
        // iterate over all maze cells and assign them with a random wall type
        for (int y = 0; y < mazeArray.length; y++) {
            for (int x = 0; x < mazeArray[y].length; x++) {
                if (mazeArray[x][y].leftWall) {
                    output += '|'; //add a vertical wall
                }
                if (mazeArray[x][y].topWall) {
                    output += '_'; // add a horizontal wall
                } else {
                    output += " ";
                }
            }
            output += "\n";
        }

        return output;
    }

    //solves the maze for the first solution
    public List<Coordinate> getFirstSolution() {
        class Helper {

            boolean isSolved = false;

            public List<Coordinate> solveMaze(Coordinate pos, List<Coordinate> moves, Character previousPos) {
                moves.add(pos);

                //check if the end position of the maze has been located
                if (pos.y == endPosition.y && pos.x == endPosition.x) {
                    isSolved = true;
                    return moves; //return the list of moves taken
                } else {
                    Cell cell = getCell(pos);

                    //if a wall isn't active, explore the cell in that direction
                    if (!cell.leftWall && previousPos != 'L') {  //if there's no left wall, move left
                        return solveMaze(new Coordinate(pos.x - 1, pos.y), moves, 'R');
                    }
                    if (!cell.rightWall && previousPos != 'R') {
                        return solveMaze(new Coordinate(pos.x + 1, pos.y), moves, 'L');
                    }
                    if (!cell.bottomWall && previousPos != 'B') {
                        return solveMaze(new Coordinate(pos.x, pos.y + 1), moves, 'T');
                    }
                    if (!cell.topWall && previousPos != 'T') {
                        return solveMaze(new Coordinate(pos.x, pos.y - 1), moves, 'B');
                    }

                    //if all nodes are explored and maze isn't solved, bubble back up tree
                    if (!isSolved)
                    {
                        moves.remove(pos); //remove the current move as it doen't lead to the solution
                        return moves;
                    }

                }

                return moves;
            }
        }

        Helper helper = new Helper();

        return helper.solveMaze(startPosition, new ArrayList<Coordinate>(), 'Z');
    }

    Cell[] getBestSolution() {
        throw new UnsupportedOperationException("getBestSolution() is Not Implemented");
    }

    public void edit(Coordinate[] cellPositions, Cell newWalls) {
        for (Coordinate pos : cellPositions) {
            editGameArray(pos, newWalls);
        }
    }

    public String[] export() {
        String mazeData = "";
        String mazeDataOverflow = "";
        for (int y = 0; y<yCount; y++) {
            for (int x = 0; x<xCount; x++) {
                if (mazeData.length() < 8000)  mazeData += (CellToChar(getCell(newCoord(x,y))));
                else mazeDataOverflow += (CellToChar(getCell(newCoord(x,y))));
            }
        }

        String[] z = new String[2];
        z[0] = mazeData;
        z[1] = mazeDataOverflow;
        return z;

        /*
        StringBuilder mazeData1 = new StringBuilder();
        StringBuilder mazeData2 = new StringBuilder();
        if (mazeData.getText().length() > 10) {
            for (int i = 0; i < 10; i++) {
                mazeData1.append(mazeData.getText().charAt(i));
            }
            for (int i = 10; i < mazeData.getText().length(); i++) {
                mazeData2.append(mazeData.getText().charAt(i));
            }
        } else {
            mazeData1 = new StringBuilder(mazeData.getText());
        }
        MazeDBObj m = new MazeDBObj(mazeName.getText(), author.getText(), dateTimeCreated .getText(),
                dateTimeEdited.getText(), mazeDimensions.getText(), mazeData1.toString(), mazeData2.toString());
        data.add(m);
         */
    }

    private Character CellToChar(Cell cell) {
        boolean L = cell.leftWall;
        boolean R = cell.rightWall;
        boolean T = cell.topWall;
        boolean B = cell.bottomWall;

        ArrayList<Character> cellPossibilities = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'));
        ArrayList<Character> toRemove = new ArrayList<>();

        if (!L) Collections.addAll(toRemove,'e','g','h','j','l','m','n','p');
        else Collections.addAll(toRemove,'a','b','c','d','f','i','k','o');
        if (!R) Collections.addAll(toRemove,'c','g','i','k','l','n','o','p');
        else Collections.addAll(toRemove,'a','b','d','e','f','h','j','m');
        if (!T) Collections.addAll(toRemove,'b','f','j','k','m','n','o','p');
        else Collections.addAll(toRemove,'a','c','d','e','g','h','i','l');
        if (!B) Collections.addAll(toRemove,'d','f','h','i','l','m','o','p');
        else Collections.addAll(toRemove,'a','b','c','e','g','j','k','n');
        cellPossibilities.removeAll(toRemove);

        return cellPossibilities.get(0);
    }

    Maze importMaze(String path) //this could return void/bool (and require a blank maze to be created in the func body)
    {
        throw new UnsupportedOperationException("export is Not Implemented");
        // @Calvey - We will need to work together to implement this one :)

    }

}
