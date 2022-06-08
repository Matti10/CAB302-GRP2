package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class JDBCMazeListDataSource implements MazeListDataSource {
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS mazes ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                    + "mazeName VARCHAR(41)," //will need to check this to make sure name input not too long
                    + "author VARCHAR(41)," //will need to check this to make sure author input not too long
                    + "dateCreated INT(11)," //check length=10, shouldn't ever trigger using unix timestamps
                    + "dateEdited INT(11)," //check length=10, shouldn't ever trigger using unix timestamps
                    + "mazeDimensions VARCHAR(8)," //as long as valid maze dims, will work (<=100x100)
                    + "isSealed TINYINT(1),"
                    + "startPos VARCHAR(8),"
                    + "endPos VARCHAR(8),"
                    + "mazeData VARCHAR(8000)," //make sure this works, should go up to 7999
                    + "mazeDataOverflow VARCHAR(2002));"; //make sure this works, should go up to 2001. total 10,000 for both (100x100)

    private static final String INSERT_MAZE = "INSERT INTO mazes (mazeName, author, dateCreated, dateEdited, mazeDimensions, isSealed, startPos, endPos, mazeData, mazeDataOverflow) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_MAZE_NAMES = "SELECT mazeName FROM mazes";
    private static final String GET_MAZE = "SELECT * FROM mazes WHERE mazeName=?";
    private static final String DELETE_MAZE = "DELETE FROM mazes WHERE mazeName=?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM mazes";

    private final Connection connection;
    private PreparedStatement addMaze;
    private PreparedStatement getMazeList;
    private PreparedStatement getMaze;
    private PreparedStatement deleteMaze;
    private PreparedStatement rowCount;

    /**
     * Constructs a JDBC connection and creates links between the list of maze objects and the database.
     */
    public JDBCMazeListDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            addMaze = connection.prepareStatement(INSERT_MAZE);
            getMazeList = connection.prepareStatement(GET_MAZE_NAMES);
            getMaze = connection.prepareStatement(GET_MAZE);
            deleteMaze = connection.prepareStatement(DELETE_MAZE);
            rowCount = connection.prepareStatement(COUNT_ROWS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds a DB-formatted maze object to the database.
     *
     * @param m - the DB-formatted maze object to be added
     */
    public void addMaze(MazeDBObj m) {
        try {
            addMaze.setString(1, m.getMazeName());
            addMaze.setString(2, m.getAuthor());
            addMaze.setString(3, m.getDateTimeCreated());
            addMaze.setString(4, m.getDateTimeEdited());
            addMaze.setString(5, m.getMazeDimensions());
            addMaze.setString(6, m.getIsSealed());
            addMaze.setString(7, m.getStartPos());
            addMaze.setString(8, m.getEndPos());
            addMaze.setString(9, m.getMazeData());
            addMaze.setString(10, m.getMazeDataOverflow());
            addMaze.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves a set of maze names from the DB.
     *
     * @return - a set of maze names
     */
    public Set<String> mazeNameSet() {
        Set<String> mazeNames = new TreeSet<>();
        ResultSet rs;

        try {
            rs = getMazeList.executeQuery();
            while (rs.next()) mazeNames.add(rs.getString("mazeName"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mazeNames;
    }

    /**
     * Builds a maze object from the DB with information associated with
     * a specified maze name.
     *
     * @param mazeName - the name of the maze to be built
     * @return - the DB-formatted maze object matching the specified maze
     *           name
     */
    public MazeDBObj getMazeDBObj(String mazeName) {
        MazeDBObj m = new MazeDBObj();
        ResultSet rs;

        try {
            getMaze.setString(1, mazeName);
            rs = getMaze.executeQuery();
            rs.next();
            m.setMazeName(rs.getString("mazeName"));
            m.setAuthor(rs.getString("author"));
            m.setDateTimeCreated(rs.getString("dateCreated"));
            m.setDateTimeEdited(rs.getString("dateEdited"));
            m.setMazeDimensions(rs.getString("mazeDimensions"));
            m.setIsSealed(rs.getString("isSealed"));
            m.setStartPos(rs.getString("startPos"));
            m.setEndPos(rs.getString("endPos"));
            m.setMazeData(rs.getString("mazeData"));
            m.setMazeDataOverflow(rs.getString("mazeDataOverflow"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    /**
     * Retrieves the amount of mazes stored in the DB.
     *
     * @return - the amount of mazes stored in the database as an integer
     */
    public int getSize() {
        ResultSet rs;
        int rows = 0;

        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rows;
    }

    /**
     * Removes a specified maze from the DB.
     *
     * @param mazeName - the name of the maze to be deleted
     */
    public void deleteMaze(String mazeName) {
        try {
            deleteMaze.setString(1, mazeName);
            deleteMaze.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A method that closes the database connection
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
