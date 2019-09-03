package com.stackroute.socketone;

import com.ibm.common.activitystreams.Activity;
import com.stackroute.socketone.controller.Data;
import com.stackroute.socketone.controller.Tweets;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

@SpringBootApplication
public class SocketOneApplication {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		SpringApplication.run(SocketOneApplication.class, args);
		Tweets tweets=new Tweets();
		System.out.println(tweets.getTweets());

		Data data=new Data();
		data.met();

    }


}


