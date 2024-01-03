//nazwa, firma, kaska, exp level, link

public class JobOffer {
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
}
