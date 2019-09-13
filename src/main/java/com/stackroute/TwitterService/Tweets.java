package com.stackroute.TwitterService;


import com.ibm.common.activitystreams.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

@Service
//@EnableScheduling
public class Tweets {

    @Value("${consumer.key}")
    String consumerKey;
    @Value("${consumer.secret}")
    String consumerSecret;
    @Value("${access.token}")
    String accessToken;
    @Value("${access.token.secret}")
    String accessTokenSecret;


    public void getTweets() {

        Flux<Object> twitter;
        Twitter twitter2 = new TwitterTemplate("7DrIzeG3MkSRdCyHmF3D1paTI", "6Bl3O67toi74k6Q605k8HSxHHfDVor3VzZfsTZuELlKqHHK1Gl", "1164104302207438848-qPATTkkC4l21HN4m92xCeQ56W2HGoT", "8rwPOCNX2RDXafS0x7FqD00rBJJyrbvhWy0N0M3I5U7qE");
        SearchResults tweets = twitter2.searchOperations().search("Disasters");
        List list=new ArrayList();
        for (Tweet twe : tweets.getTweets()) {
            String text = twe.getText();

           Activity activity = activity().actor("twitter").verb("post").object(object("tweet").content(text)).get();
          activity.toString();
            System.out.println(activity);

        }



    }

}












