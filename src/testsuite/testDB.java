package testsuite;

import maze.Maze;
import org.junit.jupiter.api.BeforeEach;

public class testDB {
    Maze maze;

    @BeforeEach
    void setup() {
        maze = new Maze();
    }

}
