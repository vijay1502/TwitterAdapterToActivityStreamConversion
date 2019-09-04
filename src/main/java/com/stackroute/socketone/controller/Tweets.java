package com.stackroute.socketone.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;
import com.ibm.common.activitystreams.Activity;
import net.minidev.json.parser.ParseException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

@RestController
//@EnableScheduling
public class Tweets {
    WebClient client = WebClient.builder()
            .baseUrl("https://api.twitter.com/1.1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    //MediaType.TEXT_EVENT_STREAM
    @GetMapping("/tweets")
    public Flux<Object> getTweets() {

        Flux<Object> twitter;

        twitter = client.get()
                .uri("/statuses/user_timeline.json?screen_name=narendramodi")
                .header("Authorization", "OAuth oauth_consumer_key=\"G7LtjidMw3EZhLpwrmpZiiCb5\",oauth_token=\"1164104302207438848-ui7otiNlf8FQIOq5vefYuXlmKlAQlm\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1567498916\",oauth_nonce=\"cN9oAy\",oauth_version=\"1.0\",oauth_signature=\"t86f4Q07UsbdB0UCrli%2BIPbLDio%3D\"")
                .retrieve()
                .bodyToFlux(Object.class);
        return twitter;
  }

//
    @Scheduled(fixedDelay = 3000)
    public void schedule() throws InterruptedException {
        Tweets tweets = new Tweets();
        Flux<Object> result = tweets.getTweets();
        List<Object> myArray = new ArrayList<>();

        result.subscribe(myArray::add);

        this.getTweets().subscribe(res -> {

            System.out.println(res);


        });
//
    }
//    }

   @Scheduled(fixedDelay = 3000)
    public Activity met(int port) throws IOException, ParseException {
        Properties properties=new Properties();
        URL url=new URL("http://localhost:"+port+"/tweets");


        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();


        Scanner sc = new Scanner(url.openStream());

        String inline=null;

        while(sc.hasNext())
        {
            inline =sc.nextLine()+inline;

            System.out.println("\n");
        }

        System.out.println(inline);
        System.out.println(inline.contains("{\"created_at"));
        System.out.println(inline.contains("},{"));

        sc.close();

        inline = inline.replace("[", "").replace("]", "");
        inline = inline.substring(1, inline.length() - 1);
        String[] split = inline.split("[}][,][{]");
        Activity activity = null;
        for (String string : split) {
//            System.out.println(string);

                String from=string.substring(inline.indexOf("\"expanded_url"),inline.indexOf(",\"display_url")-13);
                String display=string.substring(inline.indexOf("\"display_url"),inline.indexOf(",\"in")-3);
                activity=activity().verb("post").object(string).actor(from).actor(display).get();
            System.out.println(activity);

        }



   return activity; }


}












