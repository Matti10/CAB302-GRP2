package maze;

import db.MazeDBObj;
import db.MazeListData;

import java.time.Instant;
import java.util.*;

public class Maze {
    MazeListData mData;

    public static void main(String[] args) {


        Maze testMaze = initMaze(99, 99, true, 1, 0, 2, 1, "TestMaze", new MazeListData());

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

    public Maze(MazeListData mData) { //not sure if this breaks everything??? do we need a maze constructor with 0 parameters?
        //initialise maze properties
        this.xCount = 0;
        this.yCount = 0;
        this.isSealed = true;
        this.mazeArray = new Cell[xCount][yCount];
        this.mData = mData;
    }

    // maze constructor
    //length [1 to 100], height [1 to 100], isSealed [true or false] - calvey
    public Maze(int xCount, int yCount, boolean isSealed, String name, MazeListData mData) {
        if (xCount < 1 || yCount < 1) {
            throw new IllegalArgumentException("Length and Height must be greater than zero");
        } else if (xCount > 100 || yCount > 100) {
            throw new IllegalArgumentException("Length and Height must be less than 100");
        } else if (xCount == 100 && yCount == 100) {
            throw new IllegalArgumentException("Length and Height combined must not create over 9,999 maze cells");
        }

        //initialise maze properties
        this.xCount = xCount;
        this.yCount = yCount;
        this.isSealed = isSealed;
        this.name = name;
        this.mazeArray = new Cell[xCount][yCount];
        this.mData = mData;

    }

    //start/end position column [0 to length-1], row [0 to height-1] (assuming indexed from 0, or is it from 1?)  - calvey
    //setter for the start and end location of the maze
    public void setStartEndPos(Coordinate startPosition, Coordinate endPosition) {

        class Helper {
            //if maze is unsealed, this func is used to open the exit/entrance (so the maze isn't sealed)
            void setEntranceExitWalls(Coordinate pos) {
                if (pos.x == 0) {
                    edit(pos, new Cell(true, true, false, true));
                } else if (pos.x == xCount - 1) {
                    edit(pos, new Cell(true, true, true, false));
                } else if (pos.y == 0) {
                    edit(pos, new Cell(false, true, true, true));
                } else if (pos.y == yCount - 1) {
                    edit(pos, new Cell(true, false, true, true));
                } else {
                    throw new IllegalArgumentException("when maze is unsealed, start/end position must be on the edge of the maze");
                }
            }
        }

        //if maze isn't sealed, 'breakthrough' the outer walls
        if (!isSealed) {
            Helper helper = new Helper();
            helper.setEntranceExitWalls(startPosition);
            helper.setEntranceExitWalls(endPosition);
        }

        this.startPosition = startPosition;
        this.endPosition = endPosition;

    }

    //initialises the gameArray
    public void initMazeArray() {
        //initialise each cell in mazeArray
        for (int x = 0; x < xCount; x++) {
            for (int y = 0; y < yCount; y++) {
                //fill mazeArray with blank cells
                mazeArray[x][y] = new Cell(true, true, true, true);
            }
        }
    }


    //add an image to the maze
    public void addImage(String name, String path, Coordinate topLeftLocation,Coordinate bottomRightLoction, int size) {
        images.add(new imageLocation(path, name, topLeftLocation, bottomRightLoction, size));
    }

    public void removeImage(Coordinate location) {
        try {
            //loop through all positions in list plus 1. If n + 1 is reached, error is thrown as image doesn't exist
            int i = 0;
            for (; i < images.size() + 1; i++) {
                if (images.get(i).location == location) {
                    break;
                }
            }

            images.remove(i);
        } catch (Exception e) {
            throw new IllegalArgumentException("No image found at this location");
        }

    }

    public List<Coordinate> setRandomSolution(double complexity) {
        class Helper {
            boolean isSolved = false;
            double complexity;

            public Helper(double complexity) {
                this.complexity = complexity;
            }

            Coordinate bestMove(Coordinate pos) {
                //find which direction moves towards the exit
                int xDist = pos.x - endPosition.x;
                int yDist = pos.y - endPosition.y;

                if (Math.abs(xDist) > Math.abs(yDist)) {
                    if (xDist > 0) {
                        return newCoord(-1, 0);
                    } else {
                        return newCoord(1, 0);
                    }
                } else {
                    if (yDist > 0) {
                        return newCoord(0, -1);
                    } else {
                        return newCoord(0, 1);
                    }
                }
            }

            //returns the distance to the edge in the direction of move
            int distToEdge(Coordinate pos, Coordinate move) {
                int edgeBuffer = 2; //buffers the distance to the edge
                if (move.x > 0) {
                    return xCount - pos.x - edgeBuffer;
                }
                if (move.x < 0) {
                    return pos.x - edgeBuffer;
                }
                if (move.y > 0) {
                    return yCount - pos.y - edgeBuffer;
                }
                if (move.y < 0) {
                    return pos.y - edgeBuffer;
                }

                return 0;
            }

            //return an int between -1 and 1 inclusive, which is representive a direction in the maze
            int randomDirection() {
                return randomInt(-1, 1);
            }

            int randomInt(int min, int max) {
                return (int) Math.floor(Math.random() * (max - min + 1) + min);
            }

            Coordinate invertMove(Coordinate move)//reverse a move
            {
                int x = move.x;
                int y = move.y;

                if (x != 0) {
                    x = x * -1;
                }
                if (y != 0) {
                    y = y * -1;
                }

                return newCoord(x, y);
            }

            Coordinate randomMove(Coordinate previousMove) {
                Random rand = new Random();

                previousMove = invertMove(previousMove); //invert the move so it's equivilant to moving back to the previous cell

                Coordinate move = previousMove;
                while (move == previousMove || !(move.x == 0 ^ move.y == 0)) //while we're moving back the way we came and x or y isn't 0
                {
                    move = newCoord(randomDirection(), randomDirection());
                }

                return move;
            }

            Coordinate applyMove(Coordinate move, Coordinate pos) {
                try
                {
                    return newCoord(move.x + pos.x, move.y + pos.y);
                }catch (Exception e)
                {
                    return applyMove(randomMove(move),pos);
                }

            }

            public List<Coordinate> exploreSolution(Coordinate pos, List<Coordinate> moves, Coordinate previousMove, int directionBias, Coordinate destination) {
                moves.add(pos);

                //check if the end position of the maze has been located
                if (pos.y == destination.y && pos.x == destination.x) {
                    isSolved = true;
                    return moves; //return the list of moves taken
                } else {
                    //if not at the end, explore until we are
                    Cell cell = getCell(pos);
                    Coordinate move;

                    if (directionBias > 0) //if there is any direction bias moves left, make them
                    {
                        //explore in the same direction as the previous move
                        return exploreSolution(applyMove(previousMove, pos), moves, previousMove, directionBias - 1, destination);
                    } else {
                        //decide if moving towards the exit
                        if (complexity + Math.random() > 1) //the higher the complexity, the less likely this is to return true
                        {
                            //explore random
                            move = randomMove(previousMove);
                            directionBias = randomInt(0, distToEdge(pos, move)) / 3;
                        } else {
                            //explore towards exit
                            move = bestMove(pos);
                        }

                        //apply move and explore next node
                        Coordinate nextPos = applyMove(move, pos);
                        //check nextPos is legal if it is, move randomly until legal
                        while ((nextPos.x < 0 || nextPos.y < 0 || nextPos.x >= xCount || nextPos.y >= yCount) && !moves.contains(nextPos)) {
                            //illegal
                            nextPos = applyMove(randomMove(previousMove), pos);
                        }
                        return exploreSolution(nextPos, moves, move, directionBias, destination);
                    }


                }
            }


            public void addMovesToMaze(List<Coordinate> solution) {

                for (int i = 0; i < solution.size() - 1; i++) {
                    Coordinate curPos = solution.get(i);
                    Coordinate nextPos = solution.get(i + 1);
                    Cell curCell = getCell(curPos);
                    Cell newCell = new Cell(curCell.topWall, curCell.bottomWall, curCell.leftWall, curCell.rightWall);

                    //establish the direction of the next move
                    if (curPos.x - nextPos.x > 0) {
                        //left move - turn off left wall of current cell
                        newCell.leftWall = false;
                    }
                    if (curPos.x - nextPos.x < 0) {
                        //right move
                        newCell.rightWall = false;
                    }
                    if (curPos.y - nextPos.y < 0) {
                        //down move
                        newCell.bottomWall = false;
                    }
                    if (curPos.y - nextPos.y > 0) {
                        //UP move
                        newCell.topWall = false;
                    }

                    edit(curPos, newCell);

                }
            }

            public void addRandomPaths(int numPaths)
            {
                for (int i = 0; i < numPaths; i++)
                {
                    //random start position
                    Coordinate startPos = newCoord(randomInt(0,xCount-1),randomInt(0,yCount-1));
                    Coordinate endPos = newCoord(randomInt(0,xCount-1),randomInt(0,yCount-1));


                    addMovesToMaze(exploreSolution(startPos, new ArrayList<Coordinate>(), newCoord(0, 0), 0, endPos));
                }
            }

        }


        //data validation
        if (0 > complexity || complexity > 1) {
            throw new IllegalArgumentException("Please enter a complexity value between 0 and 1");
        }

        Helper helper = new Helper(complexity);

        //explore maze for a solution from start point
        List<Coordinate> sol = helper.exploreSolution(startPosition, new ArrayList<Coordinate>(), newCoord(0, 0), 0, endPosition);

        helper.addMovesToMaze(sol);
        helper.addRandomPaths((xCount+yCount)/20);
        return sol;


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
        return new int[]{this.xCount, this.yCount};
    }

    //length [1 to 100], height [1 to 100], isSealed [true or false], start/end posx [0 to length-1], start/end posy [0 to height-1]
    public static Maze initMaze(int xCount, int yCount, boolean isSealed, int startPositionX, int startPositionY, int endPositionX, int endPositionY, String name, MazeListData data) {
        //create maze object
        Maze maze = new Maze(xCount, yCount, isSealed, name, data);

        maze.initMazeArray();

        //set start and  end pos
        maze.setStartEndPos(maze.newCoord(startPositionX, startPositionY), maze.newCoord(endPositionX, endPositionY));


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
        return mazeArray[coord.x][coord.y].getCell();
    }

    public Maze getMaze() {
        return this;
    }

    public Cell[][] getMazeArray() {
        return mazeArray;
    }

    //crete a new Coordinate
    Coordinate newCoord(int x, int y) {
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
                    if (!isSolved) {
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
            editGameArray(pos, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
        }
    }

    public void edit(Coordinate cellPositions, Cell newWalls) {
        editGameArray(cellPositions, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
    }

    public void edit(List<Coordinate> cellPositions, Cell newWalls) {
        for (Coordinate pos : cellPositions) {
            editGameArray(pos, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
        }
    }

    public Boolean hasImage()
    {
        if(images.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void analyse()
    {
        //find number cells in solution
        List<Coordinate> sol = getFirstSolution();
        int countSolCells = sol.size();

        //find number of dead ends
        int countDeadEnd = 0;

        for (int i=0; i < xCount; i++)
        {
            for (int j=0; j < yCount; j++)
            {
                Cell cell = getCell(newCoord(i,j));

                if (!(cell.bottomWall && cell.leftWall && cell.rightWall && cell.topWall))
                {
                    countDeadEnd++;
                }
            }

        }
    }

    /**
     * Exports the maze to the DB.
     *
     * @param mazeName - the name of the maze
     * @param author - the author of the maze
     * @param creationTime - the maze's unix-formatted date/time of creation
     */
    public void exportMaze(String mazeName, String author, String creationTime) {
        //MUST confirm overwrite if mazeName already exists in mData. data will be overwritten if mazeNames match.


        String dateTimeCreated;
        long currentUnixTime = Instant.now().getEpochSecond();

        if (creationTime == null) dateTimeCreated = String.valueOf(currentUnixTime);
        else dateTimeCreated = creationTime;

        String dateTimeEdited = String.valueOf(currentUnixTime);
        String mazeDimensions = xCount + "x" + yCount;

        int[] startPosArr = startPosition.toIntArray();
        int[] endPosArr = endPosition.toIntArray();
        String startPos = startPosArr[0] + "," + startPosArr[1];
        String endPos = endPosArr[0] + "," + endPosArr[1];

        String mazeData = "";
        String mazeDataOverflow = "";
        for (int y = 0; y < yCount; y++) {
            for (int x = 0; x < xCount; x++) {
                if (mazeData.length() < 8000) mazeData += (CellToChar(getCell(newCoord(x, y))));
                else mazeDataOverflow += (CellToChar(getCell(newCoord(x, y))));
            }
        }
        mData.add(new MazeDBObj(mazeName, author, dateTimeCreated, dateTimeEdited, mazeDimensions, String.valueOf(sealedVal), startPos, endPos, mazeData, mazeDataOverflow));
    }

    /**
     * Imports the maze from the DB.
     *
     * @param mazeName - the name of the maze to be imported
     * @return - the current maze object, modified to meet the specifications
     *           of the imported maze
     */
    public Maze importMaze(String mazeName) {
        MazeDBObj m = mData.get(mazeName);

        String[] arrDims = m.getMazeDimensions().split("x");
        this.xCount = Integer.parseInt(arrDims[0]);
        this.yCount = Integer.parseInt(arrDims[1]);

        this.isSealed = Boolean.parseBoolean(m.getIsSealed());

        String[] start = m.getStartPos().split(",");
        this.startPosition = new Coordinate(Integer.parseInt(start[0]),Integer.parseInt(start[1]));

        String[] end = m.getEndPos().split(",");
        this.endPosition = new Coordinate(Integer.parseInt(end[0]),Integer.parseInt(end[1]));

        //Maze maze = Maze.initMaze(xCnt, yCnt, seal, xStart, yStart, xEnd, yEnd, m.getMazeName(), mData); //I think this line needs to be delete @Calvey (from my merge fuckup)

        //For below, wanting to edit an individual cell at a specific coordinate. how to implement?
        String mazeData = m.getMazeData() + m.getMazeDataOverflow();
        Coordinate thisCoord;
        Cell thisCell;
        for (int i = 0; i < mazeData.length(); i++) {
            int thisY = i/xCount;
            int thisX = i-(thisY*yCount);
            thisCoord = new Coordinate(thisX,thisY);
            thisCell = CharToCell(mazeData.charAt(i));
            this.edit(thisCoord, thisCell);
        }
        return this;
    }

    /**
     * Converts a Cell object to its Character-pairing to minimise the size of
     * data being stored in the DB. The specific pairings between cells and
     * characters are kept consistent throughout.
     *
     * @param cell - the Cell object to be converted
     * @return - the Cell object's paired character
     */
    private Character CellToChar(Cell cell) {
        ArrayList<Character> cellPossibilities = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'));
        ArrayList<Character> toRemove = new ArrayList<>();

        if (!cell.leftWall) Collections.addAll(toRemove, 'e', 'g', 'h', 'j', 'l', 'm', 'n', 'p');
        else Collections.addAll(toRemove, 'a', 'b', 'c', 'd', 'f', 'i', 'k', 'o');
        if (!cell.rightWall) Collections.addAll(toRemove, 'c', 'g', 'i', 'k', 'l', 'n', 'o', 'p');
        else Collections.addAll(toRemove, 'a', 'b', 'd', 'e', 'f', 'h', 'j', 'm');
        if (!cell.topWall) Collections.addAll(toRemove, 'b', 'f', 'j', 'k', 'm', 'n', 'o', 'p');
        else Collections.addAll(toRemove, 'a', 'c', 'd', 'e', 'g', 'h', 'i', 'l');
        if (!cell.bottomWall) Collections.addAll(toRemove, 'd', 'f', 'h', 'i', 'l', 'm', 'o', 'p');
        else Collections.addAll(toRemove, 'a', 'b', 'c', 'e', 'g', 'j', 'k', 'n');
        cellPossibilities.removeAll(toRemove);

        return cellPossibilities.get(0);
    }

    /**
     * Converts a Character Cell-pairing to minimise the size of data being
     * stored in the DB. The specific pairings between cells and characters
     * are kept consistent throughout.
     *
     * @param c - the Character to be converted
     * @return - the Character's paired Cell object
     */
    private Cell CharToCell(Character c) {
        ArrayList<Character> LWallChars = new ArrayList<>(Arrays.asList('e', 'g', 'h', 'j', 'l', 'm', 'n', 'p'));
        ArrayList<Character> RWallChars = new ArrayList<>(Arrays.asList('c', 'g', 'i', 'k', 'l', 'n', 'o', 'p'));
        ArrayList<Character> TWallChars = new ArrayList<>(Arrays.asList('b', 'f', 'j', 'k', 'm', 'n', 'o', 'p'));
        ArrayList<Character> BWallChars = new ArrayList<>(Arrays.asList('d', 'f', 'h', 'i', 'l', 'm', 'o', 'p'));

        return new Cell(TWallChars.contains(c), BWallChars.contains(c), LWallChars.contains(c), RWallChars.contains(c));
    }
}
