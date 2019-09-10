package com.stackroute.socketone.adapter;


import com.google.gson.JsonObject;
import com.ibm.common.activitystreams.Activity;
import org.json.JSONStringer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import rx.subjects.PublishSubject;
//import rx.Observable;
//import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;

import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

@Service
//@EnableScheduling
public class Tweets {
    WebClient client = WebClient.builder()
            .baseUrl("https://api.twitter.com/1.1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
//    private PublishSubject<Tweets> publishTweets;

    //MediaType.TEXT_EVENT_STREAM
    @GetMapping("/tweets")
    public Flux<Object> getTweets() {

        Flux<Object> twitter;

        twitter = client.get()
                .uri("/statuses/user_timeline.json?screen_name=CGI_Global")
                .header("Authorization", "OAuth oauth_consumer_key=\"G7LtjidMw3EZhLpwrmpZiiCb5\",oauth_token=\"1164104302207438848-ui7otiNlf8FQIOq5vefYuXlmKlAQlm\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1568090735\",oauth_nonce=\"EUiOVe\",oauth_version=\"1.0\",oauth_signature=\"tDvAuZ%2BB4PrH9KjyAjO3i4J393M%3D\"")
                .retrieve()
                .bodyToFlux(Object.class);
//        publishTweets=PublishSubject.create();


     return twitter;
  }


    @Scheduled(fixedDelay = 3000)
    public void schedule() throws InterruptedException {
        int id=0;
        Tweets tweets = new Tweets();
        Flux<Object> result = tweets.getTweets();
        List<Object> myArray = new ArrayList<>();

        result.subscribe(myArray::add);
        Disposable disposable=this.getTweets().subscribe(res -> {
            Activity.Builder activity=activity().actor("tweets").verb("post").object(String.valueOf(res));
//            System.out.println(activity.get());

            System.out.println(res);

        });
        }



    public Disposable publisher(){
//        Object publisherObject=client.get().
//                uri("/statuses/user_timeline.json?screen_name=narendramodi&count=20")
//                .header("Authorization",
//                        "OAuth oauth_consumer_key=\"G7LtjidMw3EZhLpwrmpZiiCb5\"," +
//                                "oauth_token=\"1164104302207438848-ui7otiNlf8FQIOq5vefYuXlmKlAQlm\"," +
//                                "oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1567616244\"," +
//                                "oauth_nonce=\"MCIqTE\",oauth_version=\"1.0\"," +
//                                "oauth_signature=\"S%2FMhpkwwC0wPhi%2B5EjqQYOdY%2F%2BE%3D\"")
//                .retrieve()
//                .bodyToFlux(Object.class);
//        PublishSubject<Object> publishSubject=PublishSubject.create();
//        publishSubject.subscribe(System.out::println);
//        publishSubject.onNext(publisherObject);
//        publishSubject.onNext(publisherObject);
//        Subscription subscription= publishSubject.subscribe(a -> System.out.println(publisherObject));
//        return subscription;
        PublishSubject<Object> subject = PublishSubject.create();
        subject.subscribe(x -> System.out.println(x));
        Disposable subscriber= getTweets().subscribe(object -> {
            int from=object.toString().indexOf("truncated");
            int to=object.toString().indexOf("text");
            String substring = object.toString().substring(to, from);
            Activity activity1=activity().verb("post").actor("Twitter-Adapter").object(object("tweet")
                    .content(substring))
                    .get();
            System.out.println(activity1);

        });
        return subscriber;
    }


//   @Scheduled(fixedDelay = 3000)
//public  Activity met(int port) throws IOException, ParseException {
//        Properties properties=new Properties();
//
//        URL url=new URL("http://localhost:"+port+"/tweets");
//
//
//        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.connect();
//
//
//        Scanner sc = new Scanner(url.openStream());
//
//        String inline=null;
//
//        while(sc.hasNext())
//        {
//            inline =sc.nextLine()+inline;
//
//            System.out.println("\n");
//        }
//
//        System.out.println(inline);
//        System.out.println(inline.contains("{\"created_at"));
//        System.out.println(inline.contains("},{"));
//
//        sc.close();
//
//        inline = inline.replace("[", "").replace("]", "");
//        inline = inline.substring(1, inline.length() - 1);
//        String[] split = inline.split("[}][,][{]");
//        Activity activity = null;
//        for (String string : split) {
////            System.out.println(string);
//
//                String from=string.substring(inline.indexOf("\"expanded_url"),inline.indexOf(",\"display_url")-13);
//                String display=string.substring(inline.indexOf("\"display_url"),inline.indexOf(",\"in")-3);
//                activity=activity().verb("post").object(string).actor(from).actor(display).get();
//            System.out.println(activity);
//
//        }
//
//
//   return activity; }

//public Observable observableMethod(){
//    Observable<Object> observable = Observable.from(this.getTweets().toIterable());
////    ConnectableObservable<TimeInterval<Object>> observable1= observable.repeatWhen(i -> i);
////    Subscription subscribe= observable.subscribe(item ->
////            System.out.println(item));
//return observable;}
//
//    public PublishSubject<Tweets> getPublishTweets() {
//        return publishTweets;
//    }

}












