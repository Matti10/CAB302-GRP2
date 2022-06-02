package db;

import java.io.Serial;
import java.io.Serializable;

public class MazeDBObj implements Comparable<MazeDBObj>, Serializable {
    @Serial
    private static final long serialVersionUID = -7092701502990374424L;
    private String mazeName;
    private String author;
    private String dateTimeCreated;
    private String dateTimeEdited;
    private String mazeDimensions;
    private String mazeData;
    private String mazeDataOverflow;

    public MazeDBObj() {}

    /**
     * A constructor for a DB-appropriate version of a maze
     *
     * @param mazeName - the name assigned to the maze
     * @param author - the author of the maze
     * @param dateTimeCreated - auto-generated date and time the maze was created in unix-timestamp form
     * @param dateTimeEdited - auto-generated date and time the waze was last edited in unix-timestamp form
     * @param mazeDimensions - a DB-appropriate string of the maze's x and y dimensions
     * @param mazeData - a DB-appropriate string representation of the cells (/chunks?), storing up to 8000 cells (/chunks?)
     * @param mazeDataOverflow - a DB-appropriate string storing the excess cells (/chunks?) of the maze, if the maze is made up of more than 8000 cells (/chunks?)
     */
    public MazeDBObj(String mazeName, String author, String dateTimeCreated, String dateTimeEdited, String mazeDimensions, String mazeData, String mazeDataOverflow) {
        this.mazeName = mazeName;
        this.author = author;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeEdited = dateTimeEdited;
        this.mazeDimensions = mazeDimensions;
        this.mazeData = mazeData;
        this.mazeDataOverflow = mazeDataOverflow;
    }

    /**
     * A method that gets the name of the maze from the DB object
     *
     * @return - the name of the maze as a string
     */
    public String getMazeName() {
        return mazeName;
    }

    /**
     * A method that sets the name of the maze to the DB object
     *
     * @param mazeName  - the name to be assigned to the maze as a string
     */
    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    /**
     * A method that gets the author of the maze from the DB object
     *
     * @return - the author of the maze as a string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * A method that sets the author of the maze to the DB object
     *
     * @param author  - the author to be assigned to the maze as a string
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * A method that gets the date/time that the maze was created from the DB object
     *
     * @return - the date/time the maze was created, in unix-timestamp format
     */
    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    /**
     * A method that sets the date/time that the maze was created to the DB object
     *
     * @param dateTimeCreated  - the unix-formatted date/time of creation as a string
     */
    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    /**
     * A method that gets the date/time that the maze was last edited from the DB object
     *
     * @return - the date/time the maze was last edited, in unix-timestamp format
     */
    public String getDateTimeEdited() {
        return dateTimeEdited;
    }

    /**
     * A method that sets the date/time that the maze was last edited to the DB object
     *
     * @param dateTimeEdited  - the unix date/time the maze was last edited as a string
     */
    public void setDateTimeEdited(String dateTimeEdited) {
        this.dateTimeEdited = dateTimeEdited;
    }

    /**
     * A method that gets the dimensions of the maze from the DB object
     *
     * @return - the dimensions of the maze in string format
     */
    public String getMazeDimensions() {
        return mazeDimensions;
    }

    /**
     * A method that sets the dimensions of the maze to the DB object
     *
     * @param mazeDimensions  - the dimensions of the maze in string format
     */
    public void setMazeDimensions(String mazeDimensions) {
        this.mazeDimensions = mazeDimensions;
    }

    /**
     * A method that gets the DB-appropriate representation of the maze from the DB object
     *
     * @return - the first 8000 cells worth of maze data in string format
     */
    public String getMazeData() {
        return mazeData;
    }

    /**
     * A method that sets the DB-appropriate representation of the first 8000 cells of the maze to the DB object
     *
     * @param mazeData  - the first 8000 cells worth of maze data in string format
     */
    public void setMazeData(String mazeData) {
        this.mazeData = mazeData;
    }

    /**
     * A method that gets the excess (all cells over 8000) DB-appropriate representation of the maze from the DB object
     *
     * @return - the excess maze data in string format
     */
    public String getMazeDataOverflow() {
        return mazeDataOverflow;
    }

    /**
     * A method that sets the excess (all cells over 8000) DB-appropriate representation of the maze to the DB object
     *
     * @param mazeDataOverflow  - the excess maze data in string format
     */
    public void setMazeDataOverflow(String mazeDataOverflow) {
        this.mazeDataOverflow = mazeDataOverflow;
    }

    /**
     * A method that alphabetically compares the names of two mazes
     *
     * @param other - the maze object to compare with
     * @return - an integer value specifying whether the current maze's name is greater, less or equal to the name of the specified comparison maze
     */
    public int compareTo(MazeDBObj other) {
        return this.mazeName.compareTo(other.mazeName);
    }

    /**
     * A method that translates the maze object (excluding the maze data) into a readable format
     *
     * @return - the maze name, author, date/time created and edited, and maze dimensions as a formatted string
     */
    public String toString() { //may have to translate timestamps from unix, depending on what this is used for. method may be unnecessary
        return (mazeName + ", " + author + ", " + dateTimeCreated + ", " + dateTimeEdited + ", " + mazeDimensions);
    }
}
