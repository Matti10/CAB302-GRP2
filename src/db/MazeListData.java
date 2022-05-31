package db;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * This version uses an MazeListDataSource and its methods to retrieve data
 *
 * @author Malcolm Corney
 * @version $Id: Exp $
 *
 */
public class MazeListData {

    DefaultListModel listModel;

    /* BEGIN MISSING CODE */
    MazeListDataSource mazeListData;
    /* END MISSING CODE */

    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public MazeListData() {
        listModel = new DefaultListModel();
        /* BEGIN MISSING CODE */
        mazeListData = new JDBCMazeListDataSource();
        /* END MISSING CODE */

        // add the retrieved data to the list model
        for (String name : mazeListData.mazeNameSet()) {
            listModel.addElement(name);
        }
    }

    /**
     * Adds a person to the address book.
     *
     * @param m A MazeDBObj to add to the address book.
     */
    public void add(MazeDBObj m) {

        // check to see if the person is already in the book
        // if not add to the address book and the list model
        if (!listModel.contains(m.getMazeName())) {
            listModel.addElement(m.getMazeName());
            mazeListData.addMaze(m);
        }
    }

    /**
     * Based on the name of the person in the address book, delete the person.
     *
     * @param key
     */
    public void remove(Object key) {

        // remove from both list and map
        listModel.removeElement(key);
        mazeListData.deleteMaze((String) key);
    }

    /**
     * Saves the data in the address book using a persistence
     * mechanism.
     */
    public void persist() {
        mazeListData.close();
    }

    /**
     * Retrieves MazeDBObj details from the model.
     *
     * @param key the name to retrieve.
     * @return the MazeDBObj object related to the name.
     */
    public MazeDBObj get(Object key) {
        return mazeListData.getMazeName((String) key);
    }

    /**
     * Accessor for the list model.
     *
     * @return the listModel to display.
     */
    public ListModel getModel() {
        return listModel;
    }

    /**
     * @return the number of names in the Address Book.
     */
    public int getSize() {
        return mazeListData.getSize();
    }
}
