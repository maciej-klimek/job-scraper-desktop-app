// File: JobOffersTable.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JobOffersTable extends JScrollPane {
    private final JTable jobTable;

    public JobOffersTable() {
        String[] columnNames = {"Name", "Experience Level", "Company", "Salary", "Link"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        jobTable = new JTable(model);
        setViewportView(jobTable);

        // Customize appearance
        customizeAppearance();
    }

    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) jobTable.getModel();
        model.setRowCount(0);

        for (JobOffer offer : Scraper.jobOffers) {
            Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
            model.addRow(rowData);
        }
    }

    private void customizeAppearance() {
        // Set background color
        Color backgroundColor = Color.decode("#282828");
        jobTable.setBackground(backgroundColor);
        jobTable.getTableHeader().setBackground(backgroundColor);

        // Set font color
        Color fontColor = Color.decode("#D3D3D3"); // Light Grey
        jobTable.setForeground(fontColor);
        jobTable.getTableHeader().setForeground(fontColor);

        // Set grid color
        Color gridColor = Color.decode("#505050");
        jobTable.setGridColor(gridColor);

        // Set selection background color
        Color selectionColor = Color.decode("#505050");
        jobTable.setSelectionBackground(selectionColor);

        // Set selection foreground color
        Color selectionFontColor = Color.decode("#D3D3D3"); // Light Grey
        jobTable.setSelectionForeground(selectionFontColor);
    }
}
