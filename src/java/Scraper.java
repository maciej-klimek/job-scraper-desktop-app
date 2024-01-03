import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Scraper {

    private static final Logger logger = LogManager.getLogger(Scraper.class);
    private static Document nfjWebsite;
    private static Document jjitDocument;
    private static Document offerWebsite;


    public static void scrapeData(String nfjUrl, String jjitUrl) {


        ArrayList<String> jobOffers = new ArrayList<>();


        try {
            nfjWebsite = Jsoup.connect(nfjUrl).get();
            logger.info("Succesfully connected to nofluffjobs.com");

        }
        catch (Exception ex) {
            logger.error("Cannot connect to nofluffjobs.com: invalid link or website is not responding", ex);
        }

        try {
            Elements desiredContent = nfjWebsite.select("nfj-postings-list > div.list-container");
            for (Element jobListing : desiredContent.select("a")) {

                if (!Objects.equals(jobListing.select("h3").text(), "")) {

                    // dopisac scrapowanie innych danych, nazwa pozycji, firma, logo, salary, lokacja

                    String offerName = jobListing.select("h3").text();
                    String companyName = jobListing.select("footer > h4").text();
                    String salaryValue = jobListing.select("nfj-posting-item-salary > span").text();
                    String link = jobListing.attr("href");

                    try {
                        offerWebsite = Jsoup.connect("https://nofluffjobs.com" + link).get();
                        String expLevel = offerWebsite.select("#posting-seniority > div > span").text();
                        logger.info(expLevel);

                    } catch (Exception ex){
                        logger.error("Cannot access " + link + ", ivalid link or website is not responding", ex);
                    }

//                    JobOffer currentOffer = new JobOffer(offerName, companyName, salaryValue, expLevel, link);
//                    entry += "Pozycja: " + offerName;
//                    entry += "   |   W firmie: " + companyName;
//                    entry += "   |   Zarobki: " + salaryValue;
//                    entry += "   ->    https://nofluffjobs.com" + link;
//
//                    jobOffers.add(entry);
                }
            }
            if (jobOffers.isEmpty()) {
                logger.error("Failed to load data from nofluffjobs.com");
            } else {
                logger.info("Succesfully scraped " + jobOffers.size() + " job offers from nofluffjobs.com");
            }

        }
        catch (Exception ex) {
            logger.error("An error occurred during data scraping from nofluffjobs.com", ex);
        }

        for (String jobEntry : jobOffers) {
            System.out.println(jobEntry);
        }

    }

}