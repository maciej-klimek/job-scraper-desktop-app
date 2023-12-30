import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scraper {

    private static final Logger logger = LogManager.getLogger(Scraper.class);
    private static Document nfjDocument;
    private static Document jjitDocument;


    public static void scrapeData(String nfjUrl, String jjitUrl) {


        ArrayList<String> nofluffjobsData = new ArrayList<>();
        ArrayList<String> justJoinItData = new ArrayList<>();


        try {
            nfjDocument = Jsoup.connect(nfjUrl).get();
            logger.info("Succesfully connected to nofluffjobs.com");

        }
        catch (Exception ex) {
            logger.error("Cannot connect to nofluffjobs.com: invalid link or site is not responding", ex);
        }

        try {
            Elements desiredContent = nfjDocument.select("nfj-postings-list > div.list-container");
            for (Element jobListing : desiredContent.select("a")) {

                if (!Objects.equals(jobListing.select("h3").text(), "")) {

                    // dopisac scrapowanie innych danych, nazwa pozycji, firma, logo, salary, lokacja

                    String offerName = jobListing.select("h3").text();
                    String companyName = jobListing.select("footer > h4").text();
                    String salaryValue = jobListing.select("nfj-posting-item-salary > span").text();
                    String link = jobListing.attr("href");

                    String entry = "";
                    entry += "Pozycja: " + offerName;
                    entry += "   |   W firmie: " + companyName;
                    entry += "   |   Zarobki: " + salaryValue;
                    entry += "   ->    https://nofluffjobs.com" + link;

                    nofluffjobsData.add(entry);
                }
            }
            logger.info("Succesfully scraped " + nofluffjobsData.size() + " job listings from nofluffjobs.com");

        }
        catch (Exception ex) {
            logger.error("An error occurred during data scraping from nofluffjobs.com", ex);
        }

        for (String jobEntry : nofluffjobsData) {
            System.out.println(jobEntry);
        }

    }


}