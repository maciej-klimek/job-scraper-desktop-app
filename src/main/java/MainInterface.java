// File: MainInterface.java
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainInterface extends JFrame {
    private static final ArrayList<JobOffer> jobOffers = Scraper.jobOffers;
    private static JobOffersTable jobOffersTable;
    private static SearchBar searchBar;

    public MainInterface() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Job Offers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jobOffersTable = new JobOffersTable();
        searchBar = new SearchBar();

        add(searchBar, BorderLayout.NORTH);
        add(jobOffersTable, BorderLayout.CENTER);

        // Customize appearance
        customizeAppearance();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void customizeAppearance() {
        // Set background color
        Color backgroundColor = Color.decode("#282828");
        getContentPane().setBackground(backgroundColor);

        // Set font color
        Color fontColor = Color.decode("#D3D3D3"); // Light Grey
        setForeground(fontColor);
        searchBar.setForeground(fontColor);
        jobOffersTable.setForeground(fontColor);
    }

    public static void searchJobOffers() {
        jobOffers.clear();

        String inputOfferName = searchBar.getOfferName();
        String inputLocation = searchBar.getOfferLocation();
        String inputExpLevel = searchBar.getExpLevel();

        if ("dowolna".equals(inputLocation)) {
            inputLocation = "";
        }
        if ("dowolny".equals(inputExpLevel)) {
            inputExpLevel = "";
        }

        String[] urls = UrlModifier.getModifiedUrls(inputLocation, inputOfferName, inputExpLevel);
        String nofluffjobsUrl = urls[0];
        String justjoinitUrl = urls[1];

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> Scraper.getNfjData(nofluffjobsUrl));
        executor.execute(() -> Scraper.getJjitData(justjoinitUrl));

        Timer timer = new Timer(1000, e -> jobOffersTable.updateTable());
        timer.start();

        executor.shutdown();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainInterface::new);
    }
}
