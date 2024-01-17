// File: JobOffersTable.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    public void updateTable(Comparator<JobOffer> comparator) {
        DefaultTableModel model = (DefaultTableModel) jobTable.getModel();
        model.setRowCount(0);

        //Scraper.jobOffers.sort(Comparator.comparingDouble(JobOffer::getMeanSalary).reversed());
        //Scraper.jobOffers.sort(Comparator.comparing(JobOffer::getCompany));
        //Scraper.jobOffers.sort(Comparator.comparing(JobOffer::getOfferName));
        Scraper.jobOffers.sort(comparator);

        for (JobOffer offer : Scraper.jobOffers) {
            Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
            model.addRow(rowData);
        }
    }

    public void updateTable1(Comparator<JobOffer> comparator){
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) jobTable.getModel();

            synchronized (Scraper.jobOffers){
                if(comparator != null){
                    Scraper.jobOffers.sort(comparator);
                }
            }

            model.setRowCount(0);

            for(JobOffer offer : Scraper.jobOffers){
                Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
                model.addRow(rowData);
            }
        });
    }

    /*public void sortByMeanSalary(){
        updateTable(Comparator.comparingDouble(JobOffer::getMeanSalary).reversed());
    }

    public void sortByCompanyName(){
        updateTable(Comparator.comparing(JobOffer::getCompany));
    }

    public void sortByOfferName(){
        updateTable(Comparator.comparing(JobOffer::getOfferName));
    }*/


    private void customizeAppearance() {

        jobTable.setBackground(new Color(213, 215, 219));
        jobTable.getTableHeader().setBackground(UIVariables.backgroundColor1);
        jobTable.getTableHeader().setFont(UIVariables.mainFont);


        jobTable.setForeground(UIVariables.foregroundColor);
        jobTable.getTableHeader().setForeground(UIVariables.foregroundColor);

    }
}
