// File: GUITest.java
import javax.swing.*;
import java.awt.*;

public class GUITest extends JFrame {

    public GUITest() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Job Offers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JobOffersTable jobOffersTable = new JobOffersTable();
        SearchBar searchBar = new SearchBar(jobOffersTable);

        add(searchBar, BorderLayout.NORTH);
        add(jobOffersTable, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUITest());
    }
}
