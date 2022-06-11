package db;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

//do I have to javadoc this?
public class MazeListData {
    DefaultListModel<String> listModel;
    MazeListDataSource mazeListData;

    /**
     * Constructs a list of DB-formatted maze objects. The list is initialised
     * from information in the database.
     */
    public MazeListData() {
        listModel = new DefaultListModel<>();
        mazeListData = new JDBCMazeListDataSource();

        for (String name : mazeListData.mazeNameSet()) { listModel.addElement(name); }
    }

    /**
     * Adds a specified maze to the maze list. If the maze is already in the
     * maze list, removes and re-adds the maze with updated data.
     *
     * @param m - A DB-formatted maze object
     */
    public void add(MazeDBObj m) {
        if (mazeListData.mazeNameSet().contains(m.getMazeName())) {
            mazeListData.deleteMaze(m.getMazeName());
            listModel.removeElement(m.getMazeName());
        }
        mazeListData.addMaze(m);
        listModel.addElement(m.getMazeName());

    }

    /**
     * Removes a specified maze from the maze list via its name, if the maze
     * is a member of the list.
     *
     * @param key - the name of the maze object
     */
    public void remove(Object key) {
        if (listModel.contains(key)) {
            listModel.removeElement(key);
            mazeListData.deleteMaze((String) key);
        }
    }

    /**
     * lets the data persist between sessions, can remove maybe???
     */
    public void persist() {
        mazeListData.close();
    }

    /**
     * Retrieves the DB-formatted maze object that matches a specified name.
     *
     * @param key - the name of a maze
     * @return - the matching DB-formatted maze object
     */
    public MazeDBObj get(Object key) {
        return mazeListData.getMazeDBObj((String) key);
    }

    /**
     * Retrieves a list of the currently stored maze names.
     *
     * @return - a list of maze names
     */
    public ListModel<String> getModel() {
        return listModel;
    }

    /**
     * Retrieves an array of mazes and their associated information,
     * excluding the maze's cell data.
     *
     * @return - an array of String arrays, where each String array
     *           contains a maze's attributes
     */
    public String[][] getDisplayData() {
        String[][] all = new String[getSize()][];
        MazeDBObj m;
        for (int i=0;i<getSize();i++) {
            m = mazeListData.getMazeDBObj(listModel.getElementAt(i));
            all[i] = new String[]{m.getMazeName(),m.getAuthor(),m.getMazeDimensions(),m.getDateTimeCreated(),m.getDateTimeEdited()};
        }
        return all;
    }

    /**
     * Retrieves the size of the maze list
     *
     * @return - the size of the maze list as an integer
     */
    public int getSize() {
        return mazeListData.getSize();
    }
}
