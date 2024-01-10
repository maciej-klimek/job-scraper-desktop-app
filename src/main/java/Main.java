import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {

    // for testing --------------------------------
    static String inputLocation = "krakow";
    static String inputOfferName = "python developer";
    static String inputExpLevel = "junior";
    // -------------------------------------------

    private static final Logger logger = LogManager.getLogger(Scraper.class);

    public static void main(String[] args) {
        logger.info("A");
        String[] urls = UrlModifier.getModifiedUrls(inputLocation, inputOfferName, inputExpLevel);
        String NoFluffJobsUrl = urls[0];
        String justjoinitUrl = urls[1];

        System.out.println("--------------------------------------");
        System.out.println("Looking for (" + inputExpLevel + ") " + inputOfferName + " in " + inputLocation + ".");
        System.out.println("Using link: " + NoFluffJobsUrl);
        System.out.println("Using link: " + justjoinitUrl);
        System.out.println("--------------------------------------");

        logger.info("Successfully displayed data");
    }
}

