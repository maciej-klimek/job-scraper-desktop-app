// File: MainInterface.java
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainInterface extends JFrame {
    //private static Comparator<JobOffer> currentComparator;
    private static final ArrayList<JobOffer> jobOffers = Scraper.jobOffers;
    private static JobOffersTable jobOffersTable;
    private static SearchPanel searchBar;

    /*public static void setSortingOption(String sortingOption) {
        if (jobOffersTable != null) {
            Comparator<JobOffer> comparator = null;

            switch(sortingOption){
                case "zarobkach":
                    comparator = Comparator.comparingDouble(JobOffer::getMeanSalary).reversed();
                    break;
                case "pozycji":
                    comparator = Comparator.comparing(JobOffer::getOfferName);
                    break;
                case "firmie":
                    comparator = Comparator.comparing(JobOffer::getCompany);
                    break;

                default:
                    break;
            }
            if (comparator != null){
                jobOffersTable.updateTable1(comparator);
                //currentComparator = comparator;
            }
        }
    }

    private void startUpdateTimer(){

      Timer timer = new Timer(1000, e -> {setSortingOption(searchBar.getSortingOption());
                                                    jobOffersTable.updateTable(currentComparator);
                                                    });
        timer.start();
    }*/

    public MainInterface() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Job Offers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jobOffersTable = new JobOffersTable();
        searchBar = new SearchPanel();
        searchBar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 100));

        add(searchBar, BorderLayout.NORTH);
        add(jobOffersTable, BorderLayout.CENTER);

        // Customize appearance
        customizeAppearance();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void customizeAppearance() {

        getContentPane().setBackground(UIVariables.backgroundColor2);
        setForeground(UIVariables.foregroundColor);

        searchBar.setForeground(UIVariables.foregroundColor);
        jobOffersTable.setForeground(UIVariables.foregroundColor);
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
