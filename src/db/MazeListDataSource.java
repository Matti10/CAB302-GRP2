package db;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the Address Book
 * application.
 *
 * @author Malcolm Corney<BR>
 *         SEDC, FIT, QUT
 * @version - 24/05/2006
 */
public interface MazeListDataSource {

    /**
     * Adds a MazeDBObj to the address list, if they are not already in the list
     *
     * @param m MazeDBObj to add
     */
    void addMaze(MazeDBObj m);

    /**
     * Extracts all the details of a MazeDBObj from the address book based on the
     * name passed in.
     *
     * @param mazeName The name as a String to search for.
     * @return all details in a MazeDBObj object for the name
     */
    MazeDBObj getMazeName(String mazeName);

    /**
     * Gets the number of addresses in the address book.
     *
     * @return size of address book.
     */
    int getSize();

    /**
     * Deletes a MazeDBObj from the address book.
     *
     * @param mazeName The name to delete from the address book.
     */
    void deleteMaze(String mazeName);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of names from the data source that are used in
     * the name list.
     *
     * @return set of names.
     */
    Set<String> mazeNameSet();

}