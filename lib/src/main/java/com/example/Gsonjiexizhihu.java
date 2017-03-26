package com.example;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixing on 2017/1/10.
 */

public class Gsonjiexizhihu {

    public static  List<Stories> request() throws IOException {

        String url = "http://news-at.zhihu.com/api/4/news/latest";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        String str = response.body().string();

        JsonObject object =  new JsonParser().parse(str).getAsJsonObject();
        JsonArray array = object.get("stories").getAsJsonArray();
        List<Stories> stories = new ArrayList<>();
        for (JsonElement element: array
                ) {
            Gson gson = new Gson();
            Stories story = gson.fromJson(element,Stories.class);
            stories.add(story);

        }


        return  stories;

    }

    //请求到的数据进行测试
//    public static void main(String [] args) throws IOException {
//
//        List<Stories> response = Gsonjiexizhihu.request();
//        System.out.println(""+response.size());
//        for (Stories story: response
//             ) {
//
//            System.out.println("id:"+story.getId()+
//                    "  tittle:"+story.getTitle()+" images:"+story.getImages()[0]);
//
//        }
//
//
//    }
}
