package com.example;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by huixing on 2016/12/30.
 */

public class AsynkRequest {


    public  static  void myRequest(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){

            System.out.println(Thread.currentThread().getId());
            System.out.println(response.body().string());
        }

    }



    public static void AsynkRerequesting(String url){


        System.out.println(Thread.currentThread().getId());

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){

                    System.out.println(Thread.currentThread().getId());
                    System.out.println(call.isExecuted());

                }

            }
        });

    }

    public static void main(String [] args) throws IOException {
        String url = "http://www.zhihu.com";


        AsynkRerequesting(url);

//        myRequest(url);
    }
}
