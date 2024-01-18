// File: JobOffersTable.java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(Scraper.class);


    public JobOffersTable() {
        String[] columnNames = {"Name", "Experience Level", "Company", "Salary", "Link"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        jobTable = new JTable(model);
        setViewportView(jobTable);

        customizeAppearance();

        addLinkClickListener();
    }

    public void updateTable() {

        try {

            DefaultTableModel model = (DefaultTableModel) jobTable.getModel();
            model.setRowCount(0);

            String sortingOption = SearchPanel.selectedSorting;
            Comparator<JobOffer> comparator = null;

            switch (sortingOption) {
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
            if (comparator != null) {
                Scraper.jobOffers.sort(comparator);
            }


            for (JobOffer offer : Scraper.jobOffers) {
                Object[] rowData = {offer.name, offer.expLevel, offer.company, offer.salary, offer.link};
                model.addRow(rowData);
            }
        } catch (Exception e) {
            logger.error("Failed to update the table");
        }
    }

    private void addLinkClickListener() {
        try {
            jobTable.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = jobTable.columnAtPoint(e.getPoint());
                    int row = jobTable.rowAtPoint(e.getPoint());

                    if (column == getColumnIndex("Link")) {
                        String link = jobTable.getValueAt(row, column).toString();
                        openLinkInBrowser(link);
                    }
                }
            });
        } catch (Exception e) {
            logger.error("Failed to add click listener");
        }
    }

    private int getColumnIndex(String columnName) {
        try {
            for (int i = 0; i < jobTable.getColumnCount(); i++) {
                if (columnName.equals(jobTable.getColumnName(i))) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            logger.error("Failer to get a collumn index");
            return -1;
        }
    }

    private void openLinkInBrowser(String link) {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception ex) {
            logger.error("Failed to open website via link");
        }
    }

    private void customizeAppearance() {
        try {
            jobTable.setBackground(new Color(213, 215, 219));

            jobTable.setRowHeight(30);
            jobTable.setIntercellSpacing(new Dimension(10, 5));

            jobTable.getTableHeader().setBackground(UIVariables.backgroundColor1);
            jobTable.getTableHeader().setFont(UIVariables.mainFont);

            jobTable.setForeground(UIVariables.foregroundColor);
            jobTable.getTableHeader().setForeground(UIVariables.foregroundColor);

            int totalWidth = getWidth();

            int offerNameColumnIndex = getColumnIndex("Name");
            int salaryColumnIndex = getColumnIndex("Salary");
            int linkColumnIndex = getColumnIndex("Link");


            if (offerNameColumnIndex != -1) {
                int width = (int) (totalWidth * 0.3);
                jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(width);

            }


            if (offerNameColumnIndex != -1) {
                int width = (int) (totalWidth * 0.3);
                jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(width);

                jobTable.getColumnModel().getColumn(offerNameColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
                    Font font = jobTable.getFont().deriveFont(Font.BOLD, 14);

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

            if (linkColumnIndex != -1) {
                jobTable.getColumnModel().getColumn(linkColumnIndex).setCellRenderer(new DefaultTableCellRenderer() {
                    Font font = jobTable.getFont().deriveFont(Font.BOLD);
                    Color linkColor = Color.BLUE;

                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value,
                                                                   boolean isSelected, boolean hasFocus,
                                                                   int row, int column) {
                        Component comp = super.getTableCellRendererComponent(table, value,
                                isSelected, hasFocus, row, column);
                        comp.setFont(font);
                        comp.setForeground(linkColor);

                        return comp;
                    }
                });
            }

            jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(300);
            jobTable.getColumnModel().getColumn(offerNameColumnIndex).setPreferredWidth(300);

        } catch (Exception e) {
            logger.error("Failed to customize table appearance");
        }
    }

}
