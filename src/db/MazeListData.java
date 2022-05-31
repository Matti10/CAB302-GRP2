package db;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class MazeListData {
    DefaultListModel listModel;
    MazeListDataSource mazeListData;

    public MazeListData() {
        listModel = new DefaultListModel();
        mazeListData = new JDBCMazeListDataSource();

        for (String name : mazeListData.mazeNameSet()) { listModel.addElement(name); }
    }

    public void add(MazeDBObj m) {
        if (!listModel.contains(m.getMazeName())) {
            listModel.addElement(m.getMazeName());
            mazeListData.addMaze(m);
        }
    }

    public void remove(Object key) {
        listModel.removeElement(key);
        mazeListData.deleteMaze((String) key);
    }

    public void persist() {
        mazeListData.close();
    }

    public MazeDBObj get(Object key) {
        return mazeListData.getMazeName((String) key);
    }

    public ListModel getModel() {
        return listModel;
    }

    public int getSize() {
        return mazeListData.getSize();
    }
}
