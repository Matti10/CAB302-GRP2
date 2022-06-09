package db;

import java.util.Set;

//Do I javadoc this?
public interface MazeListDataSource {
    void addMaze(MazeDBObj m);

    MazeDBObj getMazeDBObj(String mazeName);

    int getSize();

    void deleteMaze(String mazeName);

    void close();

    Set<String> mazeNameSet();
}