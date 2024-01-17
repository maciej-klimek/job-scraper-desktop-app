// File: JobOffersTable.java
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URI;
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

        addLinkClickListener();
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

                // Check if the clicked column is the "Link" column
                if (column == getColumnIndex("Link")) {
                    String link = jobTable.getValueAt(row, column).toString();
                    openLinkInBrowser(link);
                }
            }
        });
    }

    private int getColumnIndex(String columnName) {
        for (int i = 0; i < jobTable.getColumnCount(); i++) {
            if (columnName.equals(jobTable.getColumnName(i))) {
                return i;
            }
        }
        return -1;
    }

    private void openLinkInBrowser(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void customizeAppearance() {
        jobTable.setBackground(new Color(213, 215, 219));

        // Set more space in the cells (increase row height and column margin)
        jobTable.setRowHeight(30);  // Adjust the row height as needed
        jobTable.setIntercellSpacing(new Dimension(10, 5));  // Adjust the spacing as needed

        jobTable.getTableHeader().setBackground(UIVariables.backgroundColor1);
        jobTable.getTableHeader().setFont(UIVariables.mainFont);

        jobTable.setForeground(UIVariables.foregroundColor);
        jobTable.getTableHeader().setForeground(UIVariables.foregroundColor);

        int totalWidth = getWidth();  // Get the total width of the application window

        // Set relative widths for each column based on percentages
        int offerNameColumnIndex = getColumnIndex("Name");
        if (offerNameColumnIndex != -1) {
            int width = (int) (totalWidth * 0.3);  // Adjust the percentage as needed
            jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(width);

        }

        int salaryColumnIndex = getColumnIndex("Salary");



        if (offerNameColumnIndex != -1) {
            int width = (int) (totalWidth * 0.3);  // Adjust the percentage as needed
            jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(width);

            // Center-align and increase font size for the "Name" column
            jobTable.getColumnModel().getColumn(offerNameColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
                Font font = jobTable.getFont().deriveFont(Font.BOLD, 14);  // Adjust the font size as needed

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                    label.setFont(font);
                    return label;
                }
            });
        }

        if (salaryColumnIndex != -1) {
            jobTable.getColumnModel().getColumn(salaryColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
                Font font = jobTable.getFont().deriveFont(Font.BOLD);

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                    comp.setFont(font);
                    return comp;
                }
            });
        }

        jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(300);  // Adjust the width as needed
        jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(300);  // Adjust the width as needed


    }

}
