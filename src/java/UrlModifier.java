import java.util.Objects;

public class UrlModifier {
    static String NoFluffJobsUrl = "https://nofluffjobs.com";
    static String justjoinITUrl = "https://justjoin.it";

    static String[] locations = {"remote", "warszawa", "krakow", "wroclaw", "gdansk", "poznan",
            "katowice", "lodz", "bialystok", "gdynia", "lublin", "rzeszow",
            "bydgoszcz", "gliwice", "czestochowa", "szczecin", "sopot"};

    public static void setLocation(int locationIndex) {
        NoFluffJobsUrl += locations[locationIndex];
        justjoinITUrl += locations[locationIndex];
    }

    public static void setLocation(String locationName) {
        if (!Objects.equals(locationName, "")) {
            NoFluffJobsUrl += "/" + locationName;
            justjoinITUrl += "/" + locationName;
        }
    }

    public static void setSeniority(String seniorityLevel) {
        if (!Objects.equals(seniorityLevel, "")) {
            NoFluffJobsUrl += "/" + seniorityLevel;
            justjoinITUrl += "/experience-level_" + seniorityLevel + "/with_salary_yes?";
        }
    }

    public static void setOfferName(String name) {
        if (!Objects.equals(name, "")) {
            name = name.replace(" ", "%20");
            NoFluffJobsUrl += "/" + name;
        }
    }
    public static void setTechnology(String tech) {
        if (!Objects.equals(tech, "")) {
            justjoinITUrl += "/" + tech;
        }
    }

    public static String[] getModifiedUrls(String inputLoc, String inputOffName, String inputSen ) {
        String[] inputTechAndPos = inputOffName.split(" ");
        String inputTech = inputTechAndPos[0];

        setLocation(inputLoc);
        setTechnology(inputTech);
        setOfferName(inputOffName);
        setSeniority(inputSen);

        String[] urls = new String[2];
        urls[0] = NoFluffJobsUrl;
        urls[1] = justjoinITUrl;

        return urls;
    }
}
