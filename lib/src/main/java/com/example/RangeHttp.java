package com.example;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by huixing on 2017/1/1.
 */

public class RangeHttp {

    public static void main(String [] args) throws IOException {

        String  tupian = "https://pic4.zhimg.com/v2-e2e0612fa7455d03a37cd4247bc822a7_200x112.jpg";
        String  url   = "http://news-at.zhihu.com/api/4/themes";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Range","bytes=0-")
                .url
                (tupian).build();

        Response response = client.newCall(request).execute();


        System.out.println("contentlengthis:"+response.body().contentLength());

        if (response.isSuccessful()){


            Headers headers = response.headers();

            for (int i=0;i<headers.size();i++){

                System.out.println(headers.name(i)+" "+headers.value(i));
            }
        }
        else
        {
            System.out.println("请求失败");
        }

    }
}
