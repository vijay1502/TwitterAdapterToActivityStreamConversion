package com.stackroute.socketone.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
//    @Scheduled(fixedDelay = 3000)
//    public void schedule() throws InterruptedException {
//        Tweets tweets = new Tweets();
//        Flux<Object> result = tweets.getTweets();
//        List<Object> myArray = new ArrayList<>();
//
//        result.subscribe(myArray::add);
//
//        this.getTweets().subscribe(res -> {
//
//                System.out.println(res);
//
//
//        });
//
//}
//    }
}












