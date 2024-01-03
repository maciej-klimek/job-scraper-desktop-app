import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUITest extends JFrame {
    private ArrayList<JobOffer> jobOffers = Scraper.jobOffers;
    private JTextField offerNameField;
    private JTextField locationField;
    private JTextField expLevelField;

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
        locationField = new JTextField(20);
        expLevelField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchJobOffers();
            }
        });

        inputPanel.add(new JLabel("Offer Name:"));
        inputPanel.add(offerNameField);
        inputPanel.add(new JLabel("Location:"));
        inputPanel.add(locationField);
        inputPanel.add(new JLabel("Exp Level:"));
        inputPanel.add(expLevelField);
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
        // Perform web scraping and update jobOffers ArrayList based on user input
        String inputOfferName = offerNameField.getText();
        String inputLocation = locationField.getText();
        String inputExpLevel = expLevelField.getText();

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
