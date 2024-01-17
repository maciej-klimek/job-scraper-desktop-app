// File: JobOffersTable.java
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Comparator;

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

        String sortingOption = SearchPanel.selectedSorting;
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
                    Scraper.jobOffers.sort(comparator);
                }


        for (JobOffer offer : Scraper.jobOffers) {
            Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
            model.addRow(rowData);
        }
    }


    private void addLinkClickListener() {
        jobTable.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = jobTable.columnAtPoint(e.getPoint());
                int row = jobTable.rowAtPoint(e.getPoint());

                if (column == jobTable.getColumn("Link").getModelIndex() && row != -1) {
                    String link = Scraper.jobOffers.get(row).link;
                    openLinkInBrowser(link);
                }
            }
        });
    }

    private void openLinkInBrowser(String link) {
        // The actual link opening functionality remains the same
        try {
            Desktop.getDesktop().browse(new java.net.URI(link));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void customizeAppearance() {

        jobTable.setBackground(new Color(213, 215, 219));
        jobTable.getTableHeader().setBackground(UIVariables.backgroundColor1);
        jobTable.getTableHeader().setFont(UIVariables.mainFont);


        jobTable.setForeground(UIVariables.foregroundColor);
        jobTable.getTableHeader().setForeground(UIVariables.foregroundColor);

    }
}
