// File: SearchBar.java
import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.RIGHT;

public class SearchPanel extends JPanel {
    private final JTextField offerNameField;
    private final JLabel nameLabel;
    private final JComboBox<String> locationDropdown;
    private final JLabel locationLabel;
    private final JComboBox<String> expLevelDropdown;
    private final JLabel expLabel;
    private final JButton searchButton;

    public SearchPanel() {
        setLayout(new GridLayout(1, 5, 10, 5));

        offerNameField = new JTextField(10);
        offerNameField.setText("java developer");
        nameLabel = new JLabel("Offer Name:", RIGHT);

        locationDropdown = new JComboBox<>(new String[]{"dowolna", "remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
                "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
                "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"});
        locationLabel = new JLabel("Location:", RIGHT);
        locationDropdown.setSelectedIndex(3);

        expLevelDropdown = new JComboBox<>(new String[]{"dowolny", "junior", "mid", "regular", "senior", "expert"});
        expLevelDropdown.setSelectedIndex(4);
        expLabel = new JLabel("Exp Level:", RIGHT);

        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> MainInterface.searchJobOffers());

        add(nameLabel);
        add(offerNameField);
        add(locationLabel);
        add(locationDropdown);
        add(expLabel);
        add(expLevelDropdown);
        add(new JLabel());
        add(searchButton);

        // Customize appearance
        customizeAppearance();
    }

    private void customizeAppearance() {

        setBackground(UIVariables.backgroundColor2);

        offerNameField.setFont(UIVariables.mainFont);
        offerNameField.setBackground(UIVariables.backgroundColor1);
        offerNameField.setForeground(UIVariables.foregroundColor);
        nameLabel.setForeground(UIVariables.foregroundColor);
        nameLabel.setFont(UIVariables.mainFont);
        offerNameField.setBorder(UIVariables.getButtonBorder(5, 5, 5, 5, 2));

        locationDropdown.setFont(UIVariables.mainFont);
        locationDropdown.setBackground(UIVariables.backgroundColor1);
        locationDropdown.setForeground(UIVariables.foregroundColor);
        locationLabel.setForeground(UIVariables.foregroundColor);
        locationLabel.setFont(UIVariables.mainFont);

        expLevelDropdown.setFont(UIVariables.mainFont);
        expLevelDropdown.setBackground(UIVariables.backgroundColor1);
        expLevelDropdown.setForeground(UIVariables.foregroundColor);
        expLabel.setForeground(UIVariables.foregroundColor);
        expLabel.setFont(UIVariables.mainFont);

        searchButton.setFont(UIVariables.mainFont);
        searchButton.setBackground(new Color(235, 235, 235));
        searchButton.setForeground(UIVariables.foregroundColor);
        searchButton.setFont(UIVariables.mainFont);
        searchButton.setBorder(UIVariables.getButtonBorder(10, 0, 10, 0, 2));

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
