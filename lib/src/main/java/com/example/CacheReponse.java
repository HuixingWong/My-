package com.example;

import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by huixing on 2016/12/31.
 */

public class CacheReponse {

    public static void main(String [] args) throws IOException {
        int maxSize = 10*1024*1024;
        Cache cache = new Cache(new File("/Users/huixing/Desktop/OkhttpCacheDir"),maxSize);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();
        Request request = new Request.Builder().url(" http://news-at.zhihu.com/api/4/themes").
                //cacheControl(new CacheControl.Builder().noCache().build()).
                        cacheControl(new CacheControl.Builder().maxStale(1, TimeUnit.MINUTES).build()).
                        build();

        Response response = client.newCall(request).execute();

        String body1 = response.body().string();

        System.out.println("networkresponse"+response.networkResponse());

        System.out.println("cache"+response.cacheResponse());

        System.out.println("==================================");

        Response response1 = client.newCall(request).execute();

        String body2 = response1.body().string();

        System.out.println("networkresponse"+response1.networkResponse());

        System.out.println("cache"+response1.cacheResponse());


    }

}
