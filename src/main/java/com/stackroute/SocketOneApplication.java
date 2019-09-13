package com.stackroute;

import com.stackroute.TwitterService.Tweets;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import reactor.core.Disposable;
//import reactor.core.publisher.Flux;
//import rx.subjects.PublishSubject;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.ibm.common.activitystreams.Makers.object;

@SpringBootApplication
public class SocketOneApplication {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		SpringApplication.run(SocketOneApplication.class, args);

    }
    @PostConstruct
	public void some(){
		Tweets tweets=new Tweets();
		tweets.getTweets();
//		System.out.println(tweets.tweetActivity());
	}


}


