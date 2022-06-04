package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection instance = null;

    /**
     * A constructor that initialises the database connection by specifying the appropriate access fields
     */
    private DBConnection() {
        Properties props = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            instance = DriverManager.getConnection(url + "/" + schema, username, password);
        } catch (SQLException | FileNotFoundException sqle) {
            System.err.println(sqle);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //not sure how to annotate this
    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }
}
