import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUITest extends JFrame {
    private ArrayList<JobOffer> jobOffers = Scraper.jobOffers;
    private JTextField offerNameField;
    private JComboBox<String> locationDropdown;
    private JComboBox<String> expLevelDropdown;

    public GUITest() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Job Offers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input fields
        JPanel inputPanel = new JPanel();
        offerNameField = new JTextField(20);
        locationDropdown = new JComboBox<>(new String[]{"dowolna", "remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
                "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
                "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"});

        expLevelDropdown = new JComboBox<>(new String[]{"dowolny", "junior", "mid", "regular", "senior", "expert"});
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> searchJobOffers());

        inputPanel.add(new JLabel("Offer Name:"));
        inputPanel.add(offerNameField);
        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(locationDropdown);
        inputPanel.add(new JLabel("Exp Level:"));
        inputPanel.add(expLevelDropdown);
        inputPanel.add(searchButton);

        // Table
        String[] columnNames = {"Name", "Experience Level", "Company", "Salary", "Link"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable jobTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jobTable);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchJobOffers() {
        jobOffers.clear();
        // Perform web scraping and update jobOffers ArrayList based on user input
        String inputOfferName = offerNameField.getText();
        String inputLocation = locationDropdown.getSelectedItem().toString();
        String inputExpLevel = expLevelDropdown.getSelectedItem().toString();

        // Convert "dowolne" to an empty string
        if ("dowolna".equals(inputLocation)) {
            inputLocation = "";
        }
        if ("dowolny".equals(inputExpLevel)) {
            inputExpLevel = "";
        }

        String[] urls = UrlModifier.getModifiedUrls(inputLocation, inputOfferName, inputExpLevel);
        String NoFluffJobsUrl = urls[0];
        String justjoinitUrl = urls[1];

        Scraper.scrapeData(NoFluffJobsUrl, justjoinitUrl);
        updateTable(jobOffers);
    }

    private void updateTable(ArrayList<JobOffer> offers) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) getContentPane().getComponent(1)).getViewport().getView()).getModel();
        model.setRowCount(0);

        for (JobOffer offer : offers) {
            Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
            model.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUITest());
    }
}
