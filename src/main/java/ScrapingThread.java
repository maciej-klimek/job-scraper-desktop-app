public class ScrapingThread extends Thread {
    private String noFluffJobsUrl;
    private String justJoinItUrl;

    public ScrapingThread(String noFluffJobsUrl, String justJoinItUrl) {
        this.noFluffJobsUrl = noFluffJobsUrl;
        this.justJoinItUrl = justJoinItUrl;
    }

    @Override
    public void run() {
        // Your thread logic using noFluffJobsUrl and justJoinItUrl
        //Scraper.scrapeData(noFluffJobsUrl, justJoinItUrl);
    }

}

