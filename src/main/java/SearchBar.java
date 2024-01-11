// File: SearchBar.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel {
    private final JTextField offerNameField;
    private final JLabel nameLabel;
    private final JComboBox<String> locationDropdown;
    private final JLabel locationLabel;
    private final JComboBox<String> expLevelDropdown;
    private final JLabel expLabel;
    private final JButton searchButton;

    public SearchBar() {
        offerNameField = new JTextField(20);
        nameLabel = new JLabel("Offer Name:");
        locationDropdown = new JComboBox<>(new String[]{"dowolna", "remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
                "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
                "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"});
        locationLabel = new JLabel("Location:");
        expLevelDropdown = new JComboBox<>(new String[]{"dowolny", "junior", "mid", "regular", "senior", "expert"});
        expLabel = new JLabel("Exp Level:");
        searchButton = new JButton("Search");

        searchButton.addActionListener(e -> MainInterface.searchJobOffers());

        add(nameLabel);
        add(offerNameField);
        add(locationLabel);
        add(locationDropdown);
        add(expLabel);
        add(expLevelDropdown);
        add(searchButton);

        // Customize appearance
        customizeAppearance();
    }

    private void customizeAppearance() {
        // Set background color
        Color backgroundColor = Color.decode("#282828");
        setBackground(backgroundColor);

        // Set font color
        Color fontColor = new Color(255, 255,  255);

        // Set button background color
        searchButton.setBackground(backgroundColor);
        searchButton.setForeground(fontColor);

        // Set text field background color
        offerNameField.setBackground(backgroundColor);
        offerNameField.setForeground(fontColor);
        nameLabel.setForeground(fontColor);
        locationDropdown.setBackground(backgroundColor);
        locationDropdown.setForeground(fontColor);
        locationLabel.setForeground(fontColor);
        expLevelDropdown.setBackground(backgroundColor);
        expLevelDropdown.setForeground(fontColor);
        expLabel.setForeground(fontColor);

    }

    public String getOfferName() {
        return offerNameField.getText();
    }

    public String getOfferLocation() {
        return locationDropdown.getSelectedItem().toString();
    }

    public String getExpLevel() {
        return expLevelDropdown.getSelectedItem().toString();
    }
}
