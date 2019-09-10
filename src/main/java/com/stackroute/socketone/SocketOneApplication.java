package com.stackroute.socketone;

import com.stackroute.socketone.adapter.Tweets;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import reactor.core.Disposable;
//import reactor.core.publisher.Flux;
//import rx.subjects.PublishSubject;

import java.io.IOException;

import static com.ibm.common.activitystreams.Makers.object;

@SpringBootApplication
public class SocketOneApplication {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		SpringApplication.run(SocketOneApplication.class, args);
		Tweets tweets=new Tweets();
//		tweets.publisher();
		tweets.publisher();
//		tweets.schedule();
//		PublishSubject<Tweets> publishSubject=tweets.getPublishTweets();
//		Disposable disposable= (Disposable) publishSubject.subscribe(System.out::println);


//		tweets.observableMethod();
//		SinceId sinceId= new SinceId();
//
//		sinceId.since_id();

//		Scanner scanner=new Scanner(System.in);
//		System.out.println("Enter your Port: ");
//		int port=scanner.nextInt();
//		System.out.println(tweets.getTweets());

//		Scanner scanner=new Scanner(System.in);

//		int port=scanner.nextInt();
//		System.out.println(tweets.met(port));
//		tweets.some();
//		System.out.println(tweets.met(port));

    }


}


