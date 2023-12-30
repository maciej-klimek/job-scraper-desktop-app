import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {

    // for testing --------------------------------
    static String inputLocation = "Gdansk";
    static String inputOfferName = "Python developer";
    static String inputSeniority = "regular";
    // -------------------------------------------

    private static final Logger logger = LogManager.getLogger(Scraper.class);

    public static void main(String[] args) {

        String[] urls = UrlModifier.getModifiedUrls(inputLocation, inputOfferName, inputSeniority);
        String NoFluffJobsUrl = urls[0];
        String justjoinitUrl = urls[1];

        System.out.println("--------------------------------------");
        System.out.println("Looking for (" + inputSeniority + ") " + inputOfferName + " in " + inputLocation + ".");
        System.out.println("Using link: " + NoFluffJobsUrl);
        System.out.println("--------------------------------------");

        Scraper.scrapeData(NoFluffJobsUrl, justjoinitUrl);
        logger.info("Successfully displayed data");
    }
}
