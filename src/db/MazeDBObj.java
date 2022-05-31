package db;

import java.io.Serializable;

public class MazeDBObj implements Comparable<MazeDBObj>, Serializable {
    private static final long serialVersionUID = -7092701502990374424L;
    private String mazeName;
    private String author;
    private String dateTimeCreated;
    private String dateTimeEdited;
    private String mazeDimensions;
    private String mazeData;
    private String mazeDataOverflow;

    public MazeDBObj() {
    }

    public MazeDBObj(String mazeName, String author, String dateTimeCreated, String dateTimeEdited, String mazeDimensions, String mazeData, String mazeDataOverflow) {
        this.mazeName = mazeName;
        this.author = author;
        this.dateTimeCreated = dateTimeCreated;
        this.dateTimeEdited = dateTimeEdited;
        this.mazeDimensions = mazeDimensions;
        this.mazeData = mazeData;
        this.mazeDataOverflow = mazeDataOverflow;
    }

    public String getMazeName() {
        return mazeName;
    }

    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public void setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
    }

    public String getDateTimeEdited() {
        return dateTimeEdited;
    }

    public void setDateTimeEdited(String dateTimeEdited) {
        this.dateTimeEdited = dateTimeEdited;
    }

    public String getMazeDimensions() {
        return mazeDimensions;
    }

    public void setMazeDimensions(String mazeDimensions) {
        this.mazeDimensions = mazeDimensions;
    }

    public String getMazeData() {
        return mazeData;
    }

    public void setMazeData(String mazeData) {
        this.mazeData = mazeData;
    }

    public String getMazeDataOverflow() {
        return mazeDataOverflow;
    }

    public void setMazeDataOverflow(String mazeDataOverflow) {
        this.mazeDataOverflow = mazeDataOverflow;
    }

    public int compareTo(MazeDBObj other) {
        return this.mazeName.compareTo(other.mazeName);
    }

    public String toString() { return mazeName + " " + author + ", " + dateTimeCreated + " " + dateTimeEdited + " " + mazeData; }
}
