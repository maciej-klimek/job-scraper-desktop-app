// File: JobOffersTable.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class JobOffersTable extends JScrollPane {
    private JTable jobTable;

    public JobOffersTable() {
        String[] columnNames = {"Name", "Experience Level", "Company", "Salary", "Link"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        jobTable = new JTable(model);
        setViewportView(jobTable);
    }

    public void updateTable(ArrayList<JobOffer> offers) {
        DefaultTableModel model = (DefaultTableModel) jobTable.getModel();
        model.setRowCount(0);

        for (JobOffer offer : offers) {
            Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
            model.addRow(rowData);
        }
    }
}

