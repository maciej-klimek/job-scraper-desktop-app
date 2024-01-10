public class ScrapingThread implements Runnable {
    private String noFluffJobsUrl;
    private String justJoinItUrl;

    public ScrapingThread(String noFluffJobsUrl, String justJoinItUrl) {
        this.noFluffJobsUrl = noFluffJobsUrl;
        this.justJoinItUrl = justJoinItUrl;
    }

        @Override
        public void run () {
            // Your thread logic using noFluffJobsUrl and justJoinItUrl
            // Create two instances of Scraper
            Scraper scraperNFJ = new Scraper();
            Scraper scraperJJIT = new Scraper();

            // Start both Scrapers concurrently
            Thread threadNFJ = new Thread(() -> scraperNFJ.scrapeDataNFJ(noFluffJobsUrl));
            Thread threadJJIT = new Thread(() -> scraperJJIT.scrapeDataJJIT(justJoinItUrl));

            threadNFJ.start();
            threadJJIT.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


