package db;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serial;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MazeDBUI extends JFrame {
    @Serial
    private static final long serialVersionUID = -5050538890770582361L;
    private JList<String> mazeNameList;
    private JTextField mazeName;
    private JTextField author;
    private JTextField dateTimeCreated;
    private JTextField dateTimeEdited;
    private JTextField mazeDimensions;
    private JTextField mazeData;
    private JButton newButton;
    private JButton saveButton;
    private JButton deleteButton;

    MazeListData data;

    public MazeDBUI(MazeListData data) {
        this.data = data;
        initUI();
        checkListSize();

        // add listeners to interactive components
        addButtonListeners(new ButtonListener());
        addNameListListener(new NameListListener());
        addClosingListener(new ClosingListener());

        // decorate the frame and make it visible
        setTitle("Maze DB");
        setMinimumSize(new Dimension(400, 300));
        pack();
        setVisible(true);

    }

    private void initUI() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(makeDetailsPanel());
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(makeButtonsPanel());
        contentPane.add(Box.createVerticalStrut(20));
    }

    private JPanel makeDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeMazeNameListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeMazeDetailsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));
        return detailsPanel;
    }

    private JScrollPane makeMazeNameListPane() {
        mazeNameList = new JList<>(data.getModel());
        mazeNameList.setFixedCellWidth(200);

        JScrollPane scroller = new JScrollPane(mazeNameList);
        scroller.setViewportView(mazeNameList);
        scroller
                .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));

        return scroller;
    }

    private JPanel makeMazeDetailsPanel() {
        JPanel mazeDetailsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mazeDetailsPanel);
        mazeDetailsPanel.setLayout(layout);

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Name");
        JLabel authorLabel = new JLabel("Author");
        JLabel createdLabel = new JLabel("Created");
        JLabel editedLabel = new JLabel("Edited");
        JLabel dimsLabel = new JLabel("Dimensions");
        JLabel dataLabel = new JLabel("Data");

        mazeName = new JTextField(20);
        author = new JTextField(20);
        dateTimeCreated = new JTextField(20);
        dateTimeEdited = new JTextField(20);
        mazeDimensions = new JTextField(20);
        mazeData = new JTextField(20);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel)
                .addComponent(authorLabel).addComponent(createdLabel).addComponent(editedLabel)
                .addComponent(dimsLabel).addComponent(dataLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(mazeName)
                .addComponent(author).addComponent(dateTimeCreated).addComponent(dateTimeEdited)
                .addComponent(mazeDimensions).addComponent(mazeData));
        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        // The sequential group contains five parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field etc. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(nameLabel).addComponent(mazeName));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(authorLabel).addComponent(author));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(createdLabel).addComponent(dateTimeCreated));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(editedLabel).addComponent(dateTimeEdited));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(dimsLabel).addComponent(mazeDimensions));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(dataLabel).addComponent(mazeData));
        layout.setVerticalGroup(vGroup);

        return mazeDetailsPanel;
    }

    private JPanel makeButtonsPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        newButton = new JButton("New");
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        deleteButton = new JButton("Delete");
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(newButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalStrut(50));
        return buttonPanel;
    }

    private void addButtonListeners(ActionListener listener) {
        newButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
    }

    private void addNameListListener(ListSelectionListener listener) {
        mazeNameList.addListSelectionListener(listener);
    }

    private void addClosingListener(WindowListener listener) {
        addWindowListener(listener);
    }

    private void clearFields() {
        mazeName.setText("");
        author.setText("");
        dateTimeCreated.setText("");
        dateTimeEdited.setText("");
        mazeDimensions.setText("");
        mazeData.setText("");
    }

    private void setFieldsEditable(boolean editable) {
        mazeName.setEditable(editable);
        author.setEditable(editable);
        dateTimeCreated.setEditable(editable);
        dateTimeEdited.setEditable(editable);
        mazeDimensions.setEditable(editable);
        mazeData.setEditable(editable);
    }

    private void display(MazeDBObj maze) {
        if (maze != null) {
            mazeName.setText(maze.getMazeName());
            author.setText(maze.getAuthor());
            dateTimeCreated.setText(maze.getDateTimeCreated());
            dateTimeEdited.setText(maze.getDateTimeEdited());
            mazeDimensions.setText(maze.getMazeDimensions());
            mazeData.setText(maze.getMazeData()+maze.getMazeDataOverflow());
        }
    }

    private void checkListSize() {
        deleteButton.setEnabled(data.getSize() != 0);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int size = data.getSize();

            JButton source = (JButton) e.getSource();
            if (source == newButton) {
                newPressed();
            } else if (source == saveButton) {
                savePressed();
            } else if (source == deleteButton) {
                deletePressed();
            }
        }

        private void newPressed() {
            clearFields();
            setFieldsEditable(true);
            saveButton.setEnabled(true);
        }

        private void savePressed() {
            if (mazeName.getText() != null && !mazeName.getText().equals("")) {
                StringBuilder mazeData1 = new StringBuilder();
                StringBuilder mazeData2 = new StringBuilder();
                if (mazeData.getText().length() > 10) {
                    for (int i = 0; i < 10; i++) {
                        mazeData1.append(mazeData.getText().charAt(i));
                    }
                    for (int i = 10; i < mazeData.getText().length(); i++) {
                        mazeData2.append(mazeData.getText().charAt(i));
                    }
                } else {
                    mazeData1 = new StringBuilder(mazeData.getText());
                }
                MazeDBObj m = new MazeDBObj(mazeName.getText(), author.getText(), dateTimeCreated .getText(),
                        dateTimeEdited.getText(), mazeDimensions.getText(), mazeData1.toString(), mazeData2.toString());
                data.add(m);
            }
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
        }

        private void deletePressed() {
            int index = mazeNameList.getSelectedIndex();
            data.remove(mazeNameList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (data.getSize() != 0) {
                    index = 0;
                }
            }
            mazeNameList.setSelectedIndex(index);
            checkListSize();
        }
    }

    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different name is selected from the list.
     */
    private class NameListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (mazeNameList.getSelectedValue() != null
                    && !mazeNameList.getSelectedValue().equals("")) {
                display(data.get(mazeNameList.getSelectedValue()));
            }
        }
    }

    /**
     * Implements the windowClosing method from WindowAdapter/WindowListener to
     * persist the contents of the data/model.
     */
    private class ClosingListener extends WindowAdapter {

        /**
         * @see WindowAdapter#windowClosing(WindowEvent)
         */
        public void windowClosing(WindowEvent e) {
            data.persist();
            System.exit(0);
        }
    }
}
