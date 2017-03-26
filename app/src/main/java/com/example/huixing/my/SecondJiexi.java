package com.example.huixing.my;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixing on 2017/1/10.
 */

public class SecondJiexi {

    public static List<String> secondRequest() throws IOException {

        List<Stories> response = Gsonjiexizhihu.request();

        String baseUrl = "http://news-at.zhihu.com/api/4/news/";


        List<String> matchUrl = new ArrayList<>();
        for (Stories story: response
             ) {
            String url = baseUrl+story.getId();
            matchUrl.add(url);

        }

        return matchUrl;


    }


    public static void main(String [] args) throws IOException {

        List<String> list = SecondJiexi.secondRequest();

        for (String  str: list
             ) {
            System.out.println(""+str);
        }

    }
}
