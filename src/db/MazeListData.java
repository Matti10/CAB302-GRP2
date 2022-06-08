package db;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

//do I have to javadoc this?
public class MazeListData {
    DefaultListModel<String> listModel;
    MazeListDataSource mazeListData;

    /**
     * A constructor that initialises the maze list //is this a constructor??
     */
    public MazeListData() {
        listModel = new DefaultListModel<>();
        mazeListData = new JDBCMazeListDataSource();

        for (String name : mazeListData.mazeNameSet()) { listModel.addElement(name); }
    }

    /**
     * A method to add a maze to the maze list
     *
     * @param m - A maze object suitable for storage in DB
     *          //what does this do I guess?
     */
    public void add(MazeDBObj m) {
        if (listModel.contains(m.getMazeName())) {
            listModel.removeElement(m.getMazeName());
            this.remove(m.getMazeName());
        }
        listModel.addElement(m.getMazeName());
        mazeListData.addMaze(m);
    }

    /**
     * A method to remove a maze from the maze list
     *
     * @param key - the key of the maze object in the list
     *          //what does this do I guess?
     */
    public void remove(Object key) {
        listModel.removeElement(key);
        mazeListData.deleteMaze((String) key);
    }

    /**
     * A method to close the maze list data connection
     */
    public void persist() {
        mazeListData.close();
    }

    /**
     * A method that fetches a maze name via its key
     *
     * @param key - the key of the maze object in the list
     * @return - the name of the maze matching the key
     */
    public MazeDBObj get(Object key) {
        return mazeListData.getMazeDBObj((String) key);
    }

    /**
     * A method that gives access to the maze list
     *
     * @return - the list of mazes in string array format
     */
    public ListModel<String> getModel() {
        return listModel;
    }

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
     * A method that analyses the size of the maze list
     *
     * @return - the size of the maze list
     */
    public int getSize() {
        return mazeListData.getSize();
    }
}
