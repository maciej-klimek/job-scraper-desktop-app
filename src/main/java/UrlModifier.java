import java.util.Objects;

public class UrlModifier {
    static String nofluffjobsUrl = "https://nofluffjobs.com";
    static String justjoinitUrl = "https://justjoin.it";

    public static void getNfjUrl(String inputLoc, String inputOffName, String inputExp) {
        if (!Objects.equals(inputLoc, "")) {
            nofluffjobsUrl += "/" + inputLoc + "?page=1";
        }
        else {
            nofluffjobsUrl += "/?page=1";
        }

        if (!Objects.equals(inputOffName, "")) {
            inputOffName = inputOffName.replace(" ", "%20");
            nofluffjobsUrl += "&criteria=jobPosition%3D%27" + inputOffName + "%27%20";
        } else {
            nofluffjobsUrl += "&criteria=";
        }

        if (!Objects.equals(inputExp, "")) {
            nofluffjobsUrl += "seniority%3D" + inputExp;
        }

    }

    public static void getJjitUrl(String inputLoc, String inputOffName, String inputExp) {
        if (!Objects.equals(inputLoc, "")) {
            justjoinitUrl += "/" + inputLoc;
        }


        if (!Objects.equals(inputExp, "")) {
            justjoinitUrl += "/experience-level_" + inputExp;
        }

        if (!Objects.equals(inputOffName, "")) {
            inputOffName = inputOffName.replace(" ", "+");
            justjoinitUrl += "/with-salary_yes?keyword=" + inputOffName;
        } else {
            justjoinitUrl += "/with-salary_yes?";
        }
    }


    public static String[] getModifiedUrls(String inputLoc, String inputOffName, String inputExp) {

        getNfjUrl(inputLoc, inputOffName, inputExp);
        getJjitUrl(inputLoc, inputOffName, inputExp);

        String[] urls = new String[2];
        urls[0] = nofluffjobsUrl;
        urls[1] = justjoinitUrl;

        nofluffjobsUrl = "https://nofluffjobs.com";
        justjoinitUrl = "https://justjoin.it";

        return urls;
    }
}
