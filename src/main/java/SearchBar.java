// File: SearchBar.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel {
    private JTextField offerNameField;
    private JComboBox<String> locationDropdown;
    private JComboBox<String> expLevelDropdown;
    private JButton searchButton;
    private JobOffersTable jobOffersTable;

    public SearchBar(JobOffersTable jobOffersTable) {
        this.jobOffersTable = jobOffersTable;

        offerNameField = new JTextField(20);
        locationDropdown = new JComboBox<>(new String[]{"dowolna", "remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
                "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
                "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"});
        expLevelDropdown = new JComboBox<>(new String[]{"dowolny", "junior", "mid", "regular", "senior", "expert"});
        searchButton = new JButton("Search");

        searchButton.addActionListener(e -> searchJobOffers());

        add(new JLabel("Offer Name:"));
        add(offerNameField);
        add(new JLabel("Location:"));
        add(locationDropdown);
        add(new JLabel("Exp Level:"));
        add(expLevelDropdown);
        add(searchButton);
    }

    private void searchJobOffers() {
        // Implement the search logic and update job offers table
        String inputOfferName = offerNameField.getText();
        String inputLocation = locationDropdown.getSelectedItem().toString();
        String inputExpLevel = expLevelDropdown.getSelectedItem().toString();

        // ... perform search logic and update job offers table
    }
}
