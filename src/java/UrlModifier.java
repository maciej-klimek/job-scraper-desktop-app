import java.util.Objects;

public class UrlModifier {
    static String nofluffjobsUrl = "https://nofluffjobs.com";
    static String justjoinitUrl = "https://justjoin.it";


    public static void setSeniority(String seniorityLevel) {
        if (!Objects.equals(seniorityLevel, "")) {
            nofluffjobsUrl += "/" + seniorityLevel;
            justjoinitUrl += "/experience-level_" + seniorityLevel + "/with_salary_yes?";
        }
    }

    public static void setOfferName(String name) {
        if (!Objects.equals(name, "")) {
            name = name.replace(" ", "%20");
            nofluffjobsUrl += "/" + name;
        }
    }
    public static void setTechnology(String tech) {
        if (!Objects.equals(tech, "")) {
            justjoinitUrl += "/" + tech;
        }
    }

    public static String[] getModifiedUrls(String inputLoc, String inputOffName, String inputExp ) {

//        String[] inputTechAndPos = inputOffName.split(" ");
//        String inputTech = inputTechAndPos[0];
//        setTechnology(inputTech);

        if (!Objects.equals(inputLoc, "")) {
            nofluffjobsUrl += "/" + inputLoc;
            justjoinitUrl += "/" + inputLoc;
        }

        if (!Objects.equals(inputExp, "")) {
            nofluffjobsUrl += "/" + inputExp;
            justjoinitUrl += "/experience-level_" + inputExp;
        }

        if (!Objects.equals(inputOffName, "")) {

            inputOffName = inputOffName.trim();

            inputOffName = inputOffName.replace(" ", "%20");
            nofluffjobsUrl += "/" + inputOffName;
            inputOffName = inputOffName.replace("%20", "+");
            justjoinitUrl += "/with_salary_yes?keyword=" + inputOffName;
        }

        String[] urls = new String[2];
        urls[0] = nofluffjobsUrl;
        urls[1] = justjoinitUrl;

        nofluffjobsUrl = "https://nofluffjobs";
        justjoinitUrl = "https://justjoin.it";

        return urls;
    }
}
