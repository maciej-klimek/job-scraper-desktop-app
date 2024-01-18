//nazwa, firma, kaska, exp level, link

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobOffer {

    private static final Logger logger = LogManager.getLogger(Scraper.class);

    String name = "";
    String company = "";
    String salary = "";
    String expLevel = "";
    String link = "";

    public JobOffer(String offerName, String offerCompany, String offerSalary, String offerExpLevel, String offerLink) {
        this.name = offerName;
        this.company = offerCompany;
        this.salary = offerSalary;
        this.expLevel = offerExpLevel;
        this.link = offerLink;
    }

    public void print() {
        System.out.println(this.name +
                "  [" + this.expLevel + "]   |  " +
                "w firmie: " + this.company + "  |  " +
                "Zarobki: " + this.salary + "  |  " +
                this.link);
    }

    public double getMeanSalary() {

        String[] salaryRange;
        try {

            String cleanedSalary = salary.replaceAll("–", "-");

            if (cleanedSalary.contains("-")) {
                salaryRange = cleanedSalary.replaceAll("[^\\d-]+", "").split("-");
                int lower = Integer.parseInt(salaryRange[0]);
                int upper = Integer.parseInt(salaryRange[1]);
                return (lower + upper) / 2.0;
            } else {
                return Integer.parseInt(cleanedSalary.replaceAll("[^\\d-]+", ""));
            }

        } catch (Exception e) {
            logger.error("Failed to get mean salary");
            return 1;
        }
    }

    public String getCompany() {

        return company;
    }

    public String getOfferName(){

        return name;
    }
}

