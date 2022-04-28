package maze;

import java.util.Random;

public class Maze {

    static Cell[][] mazeArray; //2d array of cell objects

    // maze constructor - something like:
    public Maze() {


    //fill maze object with random maze
    // todo - make random maze solveable
    static void randomWalls()
    {
        Random rand = new Random();
        //set top boundary walls to be random
//        for ()
        // set left boundary walls to be random


        // iterate over all maze cells and assign them with a random wall type
        for (int col = 1; col < mazeArray.length; col++)
        {
            for (int row = 1; row < mazeArray[col].length; row++)
            {
                Cell currentCell = mazeArray[col][row] ;

                if (col == 0)
                {
                    //if the cell is in the first column, randomly assign the wall
                    currentCell.leftWall = rand.nextBoolean();
                }
                else
                {
                    // if it's not the first column set left wall to be the leftCells right wall (as they're the same wall)
                    Cell lefCell = mazeArray[col-1][row];
                    currentCell.leftWall = lefCell.rightWall;
                }

                if (row == 0)
                {
                    //if the cell is in the first row, randomly set its bottom wall
                    currentCell.bottomWall = rand.nextBoolean();
                }
                else
                {
                    //if it's not the first row set top wall to be the aboveCells bottom wall (as they're the same wall)
                    Cell aboveCell = mazeArray[col][row-1];
                    currentCell.topWall = aboveCell.bottomWall;
                }


                do
                {
                    randomWalls(currentCell);
                } while (wallCheck(currentCell, mazeArray[col][row-1],mazeArray[col-1][row]));


            }
        }
    }

    static void randomWalls(Cell currentCell)
    {
        Random rand = new Random();

        //set right & bottom wall to be random
        currentCell.rightWall = rand.nextBoolean();
        currentCell.bottomWall = rand.nextBoolean();
    }

    //checks the walls of a cell against its surrounding cells using a set of rules
    static boolean wallCheck(Cell currentCell, Cell aboveCell, Cell leftCell)
    {
        //ensure each wall touches at least one adjacent wall

        //we should only need to test the bottom right corner of each cell, as the top left corner of a cell is the same as the bottom right of another cell.

        //if the right wall is on, check it connects with another applicable wall
        if (
            (currentCell.rightWall && aboveCell.bottomWall) ||
            (currentCell.rightWall && aboveCell.rightWall) //||
//            (currentCell.rightWall && rightCell.topWall) ||
//            (currentCell.rightWall && rightCell.bottomWall) ||
//            (currentCell.rightWall && belowCell.topWall) ||
//            (currentCell.rightWall && belowCell.rightWall)
        )
        {
            return false;
        }
        //if the top wall is on, check it connects with another applicable wall
        else if (
//                (currentCell.bottomWall && belowCell.leftWall) ||
//                (currentCell.bottomWall && belowCell.rightWall) ||
                (currentCell.bottomWall && leftCell.rightWall) ||
                (currentCell.bottomWall && leftCell.bottomWall) //||
//                (currentCell.bottomWall && rightCell.bottomWall) ||
//                (currentCell.bottomWall && rightCell.leftWall)
        )
        {
            return  false;
        }
        else
        {
            return  true;
        }



        //if the left wall is on, check it connects with another applicable wall
//        if (
//            (currentCell.leftWall && leftCell.topWall) ||
//            (currentCell.leftWall && leftCell.bottomWall) ||
//            (currentCell.leftWall && aboveCell.leftWall) ||
//            (currentCell.leftWall && aboveCell.bottomWall) ||
//            (currentCell.leftWall && belowCell.topWall) ||
//            (currentCell.leftWall &&  belowCell.topWall)
//        )
//        {
//            return  true;
//        }

    }

    static String mazeToText()
    {
        String output = "";
        // iterate over all maze cells and assign them with a random wall type
        for (int row = 0; row < mazeArray.length; row++)
        {
            for (int col = 0; col < mazeArray[row].length; col++)
            {
                if (mazeArray[col][row].leftWall)
                {
                    output += (char) 195; //add a vertical wall
                }
                if (mazeArray[col][row].topWall)
                {
                    output += (char) 196; // add a horizontal wall
                }
                else
                {
                    output += " ";
                }
            }
            output += "\n";
        }

        return output;
    }

}
