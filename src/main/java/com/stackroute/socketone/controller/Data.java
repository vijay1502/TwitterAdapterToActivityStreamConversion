package com.stackroute.socketone.controller;

import com.ibm.common.activitystreams.Activity;
import net.minidev.json.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import static com.ibm.common.activitystreams.Makers.activity;

@RestController
//@EnableScheduling
public class Data {

    @Scheduled(fixedDelay = 3000)
    public void met() throws IOException, ParseException {
        URL url=new URL("http://localhost:8080/tweets");

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
//
//
//        inline = inline.substring(1, inline.length()-1);           //remove curly brackets
//        String[] keyValuePairs = inline.split(","); //split the string to creat key-value pairs
//
//        Map<String,String> map = new HashMap<>();
//
//        for(String pair : keyValuePairs)                        //iterate over the pairs
//        {
//            String[] entry = pair.split(":");                   //split the pairs to get key and value
//            map.put(entry[0].trim(), entry[entry.length-1].trim());
////            System.out.println(pair);
//
//            //add them to the hashmap and trim whitespaces
//        }


        inline = inline.replace("[", "").replace("]", "");
        inline = inline.substring(1, inline.length() - 1);
        String[] split = inline.split("[}][,][{]");
        for (String string : split) {
//            System.out.println(string);
            if (string.contains("created_at")){
                String from=string.substring(inline.indexOf("\"expanded_url"),inline.indexOf(",\"display_url")-13);
                String display=string.substring(inline.indexOf("\"display_url"),inline.indexOf(",\"in")-3);
            Activity activity=activity().verb("post").object(string).actor(from).actor(display).get();
            System.out.println(activity);}
        }



    }

}
