import java.util.Objects;

public class UrlModifier {
    static String NoFluffJobsUrl = "https://nofluffjobs.com";
    static String justjoinITUrl = "https://justjoin.it/with-salary_yes";

    static String[] locations = {"remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
            "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
            "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"};

    public static void setLocation(int locationIndex) {
        NoFluffJobsUrl += locations[locationIndex];
        justjoinITUrl = "https://justjoin.it/" + locations[locationIndex] + "/with-salary_yes";
    }

    public static void setLocation(String locationName) {
        if (!Objects.equals(locationName, "")) {
            NoFluffJobsUrl += "/" + locationName;
            justjoinITUrl = "https://justjoin.it/" + locationName + "/with-salary_yes";
        }
    }

    public static void setSeniority(String seniorityLevel) {
        if (!Objects.equals(seniorityLevel, "")) {
            NoFluffJobsUrl += "/" + seniorityLevel;
            justjoinITUrl = "https://justjoin.it/" + seniorityLevel + "/with-salary_yes";
        }
    }

    public static void setOfferName(String name) {
        if (!Objects.equals(name, "")) {
            name = name.replace(" ", "%20");
            NoFluffJobsUrl += "/" + name;
            justjoinITUrl += "/" + name;
        }
    }

    public static String[] getModifiedUrls(String inputLoc, String inputOffName, String inputSen ) {
        setLocation(inputLoc);
        setSeniority(inputSen);
        setOfferName(inputOffName);

        String[] urls = new String[2];
        urls[0] = NoFluffJobsUrl;
        urls[1] = justjoinITUrl;

        return urls;
    }
}
