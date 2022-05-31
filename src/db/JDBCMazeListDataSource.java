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
                    + "mazeData VARCHAR(8000)," //make sure this works, should go up to 7999
                    + "mazeDataOverflow VARCHAR(2002));"; //make sure this works, should go up to 2001. total 10,000 for both (100x100)

    private static final String INSERT_MAZE = "INSERT INTO mazes (mazeName, author, dateCreated, dateEdited, mazeDimensions, mazeData, mazeDataOverflow) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_MAZE_NAMES = "SELECT mazeName FROM mazes";
    private static final String GET_MAZE = "SELECT * FROM mazes WHERE mazeName=?";
    private static final String DELETE_MAZE = "DELETE FROM mazes WHERE mazeName=?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM mazes";

    private Connection connection;
    private PreparedStatement addMaze;
    private PreparedStatement getMazeList;
    private PreparedStatement getMaze;
    private PreparedStatement deleteMaze;
    private PreparedStatement rowCount;

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

    public void addMaze(MazeDBObj m) {
        try {
            addMaze.setString(1, m.getMazeName());
            addMaze.setString(2, m.getAuthor());
            addMaze.setString(3, m.getDateTimeCreated());
            addMaze.setString(4, m.getDateTimeEdited());
            addMaze.setString(5, m.getMazeDimensions());
            addMaze.setString(6, m.getMazeData());
            addMaze.setString(7, m.getMazeDataOverflow());
            addMaze.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Set<String> mazeNameSet() {
        Set<String> mazeNames = new TreeSet<String>();
        ResultSet rs = null;

        try {
            rs = getMazeList.executeQuery();
            while (rs.next()) mazeNames.add(rs.getString("mazeName"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return mazeNames;
    }

    public MazeDBObj getMazeName(String mazeName) {
        MazeDBObj m = new MazeDBObj();
        ResultSet rs = null;

        try {
            getMaze.setString(1, mazeName);
            rs = getMaze.executeQuery();
            rs.next();
            m.setMazeName(rs.getString("mazeName"));
            m.setAuthor(rs.getString("author"));
            m.setDateTimeCreated(rs.getString("dateCreated"));
            m.setDateTimeEdited(rs.getString("dateEdited"));
            m.setMazeDimensions(rs.getString("mazeDimensions"));
            m.setMazeData(rs.getString("mazeData"));
            m.setMazeDataOverflow(rs.getString("mazeDataOverflow"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    public int getSize() {
        ResultSet rs = null;
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

    public void deleteMaze(String mazeName) {
        try {
            deleteMaze.setString(1, mazeName);
            deleteMaze.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
