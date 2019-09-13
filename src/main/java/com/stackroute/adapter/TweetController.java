package com.stackroute.adapter;

import com.stackroute.Exceptions.EmptyQueryParameterException;
import com.stackroute.TwitterService.Tweets;
import org.apache.http.client.utils.URIBuilder;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.subjects.PublishSubject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
@RequestMapping("v1/tweet/")
public class TweetController {
private SchedulerFactory schedulerFactory;
private Scheduler scheduler;
private Trigger trigger;
private List<String> queryParams;
private URI apiQueryURI=null;
private static final String BASE_URI="https://api.twitter.com/1.1/search/tweets.json";
private Tweets tweets;
public TweetController(Tweets tweets){
    this.tweets=tweets;
}
//@GetMapping("/tweets/{searchData}")
//public Tweets getTweets(){
//return (Tweets) tweets.getTweets();
//}

    public TweetController() throws IOException, SchedulerException {
        schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        trigger = newTrigger().withIdentity("Twitter-Api", "TweetGroup").startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(120).repeatForever()).build();
       PublishSubject.create();
    }

    public void setQueryParams(List<String> queryParams) {
        this.queryParams = queryParams;
    }
    public List<String> getQueryParams(){return queryParams;}

    public void addQueryParam(String queryParam) {
        /*
         * Check if queryParams list is empty and if it's empty then
         * initialize it.
         * */
        if (this.queryParams == null) {
            this.queryParams = new ArrayList<>();
        }
        this.queryParams.add(queryParam);
    }

    private void buildURI() throws EmptyQueryParameterException {
        try {
            URIBuilder apiURIBuilder = new URIBuilder(BASE_URI);
            queryParams.forEach(queryParam -> apiURIBuilder.addParameter("q", queryParam));
            apiURIBuilder.addParameter("language", "en");
            if (this.queryParams != null && (!this.queryParams.isEmpty())) {
                this.apiQueryURI = apiURIBuilder.build();
            } else {
                throw new EmptyQueryParameterException();
            }
        } catch (URISyntaxException e) {
            // TODO Handle exception to logger or something else
            e.printStackTrace();
        }
    }

//    private void addJobToScheduler() throws SchedulerException {
//        if (tweetFetcher != null && trigger != null) {
//            scheduler.scheduleJob(tweetFettcher, trigger);
//        }
//    }

    /**
     * Start the scheduler.
     * @throws SchedulerException
     */
    private void startTweetFetchService() throws SchedulerException {
        scheduler.start();
    }

    /**
     * Stops the NewsFetchService by shutting down the scheduler.
     * @throws SchedulerException
     */
    private void stopTweetFetchService() throws SchedulerException {
        scheduler.shutdown(true);
        System.out.println("Scheduler shut down");
    }

    /**
     * Used to start fetching data from newsapi.org by making repeated calls using Quartz
     * scheduler.
     * The method builds a query URI which is used by NewsFetchService to query the newapi.org server.
     * After building the URI, initialize the newsFetchJob by calling initNewsFetchJob().
     * Add the initialized newsFetchJob to the scheduler by calling addJobToScheduler().
     * Start the scheduler by calling the startNewsFetchService().

     */
//    public void startNewsStream() throws Exception {
//        if (this.queryParams != null && (!this.queryParams.isEmpty())) {
//            buildURI();
//            /*Check if URI is not Unull*/
//            if (apiQueryURI != null) {
//                initNewsFetchJob();
//                addJobToScheduler();
//                startTweetFetchService();
//            } else {
//                throw new Exception();
//            }
//        } else {
//            throw new EmptyQueryParameterException();
//        }
//    }

    /**
     * Method to initialize the newsFetchJob with data required for NewsFetchService.
     * Creates a JobDataMap, to which data is added.
     * The JobDataMap object is then used to build the newsFetchJob.
     */
//    private void initNewsFetchJob() {
//        if (webClient != null && apiQueryURI != null) {
//            JobDataMap jobDataMap = new JobDataMap();
//            jobDataMap.put("APIQueryURI", apiQueryURI);
//            jobDataMap.put("webClient", webClient);
//            jobDataMap.put("articlePublishSubject", articlePublishSubject);
//            newsFetchJob = newJob(NewsFetchService.class)
//                    .withIdentity("newsFetchJob", "newsFetchJobGroup")
//                    .usingJobData(jobDataMap)
//                    .build();
//        }
//    }

//    public PublishSubject<Activity> getArticleSubject() {
//        return articlePublishSubject;
//    }
//
//    /**
//     * Used to stop the news stream by stopping the scheduler job for NewsFetchService.
//     *
//     * @throws SchedulerException
//     */
//    public void stopNewsStream() throws SchedulerException {
//
//        stopNewsFetchService();
//    }
}


