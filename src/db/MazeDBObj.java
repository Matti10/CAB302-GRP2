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
    private String isSealed;
    private String startPos;
    private String endPos;
    private String mazeData;
    private String mazeDataOverflow;

    public MazeDBObj() {}

    /**
     * Constructs a DB-formatted maze object.
     *
     * @param mazeName - the name assigned to the maze
     * @param author - the author of the maze
     * @param dateTimeCreated - auto-generated date and time the maze was created in unix-timestamp form
     * @param dateTimeEdited - auto-generated date and time the waze was last edited in unix-timestamp form
     * @param mazeDimensions - a DB-appropriate string of the maze's x and y dimensions
     * @param isSealed - a DB-appropriate boolean of the maze's sealed/unsealed status
     * @param startPos - a DB-appropriate coordinate of the maze's start position
     * @param startPos - a DB-appropriate coordinate of the maze's end position
     * @param mazeData - a DB-appropriate string representation of the cells, storing up to 8000 cells
     * @param mazeDataOverflow - a DB-appropriate string storing the excess cells of the maze, if the maze is made up of more than 8000 cells
     */
    public MazeDBObj(String mazeName, String author, String dateTimeCreated, String dateTimeEdited, String mazeDimensions, String isSealed, String startPos, String endPos, String mazeData, String mazeDataOverflow) {
        this.mazeName = mazeName;
        this.author = author;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeEdited = dateTimeEdited;
        this.mazeDimensions = mazeDimensions;
        this.isSealed = isSealed;
        this.startPos = startPos;
        this.endPos = endPos;
        this.mazeData = mazeData;
        this.mazeDataOverflow = mazeDataOverflow;
    }

    /**
     * Gets the name of the maze from the DB object.
     *
     * @return - the name of the maze
     */
    public String getMazeName() {
        return mazeName;
    }

    /**
     * Sets the name of the maze to the DB object.
     *
     * @param mazeName  - the name to be assigned to the maze
     */
    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    /**
     * Gets the author of the maze from the DB object.
     *
     * @return - the author of the maze
     */
    public String getAuthor() {
        return author;
    }

    /**
     * A method that sets the author of the maze to the DB object.
     *
     * @param author  - the author to be assigned to the maze
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the date/time that the maze was created from the DB object.
     *
     * @return - the date/time the maze was created, in unix-timestamp format
     */
    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    /**
     * Sets the date/time that the maze was created to the DB object.
     *
     * @param dateTimeCreated  - the unix-formatted date/time of creation
     */
    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    /**
     * Gets the date/time that the maze was last edited from the DB object.
     *
     * @return - the date/time the maze was last edited, in unix-timestamp format
     */
    public String getDateTimeEdited() {
        return dateTimeEdited;
    }

    /**
     * Sets the date/time that the maze was last edited to the DB object.
     *
     * @param dateTimeEdited  - the unix date/time the maze was last edited
     */
    public void setDateTimeEdited(String dateTimeEdited) {
        this.dateTimeEdited = dateTimeEdited;
    }

    /**
     * Gets the dimensions of the maze from the DB object.
     *
     * @return - the dimensions of the maze as a formatted string
     */
    public String getMazeDimensions() {
        return mazeDimensions;
    }

    /**
     * Sets the dimensions of the maze to the DB object.
     *
     * @param mazeDimensions  - the dimensions of the maze as a formatted string
     */
    public void setMazeDimensions(String mazeDimensions) {
        this.mazeDimensions = mazeDimensions;
    }

    /**
     * Gets the sealed state of the maze from the DB object.
     *
     * @return - the sealed state of the maze
     */
    public String getIsSealed() {
        return isSealed;
    }

    /**
     * Sets the sealed state of the maze to the DB object.
     *
     * @param isSealed - the sealed state of the maze
     */
    public void setIsSealed(String isSealed) {
        this.isSealed = isSealed;
    }

    /**
     * Gets the start coordinates of the maze from the DB object.
     *
     * @return - the start coordinates as a formatted string
     */
    public String getStartPos() {
        return startPos;
    }

    /**
     * Sets the start coordinates of the maze to the DB object.
     *
     * @param startPos - the start coordinates as a formatted string
     */
    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    /**
     * Gets the end coordinates of the maze from the DB object.
     *
     * @return - the end coordinates as a formatted string
     */
    public String getEndPos() {
        return endPos;
    }

    /**
     * Sets the end coordinates of the maze to the DB object.
     *
     * @param endPos - the end coordinates as a formatted string
     */
    public void setEndPos(String endPos) {
        this.endPos = endPos;
    }

    /**
     * Gets the DB-appropriate representation of the first 8000 cells of the maze from the DB object.
     *
     * @return - the first 8000 cells worth of maze data as a formatted string
     */
    public String getMazeData() {
        return mazeData;
    }

    /**
     * Sets the DB-appropriate representation of the first 8000 cells of the maze to the DB object.
     *
     * @param mazeData  - the first 8000 cells worth of maze data as a formatted string
     */
    public void setMazeData(String mazeData) {
        this.mazeData = mazeData;
    }

    /**
     * Gets the excess (all cells over 8000) DB-appropriate representation of the maze from the DB object.
     *
     * @return - the excess maze data as a formatted string
     */
    public String getMazeDataOverflow() {
        return mazeDataOverflow;
    }

    /**
     * A method that sets the excess (all cells over 8000) DB-appropriate representation of the maze to the DB object.
     *
     * @param mazeDataOverflow  - the excess maze as a formatted string
     */
    public void setMazeDataOverflow(String mazeDataOverflow) {
        this.mazeDataOverflow = mazeDataOverflow;
    }

    /**
     * Alphabetically compares the names of two mazes.
     *
     * @param other - the maze object to compare with
     * @return - an integer value specifying whether the current maze's name is greater, less than,
     *           or equal to the name of the specified comparison maze
     */
    public int compareTo(MazeDBObj other) {
        return this.mazeName.compareTo(other.mazeName);
    }
}
