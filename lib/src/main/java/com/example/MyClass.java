package com.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyClass {

  public static void main(String [] args) throws IOException {

      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder().url("http://www.zhihu.com").build();

      Response execute = client.newCall(request).execute();

      if (execute.isSuccessful()){
          System.out.println(execute.body().string());

      }

  }



}
