package maze;

import db.MazeDBObj;
import db.MazeListData;

import java.time.Instant;
import java.util.*;

public class Maze {
    MazeListData mData;
    Coordinate startPosition; //start position of the maze
    Coordinate endPosition; //end position of the maze
    int xCount; //width
    int yCount; //height
    Cell[][] mazeArray; //2d array of cell objects
    boolean isSealed;
    String name;
    ArrayList<imageLocation> images; //list of images in maze

    /**
     * Constructs the maze object using default values
     *
     * @param mData - Maze Data object
     */
    public Maze(MazeListData mData) {
        this.xCount = 0;
        this.yCount = 0;
        this.isSealed = true;
        this.mazeArray = new Cell[xCount][yCount];
        this.mData = mData;
        this.images = new ArrayList<imageLocation>();
    }

    /**
     * Constructs the maze object
     *
     *
     * @param xCount - Width of maze
     * @param yCount - Height of maze
     * @param isSealed - Boolean, true if maze is sealed, else false
     * @param name - Name of the maze
     * @param mData - Maze Data object
     */

    //data validation
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
        this.images = new ArrayList<imageLocation>();


    }


    /**
     * setter for the start and end location of the maze
     *
     * @param startPosition - Start/Entry coord of the maze
     * @param  endPosition - End/Exit coord of the maze
     */
    public void setStartEndPos(Coordinate startPosition, Coordinate endPosition) {

        class Helper {
            /**
             * if maze is unsealed, this func is used to open the exit/entrance (so the maze isn't sealed)
             *
             * @param  pos - A start/end postion in the maze
             */
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

        //set start/end pos
        this.startPosition = startPosition;
        this.endPosition = endPosition;

    }

    /**
     * initialises the gameArray
     */
    public void initMazeArray() {
        //initialise each cell in mazeArray
        for (int x = 0; x < xCount; x++) {
            for (int y = 0; y < yCount; y++) {
                //fill mazeArray with blank cells
                mazeArray[x][y] = new Cell(true, true, true, true);
            }
        }
    }


    //
    /**
     * add's an image to the maze
     *
     * @param name - Name of the image
     * @param path - file path to the image
     * @param topLeftLocation - Coordinate of the top left corner of the image
     * @param bottomRightLocation - Coordinate of the bottom right corner of the image
     * @param size - Size ofthe image
     * @param isAccessible - Is the image accessible in the maze (true if yes else false)
     */
    public void addImage(String name, String path, Coordinate topLeftLocation, Coordinate bottomRightLocation, int size, boolean isAccessible) {
        images.add(new imageLocation(path, name, topLeftLocation, bottomRightLocation, size, isAccessible));

        setImageAccesibility();

    }

    /**
     * Helper function that sets the accessibilty of the cells under all maze images
     * it does this by either turning all the walls on or off
     */

    void setImageAccesibility() {
        for (imageLocation image : images) {

            imageLocation imageObject = image.getImage();

            Cell inaccessibleCell = new Cell(true, true, true, true);
            Cell accessibleCell = new Cell(false, false, false, false);


            for (int x = imageObject.topLeftLocation.x; x <= imageObject.bottomRightLocation.x; x++) {
                for (int y = imageObject.topLeftLocation.y; y <= imageObject.bottomRightLocation.y; y++) {
                    if (imageObject.isAccesible) {
                        //if image is accesible, set all cells 'under' the image to have all walls turned off
                        edit(newCoord(x, y), accessibleCell);
                    } else {
                        //if image isn't accesible, set all cells 'under' the image to have all walls turned on
                        edit(newCoord(x, y), inaccessibleCell);
                    }
                }
            }

        }
    }

    /**
     * Removes an image at a given coord from the maze
     *
     * @param location - The location of the top left cell which the image occupies
     */
    public void removeImage(Coordinate location) {
        try {
            //loop through all positions in list plus 1. If n + 1 is reached, error is thrown as image doesn't exist
            int i = 0;
            for (; i < images.size() + 1; i++) {
                if (images.get(i).topLeftLocation == location) {
                    break;
                }
            }

            images.remove(i);
        } catch (Exception e) {
            throw new IllegalArgumentException("No image found at this location - ensure you're referenceing by the images top right coordinate");
        }

    }

    /**
     * Removes an image with a given name from the maze
     *
     * @param name - The of the image to be removed
     */
    public void removeImage(String name) {
        try {
            //loop through all positions in list plus 1. If n + 1 is reached, error is thrown as image doesn't exist
            int i = 0;
            for (; i < images.size() + 1; i++) {
                if (images.get(i).name == name) {
                    break;
                }
            }

            images.remove(i);
        } catch (Exception e) {
            throw new IllegalArgumentException("No image found with this name");
        }

    }

    /**
     * Sets maze to a random solveable maze
     * @param complexity - The complexity of the random maze - 0 is easiest, 1 is hardest
     * @return - The solution to the random maze
     */
    public List<Coordinate> setRandomSolution(double complexity) {
        class Helper {
            boolean isSolved; //flag for if maze is sloved
            double complexity; //complexity of solution

            /**
             * Constructor of helper object
             * @param complexity - complexity of solution
             */
            public Helper(double complexity) {
                this.complexity = complexity;
                this.isSolved = false;
            }

            /**
             *
             * @param pos - current position in the maze array
             * @param dest - the destination to move too
             * @return a coordinate representative of the best move
             */
            Coordinate bestMove(Coordinate pos, Coordinate dest) {
                //find which direction moves towards the exit
                int xDist = pos.x - dest.x;
                int yDist = pos.y - dest.y;

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



            /**
             * Helpter function to find the distance to the edge of the array from a given position
             * @param pos - current position in the maze array
             * @param move - a move with some direction
             * @return returns the distance to the edge in the direction of move
             */
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

            /**
             * @return return an int between -1 and 1 inclusive, which is representive a direction in the maze
             */
            int randomDirection() {
                return randomInt(-1, 1);
            }

            /**
             * Generates a random int
             * @param min - Minimum value of random int
             * @param max - Max value of random int
             * @return - a random int between min and max inclusive
             */
            int randomInt(int min, int max) {
                return (int) Math.floor(Math.random() * (max - min + 1) + min);
            }

            /**
             * Inverts a move (i.e. (1,0) -> (-1,0)
             * @param move - some move
             * @return a move that's the opposite of move
             */
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

            /**
             * Retruns a random move in any direction other than the previousMove
             * @param previousMove - the move previously made
             * @return a valid random move
             */
            Coordinate randomMove(Coordinate previousMove) {

                previousMove = invertMove(previousMove); //invert the move so it's equivilant to moving back to the previous cell

                Coordinate move = previousMove;
                while (move == previousMove || !(move.x == 0 ^ move.y == 0)) //while we're moving back the way we came and x or y isn't 0
                {
                    move = newCoord(randomDirection(), randomDirection());
                }

                return move;
            }

            /**
             * Check if a move is valid, if it is, apply it, if it isn't attempt random moves until a valid move is found
             * @param move - the move to make
             * @param pos - the current postion in the maze array
             * @param previousMove - the previous move
             * @param moves - all moves made so far
             * @return
             */
            Coordinate applyMove(Coordinate move, Coordinate pos, Coordinate previousMove, List<Coordinate> moves) {
                try {
                    Coordinate newPos = newCoord(move.x + pos.x, move.y + pos.y);
                    if (isAccesible(newPos) && newPos.x >= 0 && newPos.y >= 0 && !moves.contains(newPos)) {
                        return newPos;
                    } else {
                        return applyMove(randomMove(previousMove), pos, previousMove, moves);
                    }

                } catch (Exception e) {
                    return applyMove(randomMove(previousMove), pos, previousMove, moves);
                }

            }

            /**
             * Check if a cell is accessible (i.e. if there is an inaccessible image in it)
             * @param pos - the cell to check
             * @return True if accessible, else false
             */
            boolean isAccesible(Coordinate pos) {
                //check the position has an inaccesible image
                for (imageLocation i : images) {
                    imageLocation image = i.getImage();
                    if (
                            !image.isAccesible &&
                                    pos.x > image.topLeftLocation.x &&
                                    pos.x < image.bottomRightLocation.x &&
                                    pos.y > image.topLeftLocation.y &&
                                    pos.y < image.bottomRightLocation.y
                    ) {
                        return false;
                    }
                }
                return true;

            }

            /**
             * Recursively search the game array from a given start position to a given end position
             * Complexity of the path taken increases with this.complexity
             * @param pos - the current position
             * @param moves - all positions traveled through
             * @param previousMove - the previously made move
             * @param directionBias - integer that if is >0 will explore in the same direction as the previous move
             * @param destination - destination positon
             * @return List of moves(positions/Coords) used to reach destination
             */
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
                        return exploreSolution(applyMove(previousMove, pos, previousMove, moves), moves, previousMove, directionBias - 1, destination);
                    } else {
                        //decide if moving towards the exit
                        if (complexity + Math.random() > 1) //the higher the complexity, the less likely this is to return true
                        {
                            //explore random
                            move = randomMove(previousMove);
                            directionBias = randomInt(0, distToEdge(pos, move)) / 3;
                        } else {
                            //explore towards exit
                            move = bestMove(pos, destination);
                        }

                        //apply move and explore next node
                        Coordinate nextPos = applyMove(move, pos, previousMove, moves);

                        return exploreSolution(nextPos, moves, move, directionBias, destination);
                    }


                }
            }

            /**
             * Adds a list of moves to the maze by setting the walls of the maze to match the list of moves
             * @param solution - a list of moves to add to maze
             */
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

            /**
             * Recursively explores blank cells (cells with no walls) until it find a cell with one or more walls set
             * @param pos - current position
             * @param moves - list of all moves
             * @param previousMove - the previously made move
             * @return List of Coords taken to reach solution
             */
            List<Coordinate> exploreBlankCells(Coordinate pos, List<Coordinate> moves, Coordinate previousMove) {
                moves.add(pos);

                if (getCell(pos).isEmpty()) {
                    Coordinate move = randomMove(previousMove);
                    Coordinate nextPos = applyMove(move, pos, previousMove, moves);

                    return exploreBlankCells(nextPos, moves, move);
                } else {
                    //when cell with wall is found, return moves
                    return moves;
                }
            }

            /**
             * adds random (non-solution) paths to the maze
             */
            void addRandomPaths() {
                for (int x = 0; x < xCount; x++) {
                    for (int y = yCount - 1; y >= 0; y--) {
                        Coordinate pos = newCoord(x, y);
                        if (getCell(pos).isEmpty()) {
                            addMovesToMaze(exploreBlankCells(pos, new ArrayList<>(), newCoord(0, 0)));
                        }
                    }
                }
            }

            /**
             * Removes clumps of 'loops' (a 1x1 square of paths) in the maze
             */
            public void fixClumping() {
                for (int x = 0; x < xCount - 1; x++) {
                    for (int y = 0; y < yCount - 1; y++) {

                        Coordinate pos = newCoord(x, y);
                        Cell curCell = getCell(pos); //current cell
                        Cell diagCell = getCell(newCoord(x + 1, y + 1)); //cell in the bottom right corner of a 1x1 grid starting @ curCEll

                        //detect if the cells form a "loop"
                        if (
                                !curCell.rightWall &&
                                        !curCell.bottomWall &&
                                        !diagCell.topWall &&
                                        !diagCell.leftWall
                        ) {
                            //there's a loop
                            //randomly turn one of the walls thats creating the loop on
                            int i = randomInt(0, 1);
                            if (i > 0)
                            {
                                curCell.bottomWall = true;
                            }
                            else
                            {
                                curCell.rightWall = true;
                            }
                            edit(pos, curCell);
                        }
                    }
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

        helper.addMovesToMaze(sol); //add solution to the maze
        helper.addRandomPaths(); //add random paths
        helper.fixClumping(); //fix loops
        helper.addMovesToMaze(sol); //re-add solution to maze in case part of it was removed when clumping was fixed

        return sol;


    }


    //backend helper function to update a cell and then edit the walls of the surrounding cells to match the edited cell

    /**
     * Updates game array after changing cell walls.
     * It also changes the walls of surrounding cells to match that of their adjacent cell
     * @param pos - Coordinate of the cell to change
     * @param newCell - the cell to set said Coord too
     */
    public void editGameArray(Coordinate pos, Cell newCell) {
        //set new cell
        mazeArray[pos.x][pos.y] = newCell;

        //set walls of adjacent cells to match newCell
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

    /**
     *  @return The dimensions of the maze
     */
    public int[] getDimensions() {
        return new int[]{this.xCount, this.yCount};
    }

    /**
     * Initialises the maze object
     * @param xCount - Width of the maze
     * @param yCount - height of maze
     * @param isSealed - Is maze sealed
     * @param startPositionX - X part of entry of maze
     * @param startPositionY - Y part entry of maze
     * @param endPositionX - X part of exit of maze
     * @param endPositionY - Y part of maze exit
     * @param name - Maze name
     * @param data - Maze Data (for use with DB)
     * @return The newly initiated maze object
     */
    public static Maze initMaze(int xCount, int yCount, boolean isSealed, int startPositionX, int startPositionY, int endPositionX, int endPositionY, String name, MazeListData data) {
        //create maze object
        Maze maze = new Maze(xCount, yCount, isSealed, name, data);

        maze.initMazeArray();

        //set start and  end pos
        maze.setStartEndPos(maze.newCoord(startPositionX, startPositionY), maze.newCoord(endPositionX, endPositionY));


        return maze;
    }

    /**
     * @param coord - position of cell to get
     * @return Cell of given Coordinate
     */
    public Cell getCell(Coordinate coord) {
        return mazeArray[coord.x][coord.y].getCell();
    }

    /**
     *
     * @return The current maze object
     */
    public Maze getMaze() {
        return this;
    }

    /**
     * @return The current game array
     */
    public Cell[][] getMazeArray() {
        return mazeArray;
    }

    /**
     * Creates a new Coord
     * @param x - X part
     * @param y - Y part
     * @return - a new Coordinate object
     */
    Coordinate newCoord(int x, int y) {
        if (x >= xCount || y >= yCount) {
            throw new IllegalArgumentException("Coordinates must be within than game size");
        }

        return new Coordinate(x, y);
    }


    /**
     *  outputs the maze to a string (badly)
     * @return The maze in a string format
     */
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

    /**
     * solves the maze for the first solution
     * @return A list of coordinates representing the path of the solution
     */

    public List<Coordinate> getFirstSolution() {
        class Helper {

            boolean isSolved = false; //flag for if the maze is solved

            /**
             * recusively expore the maze towards the solution
             * @param pos - Current postion
             * @param moves - All moves made
             * @param previousPos - previous position
             * @return - All positions needed to solve the maze
             */
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


    /**
     * Edit an array of cells
     * @param cellPositions - Array of cell positions
     * @param newWalls - The cell to set these positions too
     */
    public void edit(Coordinate[] cellPositions, Cell newWalls) {
        for (Coordinate pos : cellPositions) {
            editGameArray(pos, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
        }
    }
    /**
     * Edit a cell
     * @param cellPositions - position of cell
     * @param newWalls - The cell to set this position too
     */
    public void edit(Coordinate cellPositions, Cell newWalls) {
        editGameArray(cellPositions, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
    }
    /**
     * Edit a list of cells
     * @param cellPositions - List of cell positions
     * @param newWalls - The cell to set these positions too
     */
    public void edit(List<Coordinate> cellPositions, Cell newWalls) {
        for (Coordinate pos : cellPositions) {
            editGameArray(pos, new Cell(newWalls.topWall, newWalls.bottomWall, newWalls.leftWall, newWalls.rightWall));
        }
    }

    /**
     * Checks if maze contains alteast one image
     * @return - True if there is an image, otherwise false
     */
    public Boolean hasImage() {
        if (images.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Performs an analysis on the maze
     * @return A string containing the results of the analysis
     */
    public String analyse() {
        //find number cells in solution
        List<Coordinate> sol = getFirstSolution();
        int countSolCells = sol.size();


        //find number of cells not in solution
        int countDeadEnd = 0;

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                Cell cell = getCell(newCoord(i, j));

                if (!cell.bottomWall || !cell.leftWall || !cell.rightWall || !cell.topWall) {
                    countDeadEnd++;
                }
            }

        }
        return "Solution travel through " + (countSolCells / (xCount * yCount)) + "% of the maze. " + ((countDeadEnd - countSolCells) / (xCount * yCount)) + "% of cells lead to a dead end";
    }

    /**
     * Exports the maze to the DB.
     *
     * @param mazeName - the name of the maze
     * @param author - the author of the maze
     * @param creationTime - the maze's unix-formatted date/time of creation
     */
    /**
     * Exports the maze to the DB.
     *
     * @param mazeName     - the name of the maze
     * @param author       - the author of the maze
     * @param creationTime - the maze's unix-formatted date/time of creation
     */
    public void exportMaze(String mazeName, String author, String creationTime) {
        //MUST confirm overwrite if mazeName already exists in mData. data will be overwritten if mazeNames match.
        String dateTimeEdited = String.valueOf(Instant.now().getEpochSecond());
        String dateTimeCreated = creationTime;
        if (Objects.equals(dateTimeCreated, "")) dateTimeCreated = dateTimeEdited;

        int[] startPosArr = startPosition.toIntArray();
        int[] endPosArr = endPosition.toIntArray();
        String startPos = startPosArr[0] + "," + startPosArr[1];
        String endPos = endPosArr[0] + "," + endPosArr[1];

        String mazeDimensions = xCount + "x" + yCount;
        String sealedState = String.valueOf(isSealed ? 1 : 0);

        String mazeData = "";
        String mazeDataOverflow = "";
        for (int y = 0; y < yCount; y++) {
            for (int x = 0; x < xCount; x++) {
                if (mazeData.length() < 8000) mazeData += (CellToChar(getCell(newCoord(x, y))));
                else mazeDataOverflow += (CellToChar(getCell(newCoord(x, y))));
            }
        }
        mData.add(new MazeDBObj(mazeName, author, dateTimeCreated, dateTimeEdited, mazeDimensions, sealedState, startPos, endPos, mazeData, mazeDataOverflow));
    }

    /**
     * Imports the maze from the DB.
     *
     * @param mazeName - the name of the maze to be imported
     * @return - the current maze object, modified to meet the specifications
     * of the imported maze
     */
    public Maze importMaze(String mazeName) {
        MazeDBObj m = mData.get(mazeName);

        String[] arrDims = m.getMazeDimensions().split("x");
        this.xCount = Integer.parseInt(arrDims[0]);
        this.yCount = Integer.parseInt(arrDims[1]);

        this.isSealed = Boolean.parseBoolean(m.getIsSealed());

        String[] start = m.getStartPos().split(",");
        String[] end = m.getEndPos().split(",");
        this.startPosition = newCoord(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
        this.endPosition = newCoord(Integer.parseInt(end[0]), Integer.parseInt(end[1]));

        String mazeData = m.getMazeData() + m.getMazeDataOverflow();
        Coordinate thisCoord;
        Cell thisCell;
        for (int i = 0; i < mazeData.length(); i++) {
            int thisY = i / xCount;
            int thisX = i - (thisY * yCount);
            thisCoord = newCoord(thisX, thisY);
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
