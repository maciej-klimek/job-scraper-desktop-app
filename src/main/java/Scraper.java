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
    static ArrayList<JobOffer> jobOffers = new ArrayList<>();



    public static void scrapeData(String nfjUrl, String jjitUrl) {
        logger.error("aa");
        System.out.println("aaa");

        //SCRAPER NOFLUFFJOBS ----------------------------------------------------------------------------------------

        try {
            nfjWebsite = Jsoup.connect(nfjUrl).get();
            logger.info("Succesfully connected to " + nfjUrl);

        }
        catch (Exception ex) {
            logger.error("Cannot connect to " + nfjUrl + ": invalid link or website is not responding", ex);
        }

        Document offerWebsite;
        try {
            Elements desiredContent = nfjWebsite.select("nfj-postings-list > div.list-container");
            int offerCount = 0;

            for (Element jobListing : desiredContent.select("a")) {

                if (!Objects.equals(jobListing.select("h3").text(), "")) {


                    String offerName = jobListing.select("h3").text();
                    String companyName = jobListing.select("footer > h4").text();
                    String salaryValue = jobListing.select("nfj-posting-item-salary > span").text();
                    String link = "https://nofluffjobs.com" + jobListing.attr("href");
                    String expLevel = "not specified";

                    try {
                        offerWebsite = Jsoup.connect(link).get();
                        expLevel = offerWebsite.select("#posting-seniority > div > span").text();

                    } catch (Exception ex){
                        logger.error("Cannot access " + link + ", invalid link or website is not responding", ex);
                    }

                    JobOffer currentOffer = new JobOffer(offerName, companyName, salaryValue, expLevel, link);
                    jobOffers.add(currentOffer);
                    offerCount++;
                    logger.info("Scraped offer " + currentOffer.name);
                }
            }

            if (jobOffers.isEmpty()) {
                logger.error("Failed to load data from nofluffjobs.com");
            } else {
                logger.info("Succesfully scraped " + offerCount + " job offers from " + nfjUrl + "\n");
            }

        }
        catch (Exception ex) {
            logger.error("An error occurred during data scraping from " + nfjUrl, ex);
        }

        //SCRAPER JUSTJOINIT -----------------------------------------------------------------------------------------
        try{
            jjitDocument = Jsoup.connect(jjitUrl).get();
            logger.info("Succesfully connected to  " + jjitUrl);

        }
        catch (Exception ex){
            logger.error("Cannot connect to " + jjitUrl + ": invalid link or website is not responding", ex);
        }

        try {
            Elements desiredContent = jjitDocument.select("div.css-2crog7");
            int offerCount = 0;

                for (Element jobListing:desiredContent){

                    String offerName = jobListing.select("h2.css-16gpjqw").text();
                    String companyName = jobListing.select("div.css-ldh1c9").text();
                    String salaryValue = jobListing.select("div.css-1b2ga3v").text().toUpperCase();
                    String link = "https://justjoin.it" + jobListing.select("a.css-4lqp8g").attr("href");
                    String expLevel = "not specified";

                    try{
                        offerWebsite = Jsoup.connect(link).get();
                        expLevel = offerWebsite.select("div.css-15qbbm2").get(1).text();
                    }
                    catch (Exception ex){
                        logger.error("Cannot access " + link + ", invalid link or website is not responding", ex);
                    }

                    JobOffer currentOffer = new JobOffer(offerName, companyName, salaryValue, expLevel, link);
                    jobOffers.add(currentOffer);
                    offerCount++;
                    logger.info("Scraped offer " + currentOffer.name);
                }
            if (jobOffers.isEmpty()) {
                logger.error("Failed to load data from nofluffjobs.com");
            } else {
                logger.info("Succesfully scraped " + offerCount + " job offers from " + jjitUrl + "\n");
            }
        }
        catch (Exception ex){
            logger.error("An error occurred during data scraping from " + jjitUrl, ex);
        }

    }
}